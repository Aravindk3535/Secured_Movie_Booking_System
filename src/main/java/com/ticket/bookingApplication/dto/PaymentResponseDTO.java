package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponseDTO(Long payemntId, Long bookingId, double amount, PaymentStatus status, String transactionId, LocalDateTime createdAt, String paymentMethod) {
}
