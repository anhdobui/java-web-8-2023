package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserDTO;
import com.example.service.UserService;

@RestController
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/user")
	public List<UserDTO> findAll(@RequestParam( name = "roleCode",required = false) String roleCode){
		userService.findByRole(roleCode);
		return null;
	}
}
