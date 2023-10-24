package com.laptrinhjavaweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "buildingid")
  private BuildingEntity building;

  @ManyToOne
  @JoinColumn(name = "staffid")
  private UserEntity staff;

  public BuildingEntity getBuilding() {
    return building;
  }

  public void setBuilding(BuildingEntity building) {
    this.building = building;
  }

  public UserEntity getStaff() {
    return staff;
  }

  public void setStaff(UserEntity staff) {
    this.staff = staff;
  }
}
