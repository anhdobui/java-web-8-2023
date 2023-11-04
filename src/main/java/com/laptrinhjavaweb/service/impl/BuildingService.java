package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.enumDefine.TypeEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.File;
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
    private UploadFileUtils uploadFileUtils;

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
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        if (buildingId != null) { // update
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                    .orElse(null);
            if(foundBuilding != null){
                buildingEntity.setAvatar(foundBuilding.getAvatar());
                buildingEntity.setStaffs(foundBuilding.getStaffs());
                buildingEntity.setRentAreas(foundBuilding.getRentAreas());
            }
        }
        saveThumbnail(buildingDTO, buildingEntity);
        handleSetNewRentAreas(buildingDTO.getRentArea(),buildingEntity);
        buildingRepository.save(buildingEntity);
    }

    private void handleSetNewRentAreas(String rentAreaValues, BuildingEntity buildingEntityEdit) {
        List<Integer> newValuesOfRentarea = handleSplitRentareas(rentAreaValues);
        if(buildingEntityEdit.getRentAreas() != null){
            buildingEntityEdit.getRentAreas().removeIf(item -> !newValuesOfRentarea.contains(item.getValue()));
            newValuesOfRentarea.removeAll(buildingEntityEdit.getRentAreas().stream()
                    .map(RentAreaEntity::getValue)
                    .collect(Collectors.toList()));
        }else{
            buildingEntityEdit.setRentAreas(new ArrayList<>());
        }

        List<RentAreaEntity> newRentAreas =  newValuesOfRentarea.stream().map(item -> {
            RentAreaEntity newRentAreaEntity = new RentAreaEntity();
            newRentAreaEntity.setBuilding(buildingEntityEdit);
            newRentAreaEntity.setValue(item);
            return newRentAreaEntity;
        }).collect(Collectors.toList());
        buildingEntityEdit.getRentAreas().addAll(newRentAreas);
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
        if(ids.size() >0){
            Long count = buildingRepository.countByIdIn(ids);
            if(count != ids.size()){
                throw new NotFoundException(SystemConstant.BUILDING_NOT_FOUND);
            }
            buildingRepository.deleteByIdIn(ids);
        }
    }

    @Override
    @Transactional
    public void updateStaffOfBuilding(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity != null) {
            List<UserEntity> newStaffs = userRepository.findByIdIn(staffIds);
            if(staffIds.size() != newStaffs.size()){
                throw new NotFoundException(SystemConstant.USER_NOT_FOUND);
            }
            buildingEntity.setStaffs(newStaffs);
            buildingRepository.save(buildingEntity);
        }
    }

    @Override
    public long totalItemFound(BuildingSearchBuilder buildingSearchBuilder) {
        return (long) buildingRepository.countBuildingFound(buildingSearchBuilder);
    }


    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
//                    File file = new File("C://home/office" + buildingEntity.getAvatar());
//                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }

}
