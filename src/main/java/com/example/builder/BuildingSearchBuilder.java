package com.example.builder;

import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private String district;
	private Integer floorArea;
	private String street;
	private String ward;
	private String direction;
	private String level;
	private String managerName;
	private String managerPhone;
	private Integer numberOfBasement;
	private Integer fromRentPrice;
	private Integer toRentPrice;
	private Integer fromRentArea;
	private Integer toRentArea;
	private List<String> types;
	private Long staffId;
	
	public String getName() {
		return name;
	}
	public String getDistrict() {
		return district;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public String getStreet() {
		return street;
	}
	public String getWard() {
		return ward;
	}
	public String getDirection() {
		return direction;
	}
	public String getLevel() {
		return level;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public Integer getFromRentPrice() {
		return fromRentPrice;
	}
	public Integer getToRentPrice() {
		return toRentPrice;
	}
	public Integer getFromRentArea() {
		return fromRentArea;
	}
	public Integer getToRentArea() {
		return toRentArea;
	}
	public List<String> getTypes() {
		return types;
	}
	public Long getStaffId() {
		return staffId;
	}
	
	
	
	private BuildingSearchBuilder(Builder build) {
		this.name = build.name;
		this.district = build.district;
		this.floorArea = build.floorArea;
		this.street = build.street;
		this.ward = build.ward;
		this.direction = build.direction;
		this.level = build.level;
		this.managerName = build.managerName;
		this.managerPhone = build.managerPhone;
		this.numberOfBasement = build.numberOfBasement;
		this.fromRentPrice = build.fromRentPrice;
		this.toRentPrice = build.toRentPrice;
		this.fromRentArea = build.fromRentArea;
		this.toRentArea = build.toRentArea;
		this.types = build.types;
		this.staffId = build.staffId;
	}



	public static class Builder{
		private String name;
		private String district;
		private Integer floorArea;
		private String street;
		private String ward;
		private String direction;
		private String level;
		private String managerName;
		private String managerPhone;
		private Integer numberOfBasement;
		private Integer fromRentPrice;
		private Integer toRentPrice;
		private Integer fromRentArea;
		private Integer toRentArea;
		private List<String> types;
		private Long staffId;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setFromRentPrice(Integer fromRentPrice) {
			this.fromRentPrice = fromRentPrice;
			return this;
		}

		public Builder setToRentPrice(Integer toRentPrice) {
			this.toRentPrice = toRentPrice;
			return this;
		}

		public Builder setFromRentArea(Integer fromRentArea) {
			this.fromRentArea = fromRentArea;
			return this;
		}

		public Builder setToRentArea(Integer toRentArea) {
			this.toRentArea = toRentArea;
			return this;
		}

		public Builder setTypes(List<String> types) {
			this.types = types;
			return this;
		}

		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
	
}
