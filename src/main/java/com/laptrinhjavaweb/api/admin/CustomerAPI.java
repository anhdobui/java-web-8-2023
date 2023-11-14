package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
