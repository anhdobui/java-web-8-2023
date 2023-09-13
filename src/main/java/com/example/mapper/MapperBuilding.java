package com.example.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.model.BuildingDTO;
import com.example.repository.entity.BuildingEntity;

@Component
public class MapperBuilding {
	public BuildingDTO entityToDTO(BuildingEntity buildingEntity,Map<String, Object> params) {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(buildingEntity.getName());
		buildingDTO.setId(buildingEntity.getId());
		buildingDTO.setDirection(buildingEntity.getDirection());
		buildingDTO.setFloorArea(buildingEntity.getFloorArea());
		buildingDTO.setLevel(buildingEntity.getLevel());
		buildingDTO.setNameManager(buildingEntity.getManagerName());
		buildingDTO.setPhoneManager(buildingEntity.getManagerPhone());
		buildingDTO.setStreet(buildingEntity.getStreet());
		buildingDTO.setNumberofbasement(buildingEntity.getNumberOfBasement());
		buildingDTO.setWard(buildingEntity.getWard());
		buildingDTO.setRentprice(buildingEntity.getRentprice());
		buildingDTO.setDistrictName((String) params.get("districtName"));
		buildingDTO.setRentarea((String) params.get("rentareaValues"));
		buildingDTO.setTypes((String) params.get("typesName"));
		buildingDTO.setStaffs((String) params.get("nameStaffs"));
		return buildingDTO;
	}
}
