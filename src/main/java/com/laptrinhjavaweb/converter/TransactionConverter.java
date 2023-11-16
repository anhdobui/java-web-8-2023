package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enumDefine.TransactionTypeEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public TransactionDTO convertToDto (TransactionEntity entity){
        TransactionDTO result = modelMapper.map(entity,TransactionDTO.class);
        result.setCustomerid(entity.getCustomer().getId());
        String typeName = !StringUtils.isNullOrEmpty(entity.getCode()) ? TransactionTypeEnum.valueOf(entity.getCode()).getName():"";
        result.setTypeName(typeName);
        return result;
    }

    public TransactionEntity convertToEntity(TransactionDTO transactionDTO) {
        Long customerid = transactionDTO.getCustomerid();
        CustomerEntity customerEntity =  customerRepository.findById(customerid).orElse(null);
        if(customerEntity != null){
            TransactionEntity result = modelMapper.map(transactionDTO,TransactionEntity.class);
            result.setCustomer(customerEntity);
            return result;
        }else{
            throw new NotFoundException(SystemConstant.USER_NOT_FOUND);
        }
    }
}
