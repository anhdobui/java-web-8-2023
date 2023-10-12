package com.example.repository.custom.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.example.repository.custom.BuildingJdbc;
import com.example.repository.custom.BuildingRepositoryCustom;
import com.example.repository.entity.BuildingEntity;

@Repository
@PropertySource("classpath:application.properties")
public class BuildingJdbcImpl implements BuildingJdbc {
//public class BuildingJdbcImpl implements BuildingRepository {
	/*
	 * private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	 * private final String USER = "root";
	 * private final String PASS = "root";
	 */
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String password;
	
	@Override
	public List<BuildingEntity> findAll() {
		List<BuildingEntity> results = new ArrayList<>();
		String QUERY = "SELECT * FROM building";
		try(Connection conn = DriverManager.getConnection(dbUrl, user, password);
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
	
}
