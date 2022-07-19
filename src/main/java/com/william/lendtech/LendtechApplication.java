package com.william.lendtech;

import com.william.lendtech.transaction.Transaction;
import com.william.lendtech.transaction.TransactionRepository;
import com.william.lendtech.transaction.TransactionType;
import com.william.lendtech.user.User;
import com.william.lendtech.user.UserServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class LendtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendtechApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(TransactionRepository transactionRepository, UserServiceImplementation userServiceImplementation) {
		return (args) -> {
			// creating a few transactions
			userServiceImplementation.saveUser(new User(-1, "William", "Makau", "willymax", "password"));
			userServiceImplementation.saveUser(new User(-1, "Peter", "Mark", "peter", "password"));
			User willymax = userServiceImplementation.getUser("willymax");
			User peter = userServiceImplementation.getUser("peter");
			transactionRepository.save(new Transaction(null, null, TransactionType.CREDIT, willymax.getId(), null, "Credit transaction"));
			transactionRepository.save(new Transaction(null, null, TransactionType.DEBIT, null, peter.getId(), "Debit transaction"));


			transactionRepository.findAll().forEach(transaction -> {
				log.info(transaction.toString());
			});
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
