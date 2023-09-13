package com.example.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.repository.RentareaRepository;
import com.example.repository.entity.RentareaEntity;
import com.example.utils.ConnectionUtils;

@Repository
public class RentareaRepositoryImpl implements RentareaRepository{

	@Override
	public List<RentareaEntity> getByBuildingId(Long id) {
		List<RentareaEntity> results = new ArrayList<>();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();) {
			String sqlQuery = "select id,value,buildingid from rentarea where buildingid =" + id;
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				RentareaEntity rentareaEntity = new RentareaEntity();
				rentareaEntity.setId(rs.getLong("id"));
				rentareaEntity.setValue(rs.getInt("value"));
				rentareaEntity.setBuildingId(id);
				results.add(rentareaEntity);
			}
		} catch(Exception e) {
			
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return results;
	}

}
