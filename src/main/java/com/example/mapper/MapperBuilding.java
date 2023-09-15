package com.example.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public BuildingDTO entityToDTO(BuildingEntity buildingEntity) {
		BuildingDTO buildingDTO = new BuildingDTO();
		DistrictEntity districtEntity = districtRepository.getById(buildingEntity.getDistrictId());
		String districtName = districtEntity.getName();
		List<RentareaEntity> rentareaEntities = rentareaRepository.getByBuildingId(buildingEntity.getId());
		List<String> rentareaValues = new ArrayList<String>();
		for(RentareaEntity rentareaEntity:rentareaEntities) {
			rentareaValues.add(rentareaEntity.getValue().toString());
		}
		List<RenttypeEntity> renttypeEntities = renttypeRepository.getByBuildingId(buildingEntity.getId());
		List<String> typesName = new ArrayList<String>();
		for(RenttypeEntity renttypeEntity:renttypeEntities) {
			typesName.add(renttypeEntity.getName());
		}
		List<UserEntity> staffs = userRepository.getByBuildingId(buildingEntity.getId());
		List<String> nameStaffs = new ArrayList<>();
		for(UserEntity staff:staffs) {
			nameStaffs.add(staff.getFullname());
		}
		buildingDTO.setName(buildingEntity.getName());
		buildingDTO.setId(buildingEntity.getId());
		buildingDTO.setDirection(buildingEntity.getDirection());
		buildingDTO.setFloorArea(buildingEntity.getFloorArea());
		buildingDTO.setLevel(buildingEntity.getLevel());
		buildingDTO.setNameManager(buildingEntity.getManagerName());
		buildingDTO.setPhoneManager(buildingEntity.getManagerPhone());
		buildingDTO.setNumberofbasement(buildingEntity.getNumberOfBasement());
		buildingDTO.setRentprice(buildingEntity.getRentprice());
		buildingDTO.setRentarea(String.join(",", rentareaValues));
		buildingDTO.setTypes(String.join(",", typesName));
		buildingDTO.setStaffs(String.join(",", nameStaffs));
		String address = buildingEntity.getStreet()+" - "+buildingEntity.getWard()+" - "+ districtName;
		buildingDTO.setAddress(address);
		return buildingDTO;
	}
}
