package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.entity.BuildingEntity;
import com.example.repository.entity.RentAreaEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,BuildingRepositoryCustom {
	
}
