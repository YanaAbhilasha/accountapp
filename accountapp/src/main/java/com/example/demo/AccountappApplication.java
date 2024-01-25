package com.example.demo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class AccountappApplication {
	private final AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountappApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		// TODO Auto-generated method stub

		List<Account> result = Stream
				.of(new Account(UUID.randomUUID().toString(), "Sachin", "Mumbai", "sachin@email.com"),
						new Account(UUID.randomUUID().toString(), "Rahul", "Banglore", "rahul@email.com"),
						new Account(UUID.randomUUID().toString(), "Sourav", "Kolkata", "sourav@email.com"))
				.collect(Collectors.toList());
		for (Account a : result) {
			accountRepository.createAccount(a);
		}

	}

}