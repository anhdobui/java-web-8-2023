package com.example.api;

import java.util.HashMap;
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
	public  List<BuildingDTO> findBuilding(@RequestParam( name = "name",required = false) String name,
												 @RequestParam(name = "floorarea",required = false) Integer floorArea,
												 @RequestParam(name = "districtid",required = false) Long districtId,
												 @RequestParam(name = "ward",required = false) String ward,
												 @RequestParam(name = "street",required = false) String street,
												 @RequestParam(name = "numberofbasement" ,required = false) Integer numberOfBasement,
												 @RequestParam(name = "direction" ,required = false) String direction,
												 @RequestParam(name = "level" ,required = false) String level,
												 @RequestParam(name = "fromrentarea" ,required = false) Integer fromRentArea,
												 @RequestParam(name = "torentarea" ,required = false) Integer toRentArea,
												 @RequestParam(name = "fromrentprice" ,required = false) Integer fromRentPrice,
												 @RequestParam(name = "torentprice" ,required = false) Integer toRentPrice,
												 @RequestParam(name = "managername" ,required = false) String managerName,
												 @RequestParam(name = "managerphone" ,required = false) String managerPhone,
												 @RequestParam(name = "staffid" ,required = false) Long staffId,
												 @RequestParam(name = "types" ,required = false) List<String> types
												) {
		Map<String, Object> paramsSearch = new HashMap<>();
		paramsSearch.put("name", name);
		paramsSearch.put("floorarea", floorArea);
		paramsSearch.put("districtid", districtId);
		paramsSearch.put("ward", ward);
		paramsSearch.put("street", street);
		paramsSearch.put("numberofbasement", numberOfBasement);
		paramsSearch.put("direction", direction);
		paramsSearch.put("level", level);
		paramsSearch.put("fromrentarea", fromRentArea);
		paramsSearch.put("torentarea", toRentArea);
		paramsSearch.put("fromrentprice", fromRentPrice);
		paramsSearch.put("torentprice", toRentPrice);
		paramsSearch.put("managername", managerName);
		paramsSearch.put("managerphone", managerPhone);
		paramsSearch.put("staffid", staffId);
		List<BuildingDTO> results = buildingService.findBuilding(paramsSearch, types);
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
