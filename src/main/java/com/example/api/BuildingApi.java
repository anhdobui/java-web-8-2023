package com.example.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.BuildingBean;

@Controller
public class BuildingApi {
	@RequestMapping(value = "/api/building",method = RequestMethod.GET)
	public @ResponseBody List<BuildingApi> getBuilding(@RequestParam( name = "name") String name,@RequestParam(name = "numberofbasement" ,required = false) Integer numberofbasement) {
		System.out.println(name);
		System.out.println(numberofbasement);
		return null;
	}
	@RequestMapping(value = "/api/building/{buildingId}",method = RequestMethod.GET)
	public @ResponseBody List<BuildingApi> getBuilding(@PathVariable("buildingId") Long buildingId) {
		System.out.println(buildingId);
		return null;
	}
	
	@RequestMapping(value = "/api/building",method = RequestMethod.POST)
	public @ResponseBody BuildingApi createBuilding(@RequestBody BuildingBean newBuilding) {
		System.out.println(newBuilding.getName());
		return null;
	}
	@RequestMapping(value = "/api/building",method = RequestMethod.PUT)
	public @ResponseBody BuildingApi updateBuilding(@RequestBody BuildingBean updateBuilding) {
		System.out.println(updateBuilding.getId());
		System.out.println(updateBuilding.getName());
		return null;
	}
	@RequestMapping(value = "/api/building",method = RequestMethod.DELETE)
	public @ResponseBody BuildingApi deleteBuilding(@RequestBody Long[] ids) {
		for(Long id:ids) {
			System.out.println(id);
		}
		return null;
	}
	
}
