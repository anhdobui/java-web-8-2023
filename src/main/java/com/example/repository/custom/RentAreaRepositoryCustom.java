package com.example.repository.custom;

import java.util.List;

import com.example.repository.entity.RentAreaEntity;

public interface RentAreaRepositoryCustom {
	List<RentAreaEntity> findByBuilding(Long buildingId);
}
