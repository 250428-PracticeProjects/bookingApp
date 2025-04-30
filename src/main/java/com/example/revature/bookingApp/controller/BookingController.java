package com.example.revature.bookingApp.controller;


import com.example.revature.bookingApp.entity.Booking;
import com.example.revature.bookingApp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking>getAllBookings() {
    return bookingService.getAllBookings();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
    return  bookingService.getBookingByI(id)
            .orElseThrow(()-> new RuntimeException("Booking not found"));
    }

    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
    Booking booking = bookingService.getBookingByI(id)
            .orElseThrow(()-> new RuntimeException("Booking not found"));

        booking.setCustomerName(bookingDetails.getCustomerName());
        booking.setServiceName(bookingDetails.getServiceName());
        booking.setBookingTime(bookingDetails.getBookingTime());
        booking.setStatus(bookingDetails.getStatus());
        booking.setPrice(bookingDetails.getPrice());

    return bookingService.createBooking(booking);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Booking> partialUpdateBooking(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Booking booking = bookingService.getBookingByI(id)
                .orElseThrow(()-> new RuntimeException("Booking not Found"));

        updates.forEach((key, value)-> {
            switch (key) {
                case "customerName":
                    booking.setCustomerName((String) value);
                    break;
                case "serviceName":
                    booking.setServiceName((String) value);
                    break;
                case "bookingTime":
                    booking.setBookingTime((String) value);
                    break;
                case "status":
                    booking.setStatus((String) value);
                    break;
                case "price":
                    booking.setPrice((float) value);
            }
        });
        Booking updateBooking =bookingService.createBooking(booking);
        return ResponseEntity.ok(updateBooking);
    }

}
