package com.banking.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private Amount amount = new Amount(BigDecimal.ZERO);
	private LocalDate date;
	private Operation operation;

	public Transaction(Amount amount, LocalDate date, Operation operation) {
		this.amount = amount;
		this.date = date;
		this.operation = operation;
	}

	public Transaction getWithdrawalTransaction() {
		return new Transaction(new Amount(amount.getNegativeValue()), date, operation);

	}

	public Amount getBalanceAfterTransaction(Amount currentBalance) {
		return currentBalance.addAmount(amount);
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {

		return date;
	}

}
