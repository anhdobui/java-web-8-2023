package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.CustomerSearchDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public Page<CustomerDTO> findCustomer(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerBuilder customerBuilder = convertToCustomerBuilder(customerSearchDTO);
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
    public CustomerDTO getCustomer(Long id) {
        CustomerDTO result = null;
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity != null) {
            result = customerConverter.convertToDto(customerEntity);
        }
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

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO newCustomer) {
        Long customerId = newCustomer.getId();
        CustomerEntity customerEntity = customerConverter.convertToEntity(newCustomer);
        if (customerId != null) { // update
            CustomerEntity foundCustomer = customerRepository.findById(customerId)
                    .orElse(null);
            if(foundCustomer != null){
                customerEntity.setStaffs(foundCustomer.getStaffs());
                customerEntity.setTransactions(foundCustomer.getTransactions());
            }
        }
        customerEntity = customerRepository.save(customerEntity);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public void updateStaffOfCustomer(Long customerid, List<Long> staffIds) {
        CustomerEntity customerEntity = customerRepository.findById(customerid).orElse(null);
        if (customerEntity != null) {
            List<UserEntity> newStaffs = userRepository.findByIdIn(staffIds);
            if(staffIds.size() != newStaffs.size()){
                throw new NotFoundException(SystemConstant.USER_NOT_FOUND);
            }
            customerEntity.setStaffs(newStaffs);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    @Transactional
    public boolean updateCustomerStatus(Long customerid, boolean isDeleted) {
        CustomerEntity customerEntity = customerRepository.findById(customerid).orElse(null);
        if(customerEntity != null){
            customerEntity.setDeleted(isDeleted);
            customerRepository.save(customerEntity);
            return true;
        }
        return false;
    }

    private CustomerBuilder convertToCustomerBuilder(CustomerSearchDTO customerSearchDTO) {
        CustomerBuilder result = new CustomerBuilder.Builder()
                                .setPhone(customerSearchDTO.getPhone())
                                .setEmail(customerSearchDTO.getEmail())
                                .setFullName(customerSearchDTO.getFullName())
                                .setStaffId(customerSearchDTO.getStaffId())
                                .build();
        return result;
    }
}
