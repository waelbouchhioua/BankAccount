package com.banking.application.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AmountTest {

	@Test
	void should_Addition_Amount_When_AddAmount() {
		Amount amount1 = new Amount(BigDecimal.valueOf(100));
		Amount amount2 = new Amount(BigDecimal.valueOf(200));
		Amount result = amount1.addAmount(amount2);

		assertEquals(BigDecimal.valueOf(300), result.value());
	}
}
