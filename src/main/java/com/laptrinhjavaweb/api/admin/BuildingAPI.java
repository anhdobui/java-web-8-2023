package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;

import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
        return newBuilding;
    }
    @DeleteMapping
    public void deleteBuildings(@RequestBody List<Long> ids){
        buildingService.delete(ids);
    }
    @PutMapping
    public void updateBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
    }
    @GetMapping("/{buildingid}/staffs")
    public ResponseDTO loadStaff(@PathVariable(name = "buildingid") Long buildingId){
        ResponseDTO result = new ResponseDTO();
        List<StaffResponseDTO> staffs = userService.getStaffsEnable(buildingId);
        result.setMessage("success");
        result.setData(staffs);
        return result;
    }
    @PostMapping("/{buildingid}/assignment")
    public void assignmentBuilding(@PathVariable(name = "buildingid") Long buildingId,@RequestBody List<Long> staffIds){
        buildingService.updateStaffOfBuilding(buildingId,staffIds);
    }
}
