package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.*;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.enumDefine.TransactionTypeEnum;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerSearchDTO customerSearchDTO, @RequestParam(value="page",required = false) Integer page,
                                     @RequestParam(value="pageSize",required = false) Integer pageSize){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isManager = authentication.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_MANAGER".equals(authority.getAuthority()));
        if (!isManager) {
            customerSearchDTO.setStaffId(((MyUserDetail) authentication.getPrincipal()).getId());
        }
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
        mav.addObject("staffmaps",userService.getStaffMaps());
        CustomerDTO oldCustomer = new CustomerDTO();
        String mode = id != null && customerService.getCustomer(id) != null ? "update":"add";
        List<TransactionResponseDTO> typeTransactions = new ArrayList<>();
        if(mode.equals("update")){
            oldCustomer = customerService.getCustomer(id);
            typeTransactions = transactionService.getTransactionByCustomerId(id);

        }
        mav.addObject("mode",mode);
        mav.addObject("typeTransaction",typeTransactions);
        mav.addObject("customerEdit",oldCustomer);
        return mav;
    }
}
