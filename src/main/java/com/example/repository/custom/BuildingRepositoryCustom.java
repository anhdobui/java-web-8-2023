package com.example.repository.custom;

import java.util.List;
import java.util.Map;

import com.example.builder.BuildingSearchBuilder;
import com.example.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
//	List<BuildingEntity> findAll();
//	BuildingEntity findById(Long id);
//	void save(BuildingEntity newBuilding);
//	void delete(BuildingEntity newBuilding);
	List<BuildingEntity> findAll(Map<String, Object> params, List<String> types);
	List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
