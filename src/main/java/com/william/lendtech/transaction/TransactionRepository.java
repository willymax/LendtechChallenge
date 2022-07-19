package com.william.lendtech.transaction;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByDescription(String description);

    Transaction findById(long id);
}
