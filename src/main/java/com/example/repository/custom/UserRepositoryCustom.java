package com.example.repository.custom;

import java.util.List;

import com.example.repository.entity.UserEntity;

public interface UserRepositoryCustom {
	List<UserEntity> findByRole(String roleCode);
}
