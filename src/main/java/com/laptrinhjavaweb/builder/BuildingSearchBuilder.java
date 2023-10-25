package com.laptrinhjavaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private String ward;
    private String street;
    private Long staffId;
    private Integer floorArea;
    private Integer numberOfBasement;
    private String districtCode;
    private String direction;
    private String level;
    private String managerName;
    private String managerPhone;
    private Integer costRentFrom;
    private Integer costRentTo;
    private Integer rentAreaFrom;
    private Integer rentAreaTo;
    private List<String> types;

    public String getName() {
        return name;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Long getStaffId() {
        return staffId;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDistrictCode() {
        return districtCode;
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

    public Integer getCostRentFrom() {
        return costRentFrom;
    }

    public Integer getCostRentTo() {
        return costRentTo;
    }

    public Integer getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Integer getRentAreaTo() {
        return rentAreaTo;
    }

    public List<String> getTypes() {
        return types;
    }

    private BuildingSearchBuilder(Builder build) {
        this.name = build.name;
        this.districtCode = build.districtCode;
        this.floorArea = build.floorArea;
        this.street = build.street;
        this.ward = build.ward;
        this.direction = build.direction;
        this.level = build.level;
        this.managerName = build.managerName;
        this.managerPhone = build.managerPhone;
        this.numberOfBasement = build.numberOfBasement;
        this.costRentFrom = build.costRentFrom;
        this.costRentTo = build.costRentTo;
        this.rentAreaFrom = build.rentAreaFrom;
        this.rentAreaTo = build.rentAreaTo;
        this.types = build.types;
        this.staffId = build.staffId;
    }

    public static class Builder{
        private String name;
        private String ward;
        private String street;
        private Long staffId;
        private Integer floorArea;
        private Integer numberOfBasement;
        private String districtCode;
        private String direction;
        private String level;
        private String managerName;
        private String managerPhone;
        private Integer costRentFrom;
        private Integer costRentTo;
        private Integer rentAreaFrom;
        private Integer rentAreaTo;
        private List<String> types;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
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

        public Builder setCostRentFrom(Integer costRentFrom) {
            this.costRentFrom = costRentFrom;
            return this;
        }

        public Builder setCostRentTo(Integer costRentTo) {
            this.costRentTo = costRentTo;
            return this;
        }

        public Builder setRentAreaFrom (Integer rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Integer rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
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
