package com.banking.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.banking.application.domain.Account;
import com.banking.application.domain.Amount;
import com.banking.application.domain.Balance;
import com.banking.application.domain.CheckingAccount;
import com.banking.application.domain.Operation;
import com.banking.application.domain.Owner;
import com.banking.application.domain.SavingAccount;
import com.banking.application.domain.Transaction;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final Map<Long, Account> accounts = new HashMap<>();
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		printMenu();
		while (running) {

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1 -> createAccount(scanner);
			case 2 -> depositMoney(scanner);
			case 3 -> withdrawMoney(scanner);
			case 4 -> displayBalance(scanner);
			case 5 -> calculateInterest(scanner);
			case 6 -> {
				System.out.println("Goodbye!");
				running = false;
			}
			default -> System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void printMenu() {
		System.out.println("\nMenu:");
		System.out.println("1. Create an account");
		System.out.println("2. Deposit money");
		System.out.println("3. Withdraw money");
		System.out.println("4. Display balance");
		System.out.println("5. Calculate interest for a savings account");
		System.out.println("6. Quit");
	}

	private void createAccount(Scanner scanner) {
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Enter the initial balance: ");
		double balance = scanner.nextDouble();
		System.out.print("Choose the account type (1 for checking, 2 for savings): ");
		int accountType = scanner.nextInt();

		Account account;
		if (accountType == 1) {
			account = new CheckingAccount(new Balance(new Amount(BigDecimal.valueOf(balance))), new Owner(name));
		} else if (accountType == 2) {
			account = new SavingAccount(new Balance(new Amount(BigDecimal.valueOf(balance))), new Owner(name));
		} else {
			System.out.println("Invalid account type.");
			return;
		}

		accounts.put(account.getAccountId().getId(), account);

	}

	private void depositMoney(Scanner scanner) {
		System.out.print("Enter your account identifier: ");
		long id = scanner.nextLong();
		System.out.print("Enter the amount to deposit: ");
		double amount = scanner.nextDouble();

		Account account = accounts.get(id);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}
		try {
			account.deposit(
					new Transaction(new Amount(BigDecimal.valueOf(amount)), LocalDate.now(), Operation.DEPOSIT));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void withdrawMoney(Scanner scanner) {
		System.out.print("Enter your account identifier: ");
		long id = scanner.nextLong();
		System.out.print("Enter the amount to withdraw: ");
		double amount = scanner.nextDouble();

		Account account = accounts.get(id);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}

		try {
			account.withdrawal(
					new Transaction(new Amount(BigDecimal.valueOf(amount)), LocalDate.now(), Operation.RETRIEVE));
			System.out.println(amount + " euros have been withdrawn from your account.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void displayBalance(Scanner scanner) {
		System.out.print("Enter your account identifier: ");
		long id = scanner.nextLong();

		Account account = accounts.get(id);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}

		System.out.println("Your balance is " + account.getBalance().getAmount().value() + " euros.");
	}

	private void calculateInterest(Scanner scanner) {
		System.out.print("Enter your account identifier: ");
		long id = scanner.nextLong();

		Account account = accounts.get(id);
		if (account == null || !(account instanceof SavingAccount)) {
			System.out.println("Savings account not found.");
			return;
		}

		SavingAccount savingAccount = (SavingAccount) account;

		savingAccount.addMonthlyInterest();
	}
}
