package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.model.Movie;
import com.ticket.bookingApplication.service.MovieServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieServiceLayer movieService;
    public MovieController(MovieServiceLayer movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }

    @PutMapping("/editMovie/{id}")
    public Movie editMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.editMovie(id, movie);
    }
}
