package com.william.lendtech.transaction;

import com.william.lendtech.security.dto.TransactionDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
public interface TransactionService {
    List<TransactionDto> findPaginated(int pageNo, int pageSize, Long userId);
    List<TransactionDto> findRecentTransactions(Long userId);
    List<Transaction> allUserTransactions(Long userId);

    Transaction saveTransaction(Transaction transaction);
}
