package com.banking.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.banking.application.domain.exception.ExceedsMonthlyWithdrawalLimitException;

public class SavingAccount extends Account {
	private BigDecimal totalMonthlyWithdrawals = BigDecimal.ZERO;
	private LocalDate lastWithdrawalDate = LocalDate.now();
	private static final BigDecimal MAX_WITHDRAWAL = BigDecimal.valueOf(1000.0);
	private static final double INTEREST_RATE = 0.05;

	public SavingAccount(Balance balance, Owner owner) {
		super(balance, owner);
	}

	@Override
	public void withdrawal(final Transaction transaction) {
		LocalDate transactionDate = transaction.getDate();
		Amount transactionAmount = transaction.getAmount();
		if (transactionDate != null && !lastWithdrawalDate.getMonth().equals(transactionDate.getMonth())
				|| lastWithdrawalDate.getYear() != transactionDate.getYear()) {
			totalMonthlyWithdrawals = BigDecimal.ZERO;
		}

		totalMonthlyWithdrawals = totalMonthlyWithdrawals.add(transactionAmount.value());
		if (totalMonthlyWithdrawals.compareTo(MAX_WITHDRAWAL) > 0) {
			throw new ExceedsMonthlyWithdrawalLimitException(
					"Exceeds maximum allowed withdrawal of " + MAX_WITHDRAWAL + " euros per month.");
		}
		super.withdrawal(transaction);
	}

	public BigDecimal calculateInterest() {
		BigDecimal currentBalance = balance.getAmount().value();
		return currentBalance.multiply(BigDecimal.valueOf(INTEREST_RATE));
	}

	public void addMonthlyInterest() {
		BigDecimal interest = calculateInterest();
		balance.add(new Amount(interest));
		System.out.println("Interest of " + interest + " euros has been added to the balance.");
	}
}
