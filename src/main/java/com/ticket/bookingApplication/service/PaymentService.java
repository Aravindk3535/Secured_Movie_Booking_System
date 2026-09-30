package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.PaymentRequestDTO;
import com.ticket.bookingApplication.dto.PaymentResponseDTO;
import com.ticket.bookingApplication.enums.BookingStatus;
import com.ticket.bookingApplication.enums.PaymentStatus;
import com.ticket.bookingApplication.enums.SeatStatus;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Booking;
import com.ticket.bookingApplication.model.Payment;
import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.repository.BookingRepository;
import com.ticket.bookingApplication.repository.PaymentRepository;
import com.ticket.bookingApplication.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final BookingRepository bookingRepo;
    private final PaymentRepository paymentRepo;
    private final SeatRepository seatRepo;

    public PaymentService(BookingRepository bookingRepo, PaymentRepository paymentRepo, SeatRepository seatRepo) {
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;
        this.seatRepo = seatRepo;
    }

    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Booking booking = bookingRepo.findById(paymentRequestDTO.bookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking is not found"));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentMethod("UPI");
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("abc123");
        payment.setCreatedAt(LocalDateTime.now());

        Payment savePayment = paymentRepo.save(payment);
        if (savePayment.getStatus().equals(PaymentStatus.SUCCESS)) {
            updateSeats(booking, SeatStatus.BOOKED);
            booking.setStatus(BookingStatus.COMPLETED);
        } else {
            updateSeats(booking, SeatStatus.AVAILABLE);
            booking.setStatus(BookingStatus.CANCELLED);
        }

        return new PaymentResponseDTO(
                savePayment.getPaymentId(),
                savePayment.getBooking().getBookingId(),
                savePayment.getAmount(),
                savePayment.getStatus(),
                savePayment.getTransactionId(),
                savePayment.getCreatedAt(),
                savePayment.getPaymentMethod()
        );
    }

    private void updateSeats(Booking booking, SeatStatus status) {
        List<Seat> seats = seatRepo.findAllBySeatIdIn(booking.getSeats());
        for(Seat seat : seats) {
            seat.setStatus(status);
        }
    }
}
