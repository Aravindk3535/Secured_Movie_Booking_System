package com.ticket.bookingApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatreId")
    private Theatre theatre;
    private LocalDateTime localDateTime;
    private Double ticketPrice;

}
