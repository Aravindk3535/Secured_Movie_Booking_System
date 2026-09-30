package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.ModelConvertor;
import com.ticket.bookingApplication.dto.ShowRequestDTO;
import com.ticket.bookingApplication.dto.ShowResponseDTO;
import com.ticket.bookingApplication.exception.ApplicationException;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Movie;
import com.ticket.bookingApplication.model.Show;
import com.ticket.bookingApplication.model.Theatre;
import com.ticket.bookingApplication.repository.MovieRepository;
import com.ticket.bookingApplication.repository.ShowRepository;
import com.ticket.bookingApplication.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceLayer {

    private final ShowRepository showRepo;
    private final MovieRepository movieRepo;
    private final TheatreRepository theartreRepo;

    public ShowServiceLayer(ShowRepository showRepo, MovieRepository movieRepo, TheatreRepository theartreRepo) {
        this.showRepo = showRepo;
        this.movieRepo = movieRepo;
        this.theartreRepo = theartreRepo;
    }

    public ShowResponseDTO getShowById(Long id) {
        Optional<Show> showDetails = showRepo.findById(id);
        if (showDetails.isEmpty()) {
            throw new ResourceNotFoundException("There is no show with this id " + id);
        }
        ShowResponseDTO responseDTO = ModelConvertor.showResponseDTO(showDetails.get());
        return responseDTO;
    }

    public List<ShowResponseDTO> getAllShows() {
        List<Show> showList = showRepo.findAll();
        List<ShowResponseDTO> showDetailsList = new ArrayList<>();
        for (Show show : showList) {
            ShowResponseDTO response = ModelConvertor.showResponseDTO(show);
            showDetailsList.add(response);
        }
        if (showList.isEmpty()) {
            throw new ResourceNotFoundException("No shows are there for this time");
        }
        return showDetailsList;
    }

    public String createShow(ShowRequestDTO show) {
        Movie movie = movieRepo.findById(show.getMovieId())
                        .orElseThrow(() -> new ResourceNotFoundException("Movie not found with this id -" + show.getMovie().getMovieId()));

        Theatre theatre = theartreRepo.findById(show.getTheatreId())
                        .orElseThrow(()-> new ResourceNotFoundException("Theatre not found with this id - " + show.getTheatre().getTheatreId()));

        Show addShow = new Show();
        addShow.setMovie(movie);
        addShow.setTheatre(theatre);
        addShow.setLocalDateTime(show.getLocalDateTime());
        validateShows(addShow);
        showRepo.save(addShow);
        return "Show Created successfully";
    }

    public String deleteShow(Long id) {
        if (showRepo.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("There is no show with this id " + id);
        }
        showRepo.deleteById(id);
        return "This show has been deleted successfully";
    }

    public ShowResponseDTO editShow(Long id, ShowRequestDTO show) {
        if (showRepo.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("There is no show with this id " + id);
        }

        Movie movie = movieRepo.findById(show.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with this id -" + show.getMovieId()));

        Theatre theatre = theartreRepo.findById(show.getTheatreId())
                .orElseThrow(()-> new ResourceNotFoundException("Theatre not found with this id - " + show.getTheatreId()));

        Show updateShow = new Show();
        updateShow.setShowId(show.getShowId());
        updateShow.setMovie(movie);
        updateShow.setTheatre(theatre);
        updateShow.setLocalDateTime(show.getLocalDateTime());
        validateShows(updateShow);
        showRepo.save(updateShow);
        ShowResponseDTO responseDTO = ModelConvertor.showResponseDTO(updateShow);
        return responseDTO;
    }

    public void validateShows(Show show) {
        String movieName = show.getMovie().getTittle();
        LocalDateTime showtime = show.getLocalDateTime();
        List<Show> showList = showRepo.findAll().stream()
                .filter(theatre -> show.getTheatre().getTheatreId().equals(show.getTheatre().getTheatreId()))
                .toList();
        if (!showList.isEmpty()) {
            for (Show show1 : showList) {
                if (movieName.equalsIgnoreCase(show1.getMovie().getTittle()) && showtime.equals(show1.getLocalDateTime())) {
                    throw new ApplicationException("Show times for this movie has been already created, choose different time");
                }
            }
        }

    }

}
