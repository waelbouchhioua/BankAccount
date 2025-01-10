package com.banking.application.domain;

import java.math.BigDecimal;

public record Amount(BigDecimal value) {

	public boolean isNegativeAmount() {
		return value.signum() <= 0;
	}

	public Amount addAmount(Amount additionalAmount) {
		return new Amount(this.value.add(additionalAmount.value()));
	}

	public BigDecimal getNegativeValue() {
		return value.negate();
	}

}
