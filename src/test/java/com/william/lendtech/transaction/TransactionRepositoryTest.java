package com.william.lendtech.transaction;

import com.william.lendtech.user.User;
import com.william.lendtech.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * The tests are not EXHAUSTIVE
     */

    /**
     * Missing Web Layer tests
     */

    @Test
    public void testFindUserTransactions() {
        User creditUser = new User(-1, "William", "Makau", "willymax", "password");
        User debitUser = new User(-1, "Peter", "Eric", "peter", "password");
        userRepository.save(creditUser);
        userRepository.save(debitUser);
        User creditUserFromDb = userRepository.findByUsername(creditUser.getUsername());
        assertEquals(creditUser.getUsername(), creditUserFromDb.getUsername());
        User debitUserFromDb = userRepository.findByUsername(debitUser.getUsername());
        assertEquals(debitUser.getUsername(), debitUserFromDb.getUsername());
        Transaction transaction = new Transaction(null, 100.00f, null, creditUserFromDb, debitUserFromDb, "description");
        transactionRepository.save(transaction);

        List<Transaction> transactions = transactionRepository.findUserTransactions(debitUserFromDb.getId());
        assertEquals(1, transactions.size());

        assertThat(transactions).extracting(Transaction::getDescription).containsOnly("description");
    }
}