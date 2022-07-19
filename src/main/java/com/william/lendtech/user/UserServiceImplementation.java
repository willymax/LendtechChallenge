package com.william.lendtech.user;

import com.william.lendtech.transaction.Transaction;
import com.william.lendtech.transaction.TransactionServiceImplementation;
import com.william.lendtech.transaction.TransactionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */

@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TransactionServiceImplementation  transactionServiceImplementation;


    @Override
    public User saveUser(User user) {
        log.info("Saving new user to the database {} {}", user.getFirstName(), user.getLastName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserProfile() {
        User currentUser = getLoggedInUser();
        return currentUser;
    }


    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * NOTE: the implementation BELOW in not scalable for calculating user balance,
     * As the amount of transactions grows, the transaction will take long
     *
     */
    @Override
    public float getCurrentBalance() {
        // THIS IMPLEMENTATION IS NOT SCALABLE
        List<Transaction> userTransactions = transactionServiceImplementation.allUserTransactions(getLoggedInUser().getId());
        double totalDebit = userTransactions.stream().filter(o -> o.getTransactionType().equals(TransactionType.DEBIT)).mapToDouble(Transaction::getAmount).sum();
        double totalCredit = userTransactions.stream().filter(o -> o.getTransactionType().equals(TransactionType.CREDIT)).mapToDouble(Transaction::getAmount).sum();

        return (float) (totalDebit - totalCredit);
    }



    public User getLoggedInUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User  not found in the database");
            throw new UsernameNotFoundException("User  not found in the database");
        } else {
            log.error("User found in the database {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
