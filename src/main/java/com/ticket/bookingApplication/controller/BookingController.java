package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.dto.BookingRequestDTO;
import com.ticket.bookingApplication.dto.BookingResponseDTO;
import com.ticket.bookingApplication.service.BookingServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private final BookingServiceLayer bookingService;

    public BookingController(BookingServiceLayer bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/createBooking")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingService.createBooking(bookingRequestDTO);
    }

    @GetMapping("/allBookings")
    public List<BookingResponseDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/cancelBooking/{id}")
    public String deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }

    @PutMapping("/editBooking/{id}")
    public BookingResponseDTO editBooking(@PathVariable Long id, @RequestBody BookingRequestDTO requestDTO) {
        return bookingService.editBooking(id, requestDTO);
    }
}
