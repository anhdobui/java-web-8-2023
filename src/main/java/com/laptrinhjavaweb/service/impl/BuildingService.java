package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.enumDefine.TypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();

        for (BuildingEntity item : entities) {
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(item.getId());
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item, rentAreaEntities);
            result.add(buildingDTO);
        }
        return result;
    }


    @Override
    public Page<BuildingDTO> findBuilding(BuildingSearchDTO buildingserch, Pageable pageable) {
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingserch);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder,pageable);
        long totalItem= totalItemFound(buildingSearchBuilder);
        buildingEntities.stream().forEach(item -> {
            List<RentAreaEntity> areaEntities = rentAreaRepository.findByBuildingId(item.getId());
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item, areaEntities);
            buildingDTOs.add(buildingDTO);
        });
        Page<BuildingDTO> result = new PageImpl<>(buildingDTOs,pageable,totalItem);
        return result;
    }

    @Override
    public Map<DistrictEnum, String> getDistricMaps() {
        Map<DistrictEnum, String> result = new LinkedHashMap<>();
        for (DistrictEnum district : DistrictEnum.values()) {
            result.put(district, district.getName());
        }
        return result;
    }

    @Override
    public Map<TypeEnum, String> getTypeMaps() {
        Map<TypeEnum, String> result = new LinkedHashMap<>();
        for (TypeEnum type : TypeEnum.values()) {
            result.put(type, type.getName());
        }
        return result;
    }

    @Override
    public BuildingDTO getBuilding(Long id) {
        BuildingDTO result = null;
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if (buildingEntity != null) {
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(id);
            result = buildingConverter.convertToDto(buildingEntity, rentAreaEntities);
        }
        return result;
    }

    private BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingSearchDTO buildingserch) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                .setName(buildingserch.getName())
                .setFloorArea(buildingserch.getFloorArea())
                .setWard(buildingserch.getWard())
                .setStreet(buildingserch.getStreet())
                .setDistrictCode(buildingserch.getDistrictCode())
                .setDirection(buildingserch.getDirection())
                .setLevel(buildingserch.getLevel())
                .setManagerName(buildingserch.getManagerName())
                .setManagerPhone(buildingserch.getManagerPhone())
                .setNumberOfBasement(buildingserch.getNumberOfBasement())
                .setRentAreaFrom(buildingserch.getRentAreaFrom())
                .setRentAreaTo(buildingserch.getRentAreaTo())
                .setCostRentFrom(buildingserch.getCostRentFrom())
                .setCostRentTo(buildingserch.getCostRentTo())
                .setTypes(buildingserch.getTypes())
                .setStaffId(buildingserch.getStaffId())
                .build();
        return result;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingEntity = buildingRepository.save(buildingEntity);
        List<RentAreaEntity> newRentAreaEntities = handleFindNewRentAreas(buildingDTO.getRentArea(), buildingEntity);
        newRentAreaEntities.stream().forEach(item -> {
            rentAreaRepository.save(item);
        });
    }

    private List<RentAreaEntity> handleFindNewRentAreas(String rentAreaValues, BuildingEntity buildingEntityEdit) {
        List<RentAreaEntity> results = new ArrayList<>();
        List<Integer> newValuesOfRentarea = handleSplitRentareas(rentAreaValues);
        List<RentAreaEntity> rentAreasExit = rentAreaRepository.findByBuildingId(buildingEntityEdit.getId());
        rentAreasExit.stream().forEach(item -> {
            if (!newValuesOfRentarea.contains(item.getValue())) {
                rentAreaRepository.delete(item);
            } else {
                newValuesOfRentarea.remove(item.getValue());
            }
        });
        newValuesOfRentarea.stream().forEach(item -> {
            RentAreaEntity newRentAreaEntity = new RentAreaEntity();
            newRentAreaEntity.setBuilding(buildingEntityEdit);
            newRentAreaEntity.setValue(item);
            results.add(newRentAreaEntity);
        });
        return results;
    }

    private List<Integer> handleSplitRentareas(String rentAreaValues) {
        List<Integer> result = Arrays.stream(rentAreaValues.split(","))
                .map(area -> {
                    try {
                        return Integer.parseInt(area.trim());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        ids.stream().forEach(id -> {
            BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
            if(buildingEntity != null){
                buildingEntity.getStaffs().clear();
                buildingRepository.save(buildingEntity);
                buildingRepository.delete(buildingEntity);
            }
        });

    }

    @Override
    @Transactional
    public void updateStaffOfBuilding(Long buildingId, List<Long> staffIds) {
        List<Long> newStaffIds = new ArrayList<>(staffIds);
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity != null) {
            List<UserEntity> currentStaffsOfBuilding = userRepository.findUsersByBuildings_Id(buildingId);
            currentStaffsOfBuilding.forEach(item -> {
                if (staffIds.contains(item.getId())) {
                    newStaffIds.remove(item.getId());
                } else {
                    buildingEntity.getStaffs().remove(item);
                    buildingRepository.save(buildingEntity);
                }
            });
            newStaffIds.forEach(id -> {
                UserEntity userEntity = userRepository.findById(id).orElse(null);
                if(userEntity != null){
                    buildingEntity.getStaffs().add(userEntity);
                    buildingRepository.save(buildingEntity);
                }
            });
        }
    }

    @Override
    public long totalItemFound(BuildingSearchBuilder buildingSearchBuilder) {
        return (long) buildingRepository.countBuildingFound(buildingSearchBuilder);
    }


}
