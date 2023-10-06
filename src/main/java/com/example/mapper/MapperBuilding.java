package com.example.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.BuildingDTO;
import com.example.repository.DistrictRepository;
import com.example.repository.RentareaRepository;
import com.example.repository.RenttypeRepository;
import com.example.repository.UserRepository;
import com.example.repository.entity.BuildingEntity;
import com.example.repository.entity.DistrictEntity;
import com.example.repository.entity.RentareaEntity;
import com.example.repository.entity.RenttypeEntity;
import com.example.repository.entity.UserEntity;

@Component
public class MapperBuilding {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentareaRepository rentareaRepository;
	
	@Autowired
	private RenttypeRepository renttypeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO entityToDTO(BuildingEntity buildingEntity) {
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		DistrictEntity districtEntity = districtRepository.getById(buildingEntity.getDistrictId());
		String districtName = districtEntity.getName();
		String address = buildingEntity.getStreet()+" - "+buildingEntity.getWard()+" - "+ districtName;
		result.setAddress(address);
		List<RentareaEntity> rentareaEntities = rentareaRepository.getByBuildingId(buildingEntity.getId());
		String rentareaValuesStr = rentareaEntities.stream().map(RentareaEntity::getValue).map(item -> item.toString()).collect(Collectors.joining(","));
		List<RenttypeEntity> renttypeEntities = renttypeRepository.getByBuildingId(buildingEntity.getId());
		String typeNames = renttypeEntities.stream().map(RenttypeEntity::getName).collect(Collectors.joining(","));
		List<UserEntity> staffs = userRepository.getByBuildingId(buildingEntity.getId());
		String nameStaffs = staffs.stream().map(UserEntity::getFullname).collect(Collectors.joining(","));
		result.setRentarea(rentareaValuesStr);
		result.setTypes(typeNames);
		result.setStaffs(nameStaffs);
		return result;
	}
}
