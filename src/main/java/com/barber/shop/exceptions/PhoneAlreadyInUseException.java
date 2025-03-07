package com.barber.shop.exceptions;

public class PhoneAlreadyInUseException extends RuntimeException {
    public PhoneAlreadyInUseException(String message) {
        super(message);
    }
}
