package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enumDefine.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<TransactionDTO> findByCustomerId(Long id) {
        List<TransactionDTO> result = new ArrayList<>();
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(id);
        transactionEntities.forEach(item -> {
            TransactionDTO transactionDTO = transactionConverter.convertToDto(item);
            result.add(transactionDTO);
        });
        return result;
    }

    @Override
    public void save(TransactionDTO transactionDTO) {
        TransactionEntity newTransaction = transactionConverter.convertToEntity(transactionDTO);
        transactionRepository.save(newTransaction);
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByCustomerId(Long customerid) {
        List<TransactionResponseDTO> result = new ArrayList<>();

        for (TransactionTypeEnum transactionTypeEnum : TransactionTypeEnum.values()) {
            TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setCode(transactionTypeEnum.toString());
            transactionResponseDTO.setName(transactionTypeEnum.getName());
            List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_IdAndCode(customerid,transactionTypeEnum.toString());
            List<TransactionDTO> transactionDTOs = transactionEntities.stream().map(transactionConverter::convertToDto).collect(Collectors.toList());
            transactionResponseDTO.setTransactions(transactionDTOs);
            result.add(transactionResponseDTO);
        }
        return result;
    }
}
