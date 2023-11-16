package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.TransactionDTO;

import java.util.List;

public class TransactionResponseDTO {
    private String code;
    private String name;
    private List<TransactionDTO> transactions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
