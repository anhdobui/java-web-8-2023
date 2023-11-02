package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToDto (BuildingEntity entity, List<RentAreaEntity> rentAreaEntities){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        StringBuilder address = new StringBuilder("");
        address.append(!StringUtils.isNullOrEmpty(entity.getStreet())? entity.getStreet()+"-":"");
        address.append(!StringUtils.isNullOrEmpty(entity.getWard())? entity.getWard()+"-":"");
        address.append(!StringUtils.isNullOrEmpty(entity.getDistrictCode()) ? DistrictEnum.valueOf(entity.getDistrictCode()).getName():"");
        result.setAddress(address.toString());

        String rentareaStr = rentAreaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
        result.setRentArea(rentareaStr);
        result.setImage(entity.getAvatar());
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto,BuildingEntity.class);
        result.setAvatar(dto.getImage());
        return result;
    }
}
