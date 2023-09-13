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
		Integer fromRentarea = (Integer) params.get("fromrentarea");
		Integer toRentarea = (Integer) params.get("torentarea");
		Long staffid = (Long) params.get("staffid");
		if(fromRentarea != null || toRentarea != null) {
			joinQuery.append(" inner join rentarea as ra on ra.buildingid=b.id");
			if(fromRentarea != null) {
				whereQuery.append(" and ra.value >= "+fromRentarea);
			}
			if(toRentarea != null) {
				whereQuery.append(" and ra.value <= "+toRentarea);
			}
		}
		if(staffid != null) {
			joinQuery.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
			whereQuery.append(" and ab.staffid ="+staffid);
		}
		if(types != null && types.size() > 0) {
			joinQuery.append(" inner join buildingrenttype as b_rt on b.id=b_rt.buildingid")
					.append(" inner join renttype as rt on rt.id = b_rt.renttypeid");
			whereQuery.append(" and");
			List<String> builWhereTypes = new ArrayList<>();
			for(String code : types) {
				builWhereTypes.add(" rt.code ='"+code+"'");
			}
			whereQuery.append(String.join(" or", builWhereTypes));
		}
	}


	private void buildQueryWithoutJoin(Map<String, Object> params, StringBuilder whereQuery) {
		String name = (String)params.get("name");
		Integer floorarea = (Integer) params.get("floorarea");
		Integer districtid = (Integer) params.get("districtid");
		String ward = (String)params.get("ward");
		String street = (String)params.get("street");
		Integer numberofbasement = (Integer) params.get("numberofbasement");
		String direction = (String)params.get("direction");
		String level = (String)params.get("level");
		String managerName = (String)params.get("managername");
		String managerPhone = (String)params.get("managerphone");
		Integer fromRentprice = (Integer) params.get("fromrentprice");
		Integer toRentprice = (Integer) params.get("torentprice");
		if(!StringUtils.isNullOrEmpty(name)) {
			whereQuery.append(" and b.name like '%"+name+"%'");
		}
		if(floorarea != null) {
			whereQuery.append(" and b.floorarea = "+floorarea);
		}
		if(districtid != null) {
			whereQuery.append(" and b.districtid = "+districtid);
		}
		if(!StringUtils.isNullOrEmpty(ward)){
			whereQuery.append(" and b.ward like '%"+ward+"%'");
		}
		if(!StringUtils.isNullOrEmpty(street)){
			whereQuery.append(" and b.street like '%"+street+"%'");
		}
		if(numberofbasement != null) {
			whereQuery.append(" and b.numberofbasement = "+numberofbasement+"");
		}
		if(!StringUtils.isNullOrEmpty(direction)) {
			whereQuery.append(" and b.direction like '%"+direction+"%'");
		}
		if(!StringUtils.isNullOrEmpty(level)) {
			whereQuery.append(" and b.level like '%"+level+"%'");
		}
		if(!StringUtils.isNullOrEmpty(managerName)) {
			whereQuery.append(" and b.managerName like '%"+managerName+"%'");
		}
		if(!StringUtils.isNullOrEmpty(managerPhone)) {
			whereQuery.append(" and b.managerPhone like '%"+managerPhone+"%'");
		}
		if(fromRentprice != null) {
			whereQuery.append(" and b.rentprice >= "+fromRentprice);
		}
		if(toRentprice != null) {
			whereQuery.append(" and b.rentprice <= "+toRentprice);
		}
		
	}
	
}
