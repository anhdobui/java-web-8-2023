package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    Long countBuildingFound(BuildingSearchBuilder builderSearch);
    List<BuildingEntity> findBuilding(BuildingSearchBuilder builderSearch, Pageable pageable);
}
