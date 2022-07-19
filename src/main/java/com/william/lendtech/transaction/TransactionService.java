package com.william.lendtech.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}
