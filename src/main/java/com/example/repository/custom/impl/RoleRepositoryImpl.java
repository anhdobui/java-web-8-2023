package com.example.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.repository.custom.RoleRepositoryCustom;
import com.example.repository.entity.RoleEntity;

@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public RoleEntity findByCode(String code) {
		String sql = "select * from role as r where r.code = '"+code+"'";
		Query query = entityManager.createNativeQuery(sql, RoleEntity.class);
		return (RoleEntity) query.getSingleResult();
	}

}
