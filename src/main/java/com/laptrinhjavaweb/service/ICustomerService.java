package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Page<CustomerDTO> findCustomer(CustomerDTO customerDTO, Pageable pageable);

    Long totalItemFound(CustomerBuilder customerBuilder);

    long delete(List<Long> ids);
}
