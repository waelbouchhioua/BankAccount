package com.banking.application.domain.exception;

public class ExceedsMonthlyWithdrawalLimitException extends RuntimeException {

    public ExceedsMonthlyWithdrawalLimitException(String message) {
        super(message);
    }

}
