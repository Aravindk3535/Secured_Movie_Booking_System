package com.ticket.bookingApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String openHome() {
        return "Welcome to Movie Ticket Page";
    }

}
