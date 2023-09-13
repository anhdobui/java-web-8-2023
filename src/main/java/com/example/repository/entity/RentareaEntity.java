package com.example.repository.entity;

public class RentareaEntity extends BaseEntity{
	private Integer value;
	private Long buildingId;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	
}
