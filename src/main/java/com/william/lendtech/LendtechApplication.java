package com.william.lendtech;

import com.github.javafaker.Faker;
import com.william.lendtech.transaction.Transaction;
import com.william.lendtech.transaction.TransactionRepository;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class LendtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(LendtechApplication.class, args);
	}
	@Bean
	public CommandLineRunner initializeData(TransactionRepository transactionRepository, UserServiceImplementation userServiceImplementation) {
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
				List<Long> indices = allUsers.stream().map(User::getId).flatMap(Stream::of)    // -> Stream<String>
						.collect(Collectors.toList());
				Long firstUserId = indices.get(random.nextInt(indices.size()));
				indices.remove(firstUserId);
				Long secondUserId = indices.get(random.nextInt(indices.size()));
				transactionRepository.save(new Transaction(null, (float) faker.number().numberBetween(100, 10000), null, allUsers.stream().filter(item -> item.getId() == firstUserId).findFirst().get(), allUsers.stream().filter(item -> item.getId() == secondUserId).findFirst().get(), "transaction description"));
			}
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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
