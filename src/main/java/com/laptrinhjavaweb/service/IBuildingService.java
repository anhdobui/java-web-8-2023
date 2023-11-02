package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.enumDefine.TypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
	List<BuildingDTO> findAll();
	Page<BuildingDTO> findBuilding(BuildingSearchDTO buildingserch, Pageable pageable);
	Map<DistrictEnum,String> getDistricMaps();
	Map<TypeEnum,String> getTypeMaps();
	BuildingDTO getBuilding(Long id);
	void save(BuildingDTO buildingDTO);
	void delete(List<Long> ids);
	void updateStaffOfBuilding(Long buildingId,List<Long> staffIds);
	long totalItemFound(BuildingSearchBuilder buildingSearchBuilder);

}
