package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerRepositoryCustom {
    Long countCustomerFound(CustomerBuilder customerBuilder);

    List<CustomerEntity> findByCustomer(CustomerBuilder customerBuilder, Pageable pageable);
}
