package com.banking.application.domain;

public class Balance {

	private Amount amount;

	public Balance(Amount amount) {
		this.amount = amount;
	}

	public void add(Amount additionAmmount) {
		this.amount = amount.addAmount(additionAmmount);

	}

	public Amount getAmount() {
		return amount;
	}

}
