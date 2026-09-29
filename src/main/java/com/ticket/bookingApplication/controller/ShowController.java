package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.dto.ShowRequestDTO;
import com.ticket.bookingApplication.dto.ShowResponseDTO;
import com.ticket.bookingApplication.model.Show;
import com.ticket.bookingApplication.service.ShowServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    private final ShowServiceLayer showService;

    public ShowController(ShowServiceLayer showService) {
        this.showService = showService;
    }

    @GetMapping("/showDetails/{id}")
    public ShowResponseDTO getShowDetailsById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @GetMapping("/allShows")
    public List<ShowResponseDTO> getAllShows(){
        return showService.getAllShows();
    }

    @PostMapping("/addShow")
    public String addShow(@RequestBody ShowRequestDTO show) {
        return showService.createShow(show);
    }

    @DeleteMapping("/deleteShow/{id}")
    public String deleteShow(@PathVariable Long id) {
        return showService.deleteShow(id);
    }

    @PutMapping("/editShow/{id}")
    public ShowResponseDTO editShow(@PathVariable Long id,
                                    @RequestBody ShowRequestDTO show) {
        return showService.editShow(id, show);
    }


}
