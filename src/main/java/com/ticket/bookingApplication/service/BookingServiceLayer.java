package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.BookingRequestDTO;
import com.ticket.bookingApplication.dto.BookingResponseDTO;
import com.ticket.bookingApplication.dto.ModelConvertor;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Booking;
import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.model.Show;
import com.ticket.bookingApplication.repository.BookingRepository;
import com.ticket.bookingApplication.repository.SeatRepository;
import com.ticket.bookingApplication.repository.ShowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingServiceLayer {

    private final BookingRepository bookingRepo;
    private final SeatRepository seatRepo;
    private final ShowRepository showRepo;

    public BookingServiceLayer(BookingRepository bookingRepo, SeatRepository seatRepo, ShowRepository showRepo) {
        this.bookingRepo = bookingRepo;
        this.seatRepo = seatRepo;
        this.showRepo = showRepo;
    }

    public BookingResponseDTO getBookingById(Long id) {
        Optional<Booking> bookingDetails = bookingRepo.findById(id);
        if (bookingDetails.isEmpty()) {
            throw new ResourceNotFoundException("Booking has not found with this id " + id );
        }
        BookingResponseDTO responseDTO = ModelConvertor.bookingResponseDTO(bookingDetails.get());
        return responseDTO;
    }

    @Transactional
    public String createBooking(BookingRequestDTO bookingRequestDTO) {
        Show show = showRepo.findById(bookingRequestDTO.getShow().getShowId())
                .orElseThrow(()-> new ResourceNotFoundException("No show found"));
        List<Seat> seats = seatRepo.findAllBySeatIdIn(bookingRequestDTO.getSeatId());

        for(Seat seat : seats) {
            if (seat.isBooked()) {
                throw new RuntimeException("Seat already booked: " + seat.getSeatNumber());
            }
            seat.setBooked(true);
        }
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setSeats(bookingRequestDTO.getSeatId());
        booking.setTotalAmount(booking.getTotalAmount());
        booking.setStatus("Booking is Successful");
        bookingRepo.save(booking);
        return "Booking has completed successfully";
    }

    public List<BookingResponseDTO> getAllBookings() {
        List<BookingResponseDTO> allBookingDetails = new ArrayList<>();
        List<Booking> bookings = bookingRepo.findAll();
        for (Booking booking : bookings) {
            BookingResponseDTO response = ModelConvertor.bookingResponseDTO(booking);
            allBookingDetails.add(response);
        }
        if (!allBookingDetails.isEmpty()) {
            return allBookingDetails;
        }
        throw new ResourceNotFoundException("No Bookings found, Book tickets to view");
    }

    public String deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        if (booking.isEmpty()) {
            throw new ResourceNotFoundException("No booking found with this id " + id);
        }
        bookingRepo.deleteById(id);
        return "This booking has been successfully deleted";
    }

    public BookingResponseDTO editBooking(Long id, BookingRequestDTO bookingRequestDTO) {
        Optional<Booking> bookingDetails = bookingRepo.findById(id);
        if (bookingDetails.isEmpty()) {
            throw new ResourceNotFoundException("No booking found with this id " + id);
        }
        Booking booking = new Booking();
        booking.updateBookingDetails(bookingRequestDTO);
        BookingResponseDTO response = ModelConvertor.bookingResponseDTO(booking);
        return response;
    }
}
