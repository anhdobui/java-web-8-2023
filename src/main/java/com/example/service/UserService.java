package com.example.service;

import java.util.List;

import com.example.model.UserDTO;

public interface UserService {
	List<UserDTO> findByRole(String cole);
}
