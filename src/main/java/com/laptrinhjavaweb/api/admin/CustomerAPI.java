package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @DeleteMapping
    public long deleteCustomers(@RequestBody List<Long> ids){
       return customerService.delete(ids);
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO newCustomer){
        return customerService.save(newCustomer);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO newCustomer){
        return customerService.save(newCustomer);
    }

    @PostMapping("/{customerid}/assignment")
    public void assignmentBuilding(@PathVariable(name = "customerid") Long customerid,@RequestBody List<Long> staffIds){
        customerService.updateStaffOfCustomer(customerid,staffIds);
    }

    @PutMapping("/{customerid}")
    public boolean updateCustomerStatus(@PathVariable(name = "customerid") Long customerid,@RequestBody boolean isDeleted){
        return customerService.updateCustomerStatus(customerid,isDeleted);
    }
}
