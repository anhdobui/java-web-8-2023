package com.example.repository;

import java.util.List;

import com.example.repository.entity.BuildingEntity;

public interface BuildingJdbc {
	List<BuildingEntity> findAll();
}
