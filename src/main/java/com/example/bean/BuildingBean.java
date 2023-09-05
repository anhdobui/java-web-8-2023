package com.example.bean;

public class BuildingBean {
	private Long id;
	private String name;
	private Integer floorArea;
	private Long[] buildingIds;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long[] getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(Long[] buildingIds) {
		this.buildingIds = buildingIds;
	}
	
	
}
