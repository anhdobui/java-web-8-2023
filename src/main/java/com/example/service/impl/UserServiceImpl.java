package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.UserDTO;
import com.example.repository.custom.RoleRepositoryCustom;
import com.example.repository.custom.UserRepositoryCustom;
import com.example.repository.entity.RoleEntity;
import com.example.repository.entity.UserEntity;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RoleRepositoryCustom roleRepository;
	
	@Autowired
	private UserRepositoryCustom userRepository;
	
	@Override
	public List<UserDTO> findByRole(String cole) {
		RoleEntity roleEntity = roleRepository.findByCode(cole);
//		List<UserEntity> userEntities = roleEntity.getUsers();
		List<UserEntity> userEntities = userRepository.findByRole(cole);
		userEntities.stream().map(UserEntity::getFullName).forEach(System.out::println);
		return null;
	}
	
}
