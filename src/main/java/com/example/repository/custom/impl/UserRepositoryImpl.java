package com.example.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.repository.custom.UserRepositoryCustom;
import com.example.repository.entity.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	
	

	@Override
	public List<UserEntity> findByRole(String roleCode) {
		try {
			String sql = "SELECT u FROM UserEntity as u JOIN u.roles as r WHERE r.code = '"+roleCode+"'";
			Query query = entityManager.createQuery(sql, UserEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
