package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.RentAreaDTO;
import com.example.repository.entity.RentAreaEntity;

@Component
public class RentAreaConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public RentAreaDTO converterEntityToDto(RentAreaEntity rentAreaEntity) {
		RentAreaDTO result = modelMapper.map(rentAreaEntity, RentAreaDTO.class);
		return result;
	}
}
