package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.enumDefine.TypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();

        for (BuildingEntity item:entities){
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(item.getId());
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item,rentAreaEntities);
            result.add(buildingDTO);
        }
        return result;
    }

    @Override
    public List<BuildingDTO> findBuilding(BuildingSearchDTO buildingserch) {
        List<BuildingDTO> result = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingserch);
        List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder);
        buildingEntities.stream().forEach(item -> {
            List<RentAreaEntity> areaEntities = rentAreaRepository.findByBuildingId(item.getId());
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item,areaEntities);
            result.add(buildingDTO);
        });
        return result;
    }

    @Override
    public Map<DistrictEnum, String> getDistricMaps() {
        Map<DistrictEnum, String> result = new LinkedHashMap<>();
        for (DistrictEnum district : DistrictEnum.values()) {
            result.put(district,district.getName());
        }
        return result;
    }

    @Override
    public Map<TypeEnum, String> getTypeMaps() {
        Map<TypeEnum, String> result = new LinkedHashMap<>();
        for (TypeEnum type : TypeEnum.values()) {
            result.put(type,type.getName());
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
        BuildingEntity newBuildingEntity =  buildingRepository.save(buildingEntity);
        List<RentAreaEntity> rentAreaEntities = handleStrValuesToListRentAreaEntity(buildingDTO.getRentArea(),newBuildingEntity);
        rentAreaEntities.stream().forEach(rentAreaEntity -> {
            rentAreaRepository.save(rentAreaEntity);
        });
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        ids.stream().forEach(id -> {
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(id);
            rentAreaEntities.stream().forEach(item -> {
                rentAreaRepository.delete(item);
            });
            buildingRepository.deleteById(id);
        });

    }

    private List<RentAreaEntity> handleStrValuesToListRentAreaEntity(String strValues,BuildingEntity newBuildingEntity) {
        List<Integer> listRentareaForBuilding = Arrays.stream(strValues.split(","))
                .map(area -> {
                    try {
                        return Integer.parseInt(area.trim());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<RentAreaEntity> result = listRentareaForBuilding.stream().map(item -> {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(item);
            rentAreaEntity.setBuilding(newBuildingEntity);
            return rentAreaEntity;
        }).collect(Collectors.toList());
        return result;
    }
}
