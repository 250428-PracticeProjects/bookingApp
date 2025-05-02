package com.example.revature.bookingApp.controller;


import com.example.revature.bookingApp.entity.Booking;
import com.example.revature.bookingApp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Booking>getAllBookings() {
    return bookingService.getAllBookings();
    }

    //PROVIDES WITH METHOD LEVEL SECURITY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
    return  bookingService.getBookingByI(id)
            .orElseThrow(()-> new RuntimeException("Booking not found"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
                    booking.setPrice((Double) value);
            }
        });
        Booking updateBooking =bookingService.createBooking(booking);
        return ResponseEntity.ok(updateBooking);
    }

}
