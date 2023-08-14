package io.upschool.exception;

public class InvalidCreditCardNumberException extends RuntimeException {
    public InvalidCreditCardNumberException(String message) {
        super(message);
    }
}
