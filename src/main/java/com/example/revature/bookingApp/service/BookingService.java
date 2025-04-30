package com.example.revature.bookingApp.service;


import com.example.revature.bookingApp.entity.Booking;
import com.example.revature.bookingApp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository =bookingRepository;
    }

    public List<Booking> getAllBookings(){
    return bookingRepository.findAll();
}

    public Booking createBooking(Booking booking) {
    return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingByI(Long id) {
    return bookingRepository.findById(id);
    }

    public void deleteBookingById(Long id) {
    bookingRepository.deleteById(id);
    }
}
