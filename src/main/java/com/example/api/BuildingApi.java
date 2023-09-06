package com.example.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.AssimentBuildingBean;
import com.example.bean.BuildingBean;
import com.example.customexception.FieldRequiredException;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingApi {
	@GetMapping
	public  List<BuildingApi> getBuilding(@RequestParam( name = "name") String name,@RequestParam(name = "numberofbasement" ,required = false) Integer numberofbasement) {
		System.out.println(name);
		System.out.println(numberofbasement);
		return null;
	}
	@GetMapping(value = "/{buildingId}")
	public  List<BuildingApi> getBuilding(@PathVariable("buildingId") Long buildingId) {
		System.out.println(buildingId);
		return null;
	}
	
	@PostMapping
	public  BuildingBean createBuilding(@RequestBody BuildingBean newBuilding) {
		validateData(newBuilding);
		return newBuilding;
	}
	private void validateData(BuildingBean newBuilding) {
		if(newBuilding.getName() == null || newBuilding.getName().equals("") || newBuilding.getNumberOfBasement() == null) {
			throw new FieldRequiredException("name or numberofbasement is null");
		}
		
	}
	@PutMapping
	public  BuildingApi updateBuilding(@RequestBody BuildingBean updateBuilding) {
		System.out.println(updateBuilding.getId());
		System.out.println(updateBuilding.getName());
		return null;
	}
	@DeleteMapping
	public  BuildingApi deleteBuilding(@RequestBody Long[] ids) {
		for(Long id:ids) {
			System.out.println(id);
		}
		return null;
	}
	
	@PostMapping("/assignment")
	public  void assignmentBuildingBean(@RequestBody AssimentBuildingBean assignmentBuildingBean) {
		System.out.println("hello");
	}
	
	
}
