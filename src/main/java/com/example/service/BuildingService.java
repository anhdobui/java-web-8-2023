package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.BuildingDTO;
import com.example.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> findAll();
	List<BuildingDTO> findBuilding(Map<String,Object> params, List<String> types);
}
