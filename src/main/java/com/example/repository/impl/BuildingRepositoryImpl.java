package com.example.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.repository.BuildingRepository;
import com.example.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findAll() {
		String sql = "FROM BuildingEntity";
		Query query = entityManager.createQuery(sql, BuildingEntity.class);
		return query.getResultList();
	}
	
}
