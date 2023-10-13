package com.example.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.builder.BuildingSearchBuilder;
import com.example.constant.SystemConstant;
import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.entity.BuildingEntity;
import com.example.utils.MapUtils;
import com.example.utils.NumberUtils;
import com.example.utils.StringUtils;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> types) {
		List<BuildingEntity> results = new ArrayList<>();
		ResultSet rs = null;
		try {
			StringBuilder finalQuery = new StringBuilder();
			StringBuilder joinQuery = new StringBuilder();
			StringBuilder whereQuery = new StringBuilder();

			finalQuery.append("select b.*").append("\nfrom building as b");
			buildQueryWithJoin(params, types, whereQuery, joinQuery);
			buildQueryWithoutJoin(params, whereQuery);
			finalQuery.append(joinQuery).append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery)
					.append("\n group by b.id");
			String sqlQuery = finalQuery.toString();
			Query query = entityManager.createNativeQuery(sqlQuery, BuildingEntity.class);
			results = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return results;
	}

	private void buildQueryWithoutJoin(Map<String, Object> params, StringBuilder whereQuery) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("staffid") && !item.getKey().equals("districtcode")
					&& !item.getKey().equals("types")) {
				if (!item.getKey().endsWith("rentarea")) {
					String value = item.getValue().toString();
					if (NumberUtils.isInteger(value)) {
						whereQuery.append(" and b." + item.getKey().toLowerCase() + " = " + Integer.parseInt(value));
					} else {
						if (!StringUtils.isNullOrEmpty(value)) {
							whereQuery.append(" and b." + item.getKey().toLowerCase() + " like '%" + value + "%'");
						}
					}
				} else {
					Integer fromRentarea = MapUtils.getObject(params, "fromrentarea", Integer.class);
					Integer toRentarea = MapUtils.getObject(params, "torentarea", Integer.class);
					if (fromRentarea != null || toRentarea != null) {
						whereQuery.append(" and EXISTS (select * from rentarea as ra WHERE ra.buildingid = b.id");
						if (fromRentarea != null) {
							whereQuery.append(" and ra.value >= " + fromRentarea);
						}
						if (toRentarea != null) {
							whereQuery.append(" and ra.value <= " + toRentarea);
						}
						whereQuery.append(")");
					}
				}
			}
		}

	}

	private void buildQueryWithJoin(Map<String, Object> params, List<String> types, StringBuilder whereQuery,
			StringBuilder joinQuery) {
		Long staffid = MapUtils.getObject(params, "staffid", Long.class);
		String districtCode = MapUtils.getObject(params, "districtcode", String.class);
		if (staffid != null) {
			joinQuery.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
			whereQuery.append(" and ab.staffid =" + staffid);
		}
		if (!StringUtils.isNullOrEmpty(districtCode)) {
			joinQuery.append(" inner join district as d on d.id = b.districtid");
			whereQuery.append(" and d.code ='" + districtCode + "'");
		}
		if (types != null && types.size() > 0) {
			joinQuery.append(" inner join buildingrenttype as b_rt on b.id=b_rt.buildingid")
					.append(" inner join renttype as rt on rt.id = b_rt.renttypeid");
			whereQuery.append(" and rt.code IN (");
			String sqlJoinTypes = types.stream().map(item -> "'" + item + "'").collect(Collectors.joining(","));
			whereQuery.append(sqlJoinTypes);
			whereQuery.append(")");
		}

	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		StringBuilder finalQuery = new StringBuilder();
		StringBuilder joinQuery = new StringBuilder("");
		StringBuilder whereQuery = new StringBuilder("");

		joinQuery.append(buildSqlJoining(builder));
		whereQuery.append(buildWhereCommonSql(builder)).append(builWhereSpecialSql(builder));
		finalQuery.append("select b.*").append("\nfrom building as b").append(joinQuery)
				.append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery).append("\n group by b.id");
		String sqlQuery = finalQuery.toString();
		Query query = entityManager.createNativeQuery(sqlQuery, BuildingEntity.class);
		return query.getResultList();
	}

	private StringBuilder buildSqlJoining(BuildingSearchBuilder builder) {
		StringBuilder sqlJoin = new StringBuilder("");
		if (builder.getStaffId() != null) {
			sqlJoin.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
		}
		if (builder.getTypes() != null && builder.getTypes().size() > 0) {
			sqlJoin.append(" inner join buildingrenttype as b_rt on b.id=b_rt.buildingid")
					.append(" inner join renttype as rt on rt.id = b_rt.renttypeid");
		}
		return sqlJoin;
	}

	private StringBuilder buildWhereCommonSql(BuildingSearchBuilder builder) {
		StringBuilder whereCommonSql = new StringBuilder("");
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName().toLowerCase();
				if (!fieldName.equals("staffid") && !fieldName.equals("districtcode") && !fieldName.equals("types")
						&& !fieldName.endsWith("rentarea") && !fieldName.endsWith("rentprice")) {
					Object objValue = field.get(builder);
					if (objValue != null) {
						if (field.getType().getName().equals("java.lang.String")) {
							whereCommonSql.append(" and b." + fieldName + " like '%" + objValue + "%'");
						} else if (field.getType().getName().equals("java.lang.Integer")) {
							whereCommonSql.append(" and b." + fieldName + " = " + objValue);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return whereCommonSql;
	}

	private StringBuilder builWhereSpecialSql(BuildingSearchBuilder builder) {
		StringBuilder whereSpecialSql = new StringBuilder("");
		if (builder.getStaffId() != null) {
			whereSpecialSql.append(" and ab.staffid =" + builder.getStaffId());
		}
		if (builder.getTypes() != null && builder.getTypes().size() > 0) {
			whereSpecialSql.append(" and rt.code IN (");
			String sqlJoinTypes = builder.getTypes().stream().map(item -> "'" + item + "'")
					.collect(Collectors.joining(","));
			whereSpecialSql.append(sqlJoinTypes);
			whereSpecialSql.append(")");
		}
		if (builder.getFromRentPrice() != null) {
			whereSpecialSql.append(" and b.rentprice >= " + builder.getFromRentPrice());
		}
		if (builder.getToRentPrice() != null) {
			whereSpecialSql.append(" and b.rentprice <= " + builder.getToRentPrice());
		}
		if (builder.getFromRentArea() != null || builder.getToRentArea() != null) {
			whereSpecialSql.append(" and EXISTS (select * from rentarea as ra WHERE ra.buildingid = b.id");
			if (builder.getFromRentArea() != null) {
				whereSpecialSql.append(" and ra.value >= " + builder.getFromRentArea());
			}
			if (builder.getToRentArea() != null) {
				whereSpecialSql.append(" and ra.value <= " + builder.getToRentArea());
			}
			whereSpecialSql.append(")");
		}
		return whereSpecialSql;
	}

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
