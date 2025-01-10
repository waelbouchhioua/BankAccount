package com.banking.application.domain;

import com.banking.application.domain.exception.InsufficientBalanceException;

public class CheckingAccount extends Account {

	public CheckingAccount(Balance balance, Owner owner) {
		super(balance, owner);

	}

	@Override
	public void withdrawal(final Transaction transaction) {

		if (this.balance.getAmount().value().compareTo(transaction.getAmount().value()) < 0) {
			throw new InsufficientBalanceException("Insufficient balance.");
		}
		super.withdrawal(transaction);
	}

}
