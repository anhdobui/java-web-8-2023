package com.example.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.repository.UserRepository;
import com.example.repository.entity.UserEntity;
import com.example.utils.ConnectionUtils;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Override
	public List<UserEntity> getByBuildingId(Long id) {
		List<UserEntity> results = new ArrayList<>();
		ResultSet rs = null;
		try(Connection conn = ConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();) {
			String sqlQuery = "select u.id,u.fullname,u.username, u.password,u.email,u.phone from building as b inner join assignmentbuilding as ab on ab.buildingid=b.id inner join user as u on u.id=ab.staffid where b.id = " + id;
			rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getLong("id"));
				userEntity.setFullname(rs.getString("fullname"));
				userEntity.setPassword(rs.getString("password"));
				userEntity.setUsername(rs.getString("username"));
				userEntity.setEmail(rs.getString("email"));
				userEntity.setPhone(rs.getString("phone"));
				results.add(userEntity);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
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
