package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.enumDefine.TransactionTypeEnum;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
    List<TransactionDTO> findByCustomerId(Long id);

    void save(TransactionDTO transactionDTO);

    List<TransactionResponseDTO> getTransactionByCustomerId(Long customerid);
}
