package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDto(CustomerEntity entity){
        CustomerDTO result = modelMapper.map(entity,CustomerDTO.class);
        result.setStatus(!entity.isDeleted() ? "Đang xử lý":"Không xử lý");
        String staffName = entity.getStaffs() != null ? entity.getStaffs().stream().map(UserEntity::getFullName).collect(Collectors.joining(",")):"";
        result.setStaffName(staffName);
        return result;
    }

    public CustomerEntity convertToEntity(CustomerDTO newCustomer) {
        CustomerEntity result = modelMapper.map(newCustomer,CustomerEntity.class);
        return result;
    }
}
