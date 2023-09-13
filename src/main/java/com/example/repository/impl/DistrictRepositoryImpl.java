package com.example.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.example.repository.DistrictRepository;
import com.example.repository.entity.DistrictEntity;
import com.example.utils.ConnectionUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity getById(Long id) {
		DistrictEntity result = new DistrictEntity();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();) {
			String sqlQuery = "select id,code,name from district where id = " + id;
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				result.setId(id);
				result.setCode(rs.getString("code"));
				result.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return result;
	}

}
