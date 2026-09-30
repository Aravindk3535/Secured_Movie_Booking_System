package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.dto.PaymentRequestDTO;
import com.ticket.bookingApplication.dto.PaymentResponseDTO;
import com.ticket.bookingApplication.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public PaymentResponseDTO createPayment(PaymentRequestDTO payment) {
        return paymentService.createPayment(payment);
    }
}
