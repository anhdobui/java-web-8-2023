package com.example.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AssimentBuildingDTO;
import com.example.model.BuildingDTO;
import com.example.model.response.BuildingSearchResponse;
import com.example.service.BuildingService;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingApi {
	@Autowired 
	private BuildingService buildingService;
	
	@GetMapping
	public  List<BuildingDTO> findBuilding(@RequestParam(required = false) Map<String, Object> params,
												 @RequestParam(name = "types" ,required = false) List<String> types
												) {
		List<BuildingDTO> results = buildingService.findBuilding(params, types);
		return results;
	}
	@GetMapping(value = "/{buildingId}")
	public  BuildingSearchResponse getBuilding(@PathVariable("buildingId") Long buildingId) {
		System.out.println(10/0);
		return null;
	}
	
	@PostMapping
	public  BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		
		return newBuilding;
	}
	
	
	@PutMapping
	public  BuildingApi updateBuilding(@RequestBody BuildingDTO updateBuilding) {
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
	public  void assignmentBuildingBean(@RequestBody AssimentBuildingDTO assignmentBuildingBean) {
		System.out.println("hello");
	}
	
	
}
