package com.banking.application.domain;

import com.banking.application.domain.exception.InvalidAmountException;

public abstract class Account {

	private AccountId accountId;
	protected Balance balance;
	private Owner owner;

	public Account(Balance balance, Owner owner) {
		this.accountId = new AccountId();
		this.balance = balance;
		this.owner = owner;
		System.out.println("Savings account created successfully. Account identifier:" + accountId.getId());
	}

	public Account(AccountId accountId, Balance balance, String ownerName) {
		this.accountId = accountId;
		this.balance = balance;
		this.owner = new Owner(ownerName);

	}

	public AccountId getAccountId() {
		return accountId;
	}

	public void setAccountId(AccountId accountId) {
		this.accountId = accountId;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void withdrawal(final Transaction transaction) {

		addTransaction(transaction.getWithdrawalTransaction());
	}

	public void deposit(final Transaction transaction) {
		if (transaction.getAmount().isNegativeAmount()) {
			throw new InvalidAmountException("Deposit amount must be positive.");
		}
		addTransaction(transaction);
		System.out.println(transaction.getAmount().value() + " euros have been deposited into your account.");
	}

	private void addTransaction(Transaction transaction) {
		this.balance.add(transaction.getAmount());

	}

	public void displayBalance(Account account) {

		if (account == null) {
			System.out.println("Account not found.");
			return;
		}
		System.out.println("Your balance is " + account.getBalance().getAmount().value() + " euros.");
	}

}
