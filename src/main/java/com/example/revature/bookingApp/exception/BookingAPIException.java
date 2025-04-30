package com.example.revature.bookingApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BookingAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
