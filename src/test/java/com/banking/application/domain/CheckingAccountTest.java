package com.banking.application.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.banking.application.domain.exception.InsufficientBalanceException;

public class CheckingAccountTest {
	private Balance balance = new Balance(new Amount(BigDecimal.TEN));
	private Owner owner = new Owner("Alice");
	CheckingAccount checkingAccount = new CheckingAccount(balance, owner);

	@Test
	void should_ThrowException_When_WithdrawalExceedsSavingsLimit() {
		Transaction withdrawal = new Transaction(new Amount(BigDecimal.valueOf(1200)), LocalDate.now(),
				Operation.RETRIEVE);
		assertThrows(InsufficientBalanceException.class, () -> checkingAccount.withdrawal(withdrawal));
	}
}
