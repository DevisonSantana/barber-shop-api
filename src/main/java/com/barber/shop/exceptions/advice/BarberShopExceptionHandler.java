package com.barber.shop.exceptions.advice;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.barber.shop.controllers.response.ProblemResponse;
import com.barber.shop.exceptions.EmailAlreadyInUseException;
import com.barber.shop.exceptions.NotFoundException;
import com.barber.shop.exceptions.PhoneAlreadyInUseException;
import com.barber.shop.exceptions.ScheduleAlreadyInUseException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFound(final NotFoundException ex, final WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<?> handlerEmailAlreadyInUse(final EmailAlreadyInUseException ex, final WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PhoneAlreadyInUseException.class)
    public ResponseEntity<?> handlerPhonelAlreadyInUse(final PhoneAlreadyInUseException ex, final WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ScheduleAlreadyInUseException.class)
    public ResponseEntity<?> handlerScheduleAlreadyInUse(final ScheduleAlreadyInUseException ex,
            final WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerUncaught(final Exception ex, final WebRequest request) {
        log.error("handlerUncaught: ", ex);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }
}
