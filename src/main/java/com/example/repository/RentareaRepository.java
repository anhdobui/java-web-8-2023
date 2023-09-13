package com.example.repository;

import java.util.List;

import com.example.repository.entity.RentareaEntity;

public interface RentareaRepository {
	List<RentareaEntity> getByBuildingId(Long id);
}
