package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "transactionAPIOfAdmin")
@RequestMapping("/api/transaction")
public class TransactionAPI {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping()
    public List<TransactionDTO> getByCustomerid(@RequestParam(name = "customerid") Long customerid){
        return transactionService.findByCustomerId(customerid);
    }

    @PostMapping
    public  void addTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.save(transactionDTO);
    }
}
