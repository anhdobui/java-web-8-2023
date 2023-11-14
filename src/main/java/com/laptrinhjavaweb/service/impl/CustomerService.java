package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public Page<CustomerDTO> findCustomer(CustomerDTO customerDTO, Pageable pageable) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerBuilder customerBuilder = convertToCustomerBuilder(customerDTO);
        List<CustomerEntity> customerEntities = customerRepository.findByCustomer(customerBuilder,pageable);
        Long totalItem = totalItemFound(customerBuilder);
        customerEntities.forEach(item -> {
            CustomerDTO itemDTO = customerConverter.convertToDto(item);
            customerDTOS.add(itemDTO);
        });
        Page<CustomerDTO> result = new PageImpl<>(customerDTOS,pageable,totalItem);
        return result;
    }

    @Override
    public Long totalItemFound(CustomerBuilder customerBuilder) {
        return (Long) customerRepository.countCustomerFound(customerBuilder);
    }

    @Override
    @Transactional
    public long delete(List<Long> ids) {
        if(ids.size() >0){
            Long count = customerRepository.countByIdIn(ids);
             if(count != ids.size()){
                throw new NotFoundException(SystemConstant.CUSTOMER_NOT_FOUND);
            }
            return customerRepository.deleteByIdIn(ids).longValue();
        }
        return 0;
    }

    private CustomerBuilder convertToCustomerBuilder(CustomerDTO customerDTO) {
        CustomerBuilder result = new CustomerBuilder.Builder()
                                .setPhone(customerDTO.getPhone())
                                .setEmail(customerDTO.getEmail())
                                .setFullName(customerDTO.getFullName())
                                .setStaffId(customerDTO.getStaffId())
                                .build();
        return result;
    }
}
