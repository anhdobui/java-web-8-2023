package com.example.repository.custom;

import java.util.List;

import com.example.repository.entity.BuildingEntity;

public interface BuildingJdbc {
	List<BuildingEntity> findAll();
}
