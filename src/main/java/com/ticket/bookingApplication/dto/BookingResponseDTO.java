package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.enums.BookingStatus;
import com.ticket.bookingApplication.model.Show;

import java.time.LocalDateTime;
import java.util.List;

public record BookingResponseDTO(
        Long bookingId,
        Long userId,
        BookingStatus status,
        Double totalAmount,
        List<Long> seats,
        Show show,
        LocalDateTime localDateTime
) {
}
