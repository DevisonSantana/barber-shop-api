package com.barber.shop.exceptions;

public class ScheduleAlreadyInUseException extends RuntimeException {
    public ScheduleAlreadyInUseException(String message) {
        super(message);
    }
}
