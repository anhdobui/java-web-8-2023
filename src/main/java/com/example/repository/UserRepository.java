package com.example.repository;

import java.util.List;

import com.example.repository.entity.UserEntity;

public interface UserRepository {
	List<UserEntity> getByBuildingId(Long id);
}
