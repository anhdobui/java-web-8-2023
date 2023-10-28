package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchDTO buildingSearch){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch",buildingSearch);
        mav.addObject("buildings",buildingService.findBuilding(buildingSearch));
        mav.addObject("districtmaps",buildingService.getDistricMaps());
        mav.addObject("typemaps",buildingService.getTypeMaps());
        mav.addObject("staffmaps",userService.getStaffMaps());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@RequestParam(name = "id",required = false) Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO oldBuilding = new BuildingDTO();
        String mode = id != null && buildingService.getBuilding(id) != null ? "update":"add";
        if(mode.equals("update")){
            oldBuilding = buildingService.getBuilding(id);
        }
        mav.addObject("mode",mode);
        mav.addObject("buildingEdit",oldBuilding);
        mav.addObject("districtmaps",buildingService.getDistricMaps());
        mav.addObject("typemaps",buildingService.getTypeMaps());
        return mav;
    }
}
