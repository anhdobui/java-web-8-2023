package com.example.repository;

import java.util.List;
import java.util.Map;

import com.example.model.BuildingDTO;
import com.example.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll();
	List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> types);
}
