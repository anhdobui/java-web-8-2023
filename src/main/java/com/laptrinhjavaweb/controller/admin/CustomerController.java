package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.CustomerSearchDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerSearchDTO customerSearchDTO, @RequestParam(value="page",required = false) Integer page,
                                     @RequestParam(value="pageSize",required = false) Integer pageSize){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch",customerSearchDTO);
        mav.addObject("staffmaps",userService.getStaffMaps());
        if(pageSize == null){
            pageSize = 2;
        }
        if(page == null || page < 1){
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<CustomerDTO> objects = customerService.findCustomer(customerSearchDTO,pageable);
        mav.addObject("customers",objects.getContent());
        mav.addObject("currentPage",objects.getNumber()+1);
        mav.addObject("totalItems",objects.getTotalElements());
        mav.addObject("totalPages",objects.getTotalPages());
        mav.addObject("pageSize",pageSize);
        mav.addObject("page", page);
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@RequestParam(name = "id",required = false) Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO oldCustomer = new CustomerDTO();
        String mode = id != null && customerService.getCustomer(id) != null ? "update":"add";
        if(mode.equals("update")){
            oldCustomer = customerService.getCustomer(id);
        }
        mav.addObject("mode",mode);
        mav.addObject("customerEdit",oldCustomer);
        return mav;
    }
}
