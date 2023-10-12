package com.example.repository.custom;

import com.example.repository.entity.RoleEntity;

public interface RoleRepositoryCustom {
	RoleEntity findByCode(String code);
}
