package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.model.Theatre;
import com.ticket.bookingApplication.service.TheatreServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheatreController {

    private final TheatreServiceLayer theatreService;

    public TheatreController(TheatreServiceLayer theatreService) {
        this.theatreService = theatreService;
    }

    @RequestMapping("/movies")
    public String theatres() {
        return "Select your desired theatre to book tickets";
    }

    @GetMapping("/alltheatres")
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }

    @GetMapping("/theatre/{id}")
    public Theatre getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreByID(id);
    }

    @PostMapping("/addTheatres")
    public String addTheatres(@RequestBody Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    @DeleteMapping("/deleteTheatre/{id}")
    public String deleteTheatre(@PathVariable Long id) {
        return theatreService.deleteTheatre(id);
    }

    @PutMapping("/editTheatre/{id}")
    public Theatre editTheatre(@PathVariable Long id, @RequestBody Theatre theatre) {
        return theatreService.editTheatre(id, theatre);
    }
}
