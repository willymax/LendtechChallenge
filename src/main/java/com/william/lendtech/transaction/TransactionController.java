package com.william.lendtech.transaction;

import com.william.lendtech.security.dto.TransactionDto;
import com.william.lendtech.user.UserServiceImplementation;
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
public class TransactionController {
    private final TransactionServiceImplementation transactionServiceImplementation;
    private final UserServiceImplementation userServiceImplementation;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/transaction/save").toUriString());
        return ResponseEntity.created(uri).body(transactionServiceImplementation.saveTransaction(transaction));
    }

    @GetMapping("/transactions/recent")
    public ResponseEntity<List<TransactionDto>> getRecentTransactions() {

        return ResponseEntity.ok().body(transactionServiceImplementation.findRecentTransactions(userServiceImplementation.getLoggedInUser().getId()));
    }

    @GetMapping("/transactions/all")
    public List<Transaction> getPaginatedTransactions(@RequestParam(required=true) int pageNo,
                                                      @RequestParam(required=true) int pageSize) {

        return transactionServiceImplementation.findPaginated(pageNo, pageSize);
    }
}
