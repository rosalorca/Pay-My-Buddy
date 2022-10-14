package com.openclassrooms;

import com.openclassrooms.model.Account;
import com.openclassrooms.model.Operation;
import com.openclassrooms.model.User;
import com.openclassrooms.service.AccountService;
import com.openclassrooms.service.OperationService;
import com.openclassrooms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Iterable<User> users = userService.getUsers();
		users.forEach(user -> System.out.println(user.getUserId()));
		Optional<User> optUser = userService.getUsersById(1);
		User userId1 = optUser.get();
		userId1.getName();

		Iterable<Operation> operations = operationService.getOperations();
		operations.forEach(operation -> operation.getTransferId());
		Optional<Operation> optOperation = operationService.getOperationsById(1);
		Operation operasyonId1  = optOperation.get();
		operasyonId1.getUser1_Id();

		Iterable<Account> accounts= accountService.getAccounts();
		accounts.forEach(account -> account.getBalance());
		Optional<Account> optAccount = accountService.getAccountsById(1);
		Account accountId1 = optAccount.get();
		accountId1.getBalance();


	}
}
