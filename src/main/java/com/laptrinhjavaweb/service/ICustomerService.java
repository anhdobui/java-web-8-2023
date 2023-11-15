package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.CustomerSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Page<CustomerDTO> findCustomer(CustomerSearchDTO customerSearchDTO, Pageable pageable);
    CustomerDTO getCustomer(Long id);
    Long totalItemFound(CustomerBuilder customerBuilder);

    long delete(List<Long> ids);

    CustomerDTO save(CustomerDTO newCustomer);

    void updateStaffOfCustomer(Long customerid, List<Long> staffIds);

    boolean updateCustomerStatus(Long customerid, boolean isDeleted);
}
