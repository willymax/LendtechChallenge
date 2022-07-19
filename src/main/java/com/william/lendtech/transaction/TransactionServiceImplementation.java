package com.william.lendtech.transaction;

import com.william.lendtech.security.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionServiceImplementation implements  TransactionService{
    private final TransactionRepository transactionRepository;



    private final ModelMapper modelMapper;

    @Override
    public List<Transaction> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Transaction> pagedResult = transactionRepository.findAll(paging);

        return pagedResult.toList();
    }

    @Override
    public List<TransactionDto> findRecentTransactions(Long userId) {
        Pageable paging = PageRequest.of(1, 10);

        List<Transaction> transactions = transactionRepository.findRecentTransactions(userId);

        return transactions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> allUserTransactions(Long userId) {
        return transactionRepository.findUserTransactions(userId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    private TransactionDto convertToDto(Transaction transaction){
        return modelMapper.map(transaction, TransactionDto.class);
    }

}
