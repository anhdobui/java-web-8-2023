package com.example.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.constant.SystemConstant;
import com.example.repository.BuildingRepository;
import com.example.repository.entity.BuildingEntity;
import com.example.utils.ConnectionUtils;
import com.example.utils.MapUtils;
import com.example.utils.NumberUtils;
import com.example.utils.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	@Override
	public List<BuildingEntity> findAll() {
		List<BuildingEntity> results = new ArrayList<>();
		String QUERY = "SELECT * FROM building";
		try(Connection conn = ConnectionUtils.getConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(QUERY);
		      ) {		      
		         while(rs.next()){
		        	 BuildingEntity buildingEntity = new BuildingEntity();
		        	 buildingEntity.setName(rs.getString("name"));
		        	 buildingEntity.setStreet(rs.getString("street"));
		        	 buildingEntity.setDistrictId(rs.getLong("districtid"));
		        	 buildingEntity.setWard(rs.getString("ward"));
		        	 results.add(buildingEntity);
		         }
		         return results;
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		return null;
	}


	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> types) {
		List<BuildingEntity> results = new ArrayList<>();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtils.getConnection();
		    Statement stmt = conn.createStatement();) {
			StringBuilder finalQuery = new StringBuilder();
			StringBuilder joinQuery = new StringBuilder();
			StringBuilder whereQuery = new StringBuilder();
			
			finalQuery.append("select b.id, b.name, b.floorarea, b.districtid, b.street, b.ward, b.numberofbasement, b.direction, b.level, b.managerName, b.managerPhone, b.rentprice")
						.append("\nfrom building as b");
			buildQueryWithJoin(params,types,whereQuery,joinQuery);
			buildQueryWithoutJoin(params,whereQuery);
			finalQuery.append(joinQuery)
					.append(SystemConstant.ONE_EQUAL_ONE)
					.append(whereQuery)
					.append("\n group by b.id");
			String sqlQuery = finalQuery.toString();
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()){
	        	 BuildingEntity buildingEntity = new BuildingEntity();
	        	 buildingEntity.setName(rs.getString("name"));
	        	 buildingEntity.setStreet(rs.getString("street"));
	        	 buildingEntity.setDistrictId(rs.getLong("districtid"));
	        	 buildingEntity.setWard(rs.getString("ward"));
	        	 buildingEntity.setFloorArea(rs.getInt("floorarea"));
	        	 buildingEntity.setId(rs.getLong("id"));
	        	 buildingEntity.setDirection(rs.getString("direction"));
	        	 buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
	        	 buildingEntity.setLevel(rs.getString("level"));
	        	 buildingEntity.setManagerName(rs.getString("managerName"));
	        	 buildingEntity.setManagerPhone(rs.getString("managerphone"));
	        	 buildingEntity.setRentprice(rs.getInt("rentprice"));
	        	 results.add(buildingEntity);
	         }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return results;
	}


	private void buildQueryWithJoin(Map<String,Object> params,List<String> types,StringBuilder whereQuery,StringBuilder joinQuery) {
		Long staffid = MapUtils.getObject(params, "staffid", Long.class);
		String districtCode = MapUtils.getObject(params, "districtcode", String.class);
		if(staffid != null) {
			joinQuery.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
			whereQuery.append(" and ab.staffid ="+staffid);
		}
		if(!StringUtils.isNullOrEmpty(districtCode)) {
			joinQuery.append(" inner join district as d on d.id = b.districtid");
			whereQuery.append(" and d.code ='" + districtCode + "'");
		}
		if(types != null && types.size() > 0) {
			joinQuery.append(" inner join buildingrenttype as b_rt on b.id=b_rt.buildingid")
					.append(" inner join renttype as rt on rt.id = b_rt.renttypeid");
			whereQuery.append(" and rt.code IN (");
			List<String> listStringType = new ArrayList<>();
			for(String code : types) {
				listStringType.add("'"+code+"'");
			}
			whereQuery.append(String.join(",", listStringType));
			whereQuery.append(")");
		}
	}


	private void buildQueryWithoutJoin(Map<String, Object> params, StringBuilder whereQuery) {
		for(Map.Entry<String, Object> item : params.entrySet()) {
			if(!item.getKey().equals("staffid") && !item.getKey().equals("districtcode")) {
				if(!item.getKey().endsWith("rentarea")) {
					String value = item.getValue().toString();
					if(NumberUtils.isInteger(value)) {
						whereQuery.append(" and b."+item.getKey().toLowerCase() +" = "+Integer.parseInt(value));
					}else {
						if(!StringUtils.isNullOrEmpty(value)) {
							whereQuery.append(" and b." + item.getKey().toLowerCase() + " like '%" + value + "%'");
						}
					}
				}else {
					Integer fromRentarea = MapUtils.getObject(params, "fromrentarea", Integer.class);
					Integer toRentarea = MapUtils.getObject(params, "torentarea", Integer.class);
					if(fromRentarea != null || toRentarea != null) {
						whereQuery.append(" and EXISTS (select * from rentarea as ra WHERE ra.buildingid = b.id");
						if(fromRentarea != null) {
							whereQuery.append(" and ra.value >= "+fromRentarea);
						}
						if(toRentarea != null) {
							whereQuery.append(" and ra.value <= "+toRentarea);
						}
						whereQuery.append(")");
					}
				}
			}
		}
	}
	
}
