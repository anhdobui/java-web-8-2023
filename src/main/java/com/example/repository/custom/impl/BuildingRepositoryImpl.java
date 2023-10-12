package com.example.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public List<BuildingEntity> findAll() {
////		JPQL
////		String sql = "FROM BuildingEntity";
//		
////		Query query = entityManager.createQuery(sql, BuildingEntity.class);
//		
////		SQL native
//		String sql = "select * from building";
//		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
//		return query.getResultList();
//	}
//
//	@Override
//	public BuildingEntity findById(Long id) {
//		String sql = "select * from building as b where b.id = "+id;
//		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
//		return (BuildingEntity) query.getSingleResult();
//	}
//
//	@Override
//	public void save(BuildingEntity newBuilding) {
//		if(newBuilding.getId() != null) {
//			entityManager.merge(newBuilding);
//		}else {
//			entityManager.persist(newBuilding);
//		}
//		
//		
//	}
//
//	@Override
//	public void delete(BuildingEntity newBuilding) {
//		entityManager.remove(newBuilding);
//		
//	}
	
}
