package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.exception.ApplicationException;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Theatre;
import com.ticket.bookingApplication.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceLayer {

    private final TheatreRepository theatreRepo;

    public TheatreServiceLayer (TheatreRepository theatreRepo) {
        this.theatreRepo = theatreRepo;
    }

    public List<Theatre> getTheatres() {
        List<Theatre> theatreList = theatreRepo.findAll();
        if (theatreList.isEmpty()) {
            throw new ResourceNotFoundException("Sorry no theatres are found, Add theatres");
        }
        return theatreRepo.findAll();
    }

    public Theatre getTheatreByID(Long id) {
        return theatreRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre by this Id-" + id + " is not found "));
    }

    public String addTheatre(Theatre theatre) {
        validateTheatre(theatre);
        theatreRepo.save(theatre);
        return "Theatre added successfully";
    }

    public String deleteTheatre(Long id) {
        if (theatreRepo.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Theatre by this Id-" + id + " is not found ");
        }
        theatreRepo.deleteById(id);
        return "Theatre deleted successfully";
    }

    public Theatre editTheatre(Long id, Theatre theatre) {
        Optional<Theatre> isPresent = theatreRepo.findById(id);
        if (isPresent.isEmpty()) {
            throw new ResourceNotFoundException("Theatre by this Id-" + id + " is not found ");
        }
        validateTheatre(theatre);
        isPresent.get().setTheatreId(id);
        isPresent.get().setTheatreName(theatre.getTheatreName());
        isPresent.get().setLocation(theatre.getLocation());
        theatreRepo.save(isPresent.get());
        return isPresent.get();
    }

    public void validateTheatre(Theatre theatre) {
        String name = theatre.getTheatreName();
        String location = theatre.getLocation();
        List<Theatre> theatreList = theatreRepo.findAll();
        if (!theatreList.isEmpty()) {
            for (Theatre theatre1 : theatreList) {
                if (theatre1.getTheatreName().equals(name) && theatre1.getLocation().equals(location)) {
                    throw new ApplicationException("Movie name and location are already been created");
                }
            }
        }
    }
}
