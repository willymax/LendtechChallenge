package com.william.lendtech;

import com.github.javafaker.Faker;
import com.william.lendtech.transaction.Transaction;
import com.william.lendtech.transaction.TransactionRepository;
import com.william.lendtech.transaction.TransactionType;
import com.william.lendtech.user.User;
import com.william.lendtech.user.UserServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@Slf4j
public class LendtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendtechApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(TransactionRepository transactionRepository, UserServiceImplementation userServiceImplementation) {
		return (args) -> {
			Faker faker = new Faker();

			// creating a few transactions
			for (int i = 0; i < 20; i++) {
				userServiceImplementation.saveUser(new User(-1, faker.name().firstName(), faker.name().lastName(), faker.name().username(), "password"));
			}
			userServiceImplementation.saveUser(new User(-1, "William", "Makau", "willymax", "password"));

			List<User> allUsers = userServiceImplementation.findAll();

			Random random = new Random();
			for (int i = 0; i < 500; i++) {
				transactionRepository.save(new Transaction(null, (float) faker.number().randomDouble(2, 100, 10000), null, TransactionType.DEBIT, null, allUsers.get(random.nextInt(allUsers.size())), "Credit transaction"));
				transactionRepository.save(new Transaction(null, (float) faker.number().randomDouble(2, 100, 10000), null, TransactionType.CREDIT, allUsers.get(random.nextInt(allUsers.size())), null, "Credit transaction"));
			}

			transactionRepository.findAll().forEach(transaction -> {
				log.info(transaction.toString());
			});
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
