package com.example.revature.bookingApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice  //Handles exceptions globally
public class GlobalExeptionHandler {

    @ExceptionHandler(BookingAPIException.class)
    public ResponseEntity<ErrorDetails> handleBookingApiException(BookingAPIException exception,
                                                                  WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false) // only returns URL not other details
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
