package com.banking.application.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class BalanceTest {
	@Test
	void should_test_When_AddBalance() {
		Balance balance = new Balance(new Amount(BigDecimal.valueOf(500)));
		balance.add(new Amount(BigDecimal.valueOf(200)));

		assertEquals(BigDecimal.valueOf(700), balance.getAmount().value());
	}
}
