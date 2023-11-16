package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;

import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchDTO buildingSearch,
                                     @RequestParam(value="page",required = false) Integer page,
                                     @RequestParam(value="pageSize",required = false) Integer pageSize){
        ModelAndView mav = new ModelAndView("admin/building/list");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isManager = authentication.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_MANAGER".equals(authority.getAuthority()));
        if (!isManager) {
            buildingSearch.setStaffId(((MyUserDetail) authentication.getPrincipal()).getId());
        }
        mav.addObject("modelSearch",buildingSearch);
        mav.addObject("districtmaps",buildingService.getDistricMaps());
        mav.addObject("typemaps",buildingService.getTypeMaps());
        mav.addObject("staffmaps",userService.getStaffMaps());


        if(pageSize == null){
            pageSize = 2;
        }
        if(page == null || page < 1){
            page = 1;
        }
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Page<BuildingDTO> objects = buildingService.findBuilding(buildingSearch,pageable);
            mav.addObject("buildings",objects.getContent());
            mav.addObject("currentPage",objects.getNumber()+1);
            mav.addObject("totalItems",objects.getTotalElements());
            mav.addObject("totalPages",objects.getTotalPages());
            mav.addObject("pageSize",pageSize);
            mav.addObject("page", page);
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
