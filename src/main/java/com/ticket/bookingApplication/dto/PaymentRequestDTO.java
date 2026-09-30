package com.ticket.bookingApplication.dto;

public record PaymentRequestDTO(Long bookingId, String paymentMethod) {
}
