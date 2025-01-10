package com.banking.application.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.application.domain.exception.ExceedsMonthlyWithdrawalLimitException;
import com.banking.application.domain.exception.InvalidAmountException;

public class SavingAccountTest {

	SavingAccount savingAccount;
	Owner owner;
	Balance initialBalance;

	@BeforeEach
	void setUp() {
		owner = new Owner("Alexandre");
		initialBalance = new Balance(new Amount(new BigDecimal(1000)));
		savingAccount = new SavingAccount(initialBalance, owner);
	}

	@Test
	void should_create_new_saving_account_with_generated_id() {

		assertNotNull(savingAccount);
		assertNotNull(savingAccount.getAccountId());
		assertEquals(savingAccount.getOwner(), owner);
		assertEquals(savingAccount.getBalance(), initialBalance);

	}

	@Test
	void should_throw_exception_when_deposit_negative_amount() {

		Transaction deposit = new Transaction(new Amount(BigDecimal.valueOf(-200)), null, Operation.DEPOSIT);
		assertThrows(InvalidAmountException.class, () -> savingAccount.deposit(deposit));

	}

	@Test
	void should_add_amount_when_deposit_positive_amount() {
		BigDecimal initialBalance = savingAccount.getBalance().getAmount().value();
		Transaction depositTransaction = new Transaction(new Amount(new BigDecimal(100)), LocalDate.now(),
				Operation.DEPOSIT);
		savingAccount.deposit(depositTransaction);
		assertEquals(initialBalance.add(new BigDecimal(100)), savingAccount.getBalance().getAmount().value());
	}

	@Test
	void should_throw_ExceedsMonthlyWithdrawalLimitException_When_WithdrawalExceedsBalance() {
		Transaction withdrawal = new Transaction(new Amount(BigDecimal.valueOf(600)), LocalDate.now(),
				Operation.RETRIEVE);
		Transaction withdrawal2 = new Transaction(new Amount(BigDecimal.valueOf(600)), LocalDate.now(),
				Operation.RETRIEVE);
		savingAccount.withdrawal(withdrawal);
		assertThrows(ExceedsMonthlyWithdrawalLimitException.class, () -> savingAccount.withdrawal(withdrawal2));
	}

	@Test
	void should_add_monthly_Interest() {
		savingAccount.addMonthlyInterest();
		assertTrue(savingAccount.getBalance().getAmount().value().compareTo(new BigDecimal(1050)) == 0);
	}

}
