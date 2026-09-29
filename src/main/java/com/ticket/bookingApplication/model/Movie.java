package com.ticket.bookingApplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String tittle;
    private String description;
    private String movieDuration;
    private String language;
    private String genre;

    public Movie(String tittle, String description, String movieDuration, String language, String genre) {
        this.tittle = tittle;
        this.description = description;
        this.movieDuration = movieDuration;
        this.language = language;
        this.genre = genre;
    }
}
