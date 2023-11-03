package com.laptrinhjavaweb.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

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
    private String districtCode;
    @Column(name="numberofbasement")
    private Integer numberOfBasement;
    @Column(name="direction")
    private String direction;
    @Column(name="level")
    private String level;
    @Column(name="floorarea")
    private Integer floorArea;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
    @Column(name="rentprice")
    private Integer rentPrice;
    @Column(name="rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name="type")
    private String type;

    @Column(name="carfee")
    private String carFee;
    @Column(name="motofee")
    private String motoFee;
    @Column(name="overtimefee")
    private String overtimeFee;
    @Column(name="waterfee")
    private String waterFee;
    @Column(name="electricityfee")
    private String electricityFee;
    @Column(name="deposit")
    private String deposit;
    @Column(name="payment")
    private String payment;
    @Column(name="renttime")
    private String renttime;
    @Column(name="note")
    private String note;
    @Column(name="linkofbuilding")
    private String linkOfBuilding;
    @Column(name="map")
    private String map;
    @Column(name="avatar")
    private String avatar;
    @Column(name="brokeragetee",precision = 13, scale = 2)
    private BigDecimal brokeragetee;

    @OneToMany(mappedBy = "building",cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private List<RentAreaEntity> rentAreas;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs;

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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public void setMotoFee(String motoFee) {
        this.motoFee = motoFee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRenttime() {
        return renttime;
    }

    public void setRenttime(String renttime) {
        this.renttime = renttime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkOfBuilding() {
        return linkOfBuilding;
    }

    public void setLinkOfBuilding(String linkOfBuilding) {
        this.linkOfBuilding = linkOfBuilding;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getBrokeragetee() {
        return brokeragetee;
    }

    public void setBrokeragetee(BigDecimal brokeragetee) {
        this.brokeragetee = brokeragetee;
    }
}