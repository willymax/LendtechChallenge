package com.william.lendtech.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController
{
    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/transaction/save").toUriString());
        return ResponseEntity.created(uri).body(transactionService.saveTransaction(transaction));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getRecentTransactions() {
        return ResponseEntity.ok().body(transactionService.getAll());
    }
}
