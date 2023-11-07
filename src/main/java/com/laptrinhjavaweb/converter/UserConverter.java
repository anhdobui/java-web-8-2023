package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
    public StaffResponseDTO convertToStaffResponDto(UserEntity entity, List<Long> staffIdsOfBuilding){
        StaffResponseDTO result = new StaffResponseDTO();
        result.setFullName(entity.getFullName());
        result.setStaffId(entity.getId());
        result.setChecked(staffIdsOfBuilding.contains(entity.getId()) ? "checked":"");
        return result;
    }
    public StaffResponseDTO convertToStaffResponDto(UserEntity entity, boolean isExits){
        StaffResponseDTO result = new StaffResponseDTO();
        result.setFullName(entity.getFullName());
        result.setStaffId(entity.getId());
        result.setChecked(isExits ? "checked":"");
        return result;
    }
}
