package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.BuildingDTO;
import com.example.model.RentAreaDTO;
import com.example.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> findAll(Map<String, Object> params, List<String> types);
	List<RentAreaDTO> findRentAreaByBuilding(Long buildingId);
	void save(BuildingDTO newBuilding);
}
