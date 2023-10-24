package com.laptrinhjavaweb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name="name")
    private String name;
    @Column(name="street")
    private String street;
    @Column(name="ward")
    private  String ward;
    @Column(name="district")
    private String district;
    @Column(name="numberofbasement")
    private Integer numberOfBasement;
    @Column(name="direction")
    private String direction;
    @Column(name="level")
    private String level;
    @Column(name="floorarea")
    private Integer floorArea;
    @Column(name="rentprice")
    private Integer rentPrice;
    @Column(name="rentpricedescription")
    private String rentPriceDescription;
    @Column(name="type")
    private String type;

    @OneToMany(mappedBy = "building")
    private List<RentAreaEntity> rentAreas;

    @OneToMany(mappedBy = "building")
    private List<AssignmentBuildingEntity> assignmentBuildings;

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AssignmentBuildingEntity> getAssignmentBuildings() {
        return assignmentBuildings;
    }

    public void setAssignmentBuildings(
        List<AssignmentBuildingEntity> assignmentBuildings) {
        this.assignmentBuildings = assignmentBuildings;
    }

}
