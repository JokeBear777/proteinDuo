package com.proteinduo.global.exception.exception;

public class PeriodNotSatisfiedException extends RuntimeException {
    public PeriodNotSatisfiedException(String message) {
        super(message);
    }

    public PeriodNotSatisfiedException(String message, Throwable cause) {
        super(message, cause);
    }

}
