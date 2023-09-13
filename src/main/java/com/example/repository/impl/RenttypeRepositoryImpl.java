package com.example.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.repository.RenttypeRepository;
import com.example.repository.entity.RenttypeEntity;
import com.example.utils.ConnectionUtils;

@Repository
public class RenttypeRepositoryImpl implements RenttypeRepository {

	@Override
	public List<RenttypeEntity> getByBuildingId(Long id) {
		List<RenttypeEntity> results = new ArrayList<>();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();) {
			String sqlQuery = "select rt.id,rt.code,rt.name from building as b inner join buildingrenttype as b_rt on b.id=b_rt.buildingid inner join renttype as rt on rt.id = b_rt.renttypeid where b.id = " + id;
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				RenttypeEntity renttypeEntity = new RenttypeEntity();
				renttypeEntity.setCode(rs.getString("code"));
				renttypeEntity.setName(rs.getString("name"));
				renttypeEntity.setId(rs.getLong("id"));
				results.add(renttypeEntity);
			}
		} catch(Exception e){
			
		}finally {
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
