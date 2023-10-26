package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
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
        address.append(entity.getStreet()!=null? entity.getStreet()+"-":"");
        address.append(entity.getWard()!=null? entity.getWard()+"-":"");
        address.append(entity.getDistrictCode()!=null? DistrictEnum.valueOf(entity.getDistrictCode()).getName():"");
        result.setAddress(address.toString());

        String rentareaStr = rentAreaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
        result.setRentArea(rentareaStr);
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto,BuildingEntity.class);
        return result;
    }
}
