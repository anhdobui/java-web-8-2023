package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.builder.BuildingSearchBuilder;
import com.example.converter.RentAreaConverter;
import com.example.model.BuildingDTO;
import com.example.model.RentAreaDTO;
import com.example.model.response.BuildingSearchResponse;
import com.example.repository.BuildingRepository;
import com.example.repository.RentAreaRepository;
import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.custom.RentAreaRepositoryCustom;
import com.example.repository.entity.BuildingEntity;
import com.example.repository.entity.RentAreaEntity;
import com.example.service.BuildingService;
import com.example.utils.MapUtils;

@Service
public class BuildingServiceImpl implements BuildingService {
//	@Autowired
//	private BuildingJdbc buildingJdbc;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private RentAreaConverter rentAreaConverter;
	

	@Override
	public List<BuildingSearchResponse> findAll(Map<String, Object> params, List<String> types) {
		List<BuildingSearchResponse> results = new ArrayList<>();
//		List<BuildingEntity> buildingEntitis = buildingJdbc.findAll();
//		List<BuildingEntity> buildingEntitis = buildingRepository.findAll();
//		List<BuildingEntity> buildingEntitis = buildingRepository.findAll(params,types);
		BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(params,types);
		List<BuildingEntity> buildingEntitis = buildingRepository.findAll(buildingSearchBuilder);
		for(BuildingEntity item: buildingEntitis) {
			BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
			buildingSearchResponse.setName(item.getName());
			buildingSearchResponse.setAddress(item.getStreet() + " - " + item.getWard());
			results.add(buildingSearchResponse);
		}
		return results;
	}

	private BuildingSearchBuilder convertToBuildingSearchBuilder(Map<String, Object> params, List<String> types) {
		BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
										.setName(MapUtils.getObject(params, "name", String.class))
										.setFloorArea(MapUtils.getObject(params, "floorarea", Integer.class))
										.setWard(MapUtils.getObject(params, "ward", String.class))
										.setStreet(MapUtils.getObject(params, "street", String.class))
										.setDistrict(MapUtils.getObject(params, "district", String.class))
										.setDirection(MapUtils.getObject(params, "direction", String.class))
										.setLevel(MapUtils.getObject(params, "level", String.class))
										.setManagerName(MapUtils.getObject(params, "managername", String.class))
										.setManagerPhone(MapUtils.getObject(params, "managerphone", String.class))
										.setNumberOfBasement(MapUtils.getObject(params, "numberofbasement", Integer.class))
										.setFromRentArea(MapUtils.getObject(params, "fromrentarea", Integer.class))
										.setToRentArea(MapUtils.getObject(params, "torentarea", Integer.class))
										.setFromRentPrice(MapUtils.getObject(params, "fromrentprice", Integer.class))
										.setToRentPrice(MapUtils.getObject(params, "torentprice", Integer.class))
										.setTypes(types)							
										.setStaffId(MapUtils.getObject(params, "staffid", Long.class))
										.build();
		return result;
	}

	@Override
	public List<RentAreaDTO> findRentAreaByBuilding(Long buildingId) {
//		BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
//		List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding_Id(buildingId);
		List<RentAreaDTO> results = rentAreaEntities.stream().map(rentAreaConverter::converterEntityToDto).collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = null;
		buildingRepository.save(buildingEntity);
		
	}
	
	
}
