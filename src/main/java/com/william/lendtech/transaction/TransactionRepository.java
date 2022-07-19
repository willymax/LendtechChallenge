package com.william.lendtech.transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
     List<Transaction> findByCreditUser(Long id, Pageable pageable);


     @Query(value = "SELECT * FROM transaction WHERE credit_user_id = ?1 OR debit_user_id = ?1 ORDER BY transaction_date_time desc limit 10", nativeQuery = true)
     List<Transaction> findRecentTransactions(Long userId);

     @Query(value = "SELECT * FROM transaction WHERE credit_user_id = ?1 OR debit_user_id = ?1 ORDER BY transaction_date_time desc", nativeQuery = true)
     List<Transaction> findUserTransactions(Long userId);

}
