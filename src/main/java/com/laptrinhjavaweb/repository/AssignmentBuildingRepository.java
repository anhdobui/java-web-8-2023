package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentBuildingRepository  extends JpaRepository<AssignmentBuildingEntity, Long> {
    Optional<AssignmentBuildingEntity> findOByBuildingAndStaff(BuildingEntity buildingEntity, UserEntity staff);
}
