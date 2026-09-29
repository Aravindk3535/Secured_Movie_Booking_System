package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.dto.SeatRequestDTO;
import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.service.SeatServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {

    private final SeatServiceLayer seatService;

    public SeatController(SeatServiceLayer seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seat/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return  seatService.getSeatById(id);
    }

    @GetMapping("/seats")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PostMapping("/addSeats")
    public String createSeat(@RequestBody SeatRequestDTO seat) {
        return seatService.createSeat(seat);
    }

    @DeleteMapping("removeSeat/{id}")
    public String deleteSeat(@PathVariable Long id) {
        return seatService.deleteSeat(id);
    }

    @PutMapping("/editSeat/{id}")
    public Seat editSeat(@PathVariable Long id, @RequestBody SeatRequestDTO requestDTO) {
        return seatService.editSeat(id, requestDTO);
    }
}
