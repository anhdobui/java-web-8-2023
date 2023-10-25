package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.enumDefine.DistrictEnum;
import com.laptrinhjavaweb.enumDefine.TypeEnum;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
	List<BuildingDTO> findAll();
	List<BuildingDTO> findBuilding(BuildingSearchDTO buildingserch);
	Map<DistrictEnum,String> getDistricMaps();
	Map<TypeEnum,String> getTypeMaps();
	void save(BuildingDTO buildingDTO);

}
