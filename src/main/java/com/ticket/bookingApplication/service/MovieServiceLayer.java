package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Movie;
import com.ticket.bookingApplication.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceLayer {

    private final MovieRepository movieRepo;

    public MovieServiceLayer(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie getMovieById(Long id) {
        return movieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public String addMovie(Movie movie) {
        movieRepo.save(movie);
        return "Movie has been added successfully";
    }

    public String deleteMovie(Long id) {
        if (movieRepo.existsById(id)) {
            movieRepo.deleteById(id);
            return "Movie has been deleted successfully";
        }
        throw new ResourceNotFoundException("Movie not found with this id " + id);
    }

    public Movie editMovie(Long id, Movie movie) {
        Optional<Movie> updatedMovie = movieRepo.findById(id);
        if (updatedMovie.isEmpty()) {
            throw new ResourceNotFoundException("Movie not found with this id " + id);
        }
                updatedMovie.get().setTittle(movie.getTittle());
                updatedMovie.get().setDescription(movie.getDescription());
                updatedMovie.get().setMovieDuration(movie.getMovieDuration());
                updatedMovie.get().setLanguage(movie.getLanguage());
                updatedMovie.get().setGenre(movie.getGenre());
                movieRepo.save(updatedMovie.get());

        return updatedMovie.get();
    }
}
