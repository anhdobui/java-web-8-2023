package com.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.MapperBuilding;
import com.example.model.BuildingDTO;
import com.example.model.response.BuildingSearchResponse;
import com.example.repository.BuildingRepository;
import com.example.repository.DistrictRepository;
import com.example.repository.RentareaRepository;
import com.example.repository.RenttypeRepository;
import com.example.repository.UserRepository;
import com.example.repository.entity.BuildingEntity;
import com.example.repository.entity.DistrictEntity;
import com.example.repository.entity.RentareaEntity;
import com.example.repository.entity.RenttypeEntity;
import com.example.repository.entity.UserEntity;
import com.example.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private MapperBuilding mapperBuilding;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentareaRepository rentareaRepository;
	
	@Autowired
	private RenttypeRepository renttypeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BuildingSearchResponse> findAll() {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntitis = buildingRepository.findAll();
		for(BuildingEntity item: buildingEntitis) {
			BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
			buildingSearchResponse.setName(item.getName());
			buildingSearchResponse.setAddress(item.getStreet() + " - " + item.getWard());
			results.add(buildingSearchResponse);
		}
		return results;
	}

	@Override
	public List<BuildingDTO> findBuilding(Map<String, Object> params, List<String> types) {
		List<BuildingDTO> results = new ArrayList<>();
		List<BuildingEntity> buildingEntitis = buildingRepository.findBuilding(params, types);
		for(BuildingEntity item : buildingEntitis) {
			BuildingDTO buildingDTO = new BuildingDTO();
			DistrictEntity districtEntity = districtRepository.getById(item.getDistrictId());
			List<RentareaEntity> rentareaEntities = rentareaRepository.getByBuildingId(item.getId());
			List<RenttypeEntity> renttypeEntities = renttypeRepository.getByBuildingId(item.getId());
			List<UserEntity> staffs = userRepository.getByBuildingId(item.getId());
			List<String> rentareaValues = new ArrayList<String>();
			for(RentareaEntity rentareaEntity:rentareaEntities) {
				rentareaValues.add(rentareaEntity.getValue().toString());
			}
			List<String> typesName = new ArrayList<String>();
			for(RenttypeEntity renttypeEntity:renttypeEntities) {
				typesName.add(renttypeEntity.getName());
			}
			List<String> nameStaffs = new ArrayList<String>();
			for(UserEntity staff:staffs) {
				nameStaffs.add(staff.getFullname());
			}
			Map<String, Object> sideInfos = new HashMap<>();
			sideInfos.put("districtName", districtEntity.getName());
			sideInfos.put("rentareaValues", String.join(",", rentareaValues));
			sideInfos.put("typesName", String.join(",", typesName));
			sideInfos.put("nameStaffs", String.join(",", nameStaffs));
			buildingDTO = mapperBuilding.entityToDTO(item,sideInfos);
			results.add(buildingDTO);
		}
		return results;
	}
	
	
}
