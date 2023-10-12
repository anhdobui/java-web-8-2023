package com.example.service;

import java.util.List;

import com.example.model.BuildingDTO;
import com.example.model.RentAreaDTO;
import com.example.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> findAll();
	List<RentAreaDTO> findRentAreaByBuilding(Long buildingId);
	void save(BuildingDTO newBuilding);
}
