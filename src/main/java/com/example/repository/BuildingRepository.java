package com.example.repository;

import java.util.List;

import com.example.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll();
	BuildingEntity findById(Long id);
}
