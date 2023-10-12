package com.example.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.repository.custom.RentAreaRepositoryCustom;
import com.example.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<RentAreaEntity> findByBuilding(Long buildingId) {
		// JPQL
		String sql = "FROM RentAreaEntity WHERE building.id = "+buildingId;
		Query query =  entityManager.createQuery(sql,RentAreaEntity.class);
		return query.getResultList();
	}
	
}
