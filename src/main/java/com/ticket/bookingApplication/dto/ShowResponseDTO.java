package com.ticket.bookingApplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShowResponseDTO {
    private Long showId;
    private String movieName;
    private String theatreName;
    private LocalDateTime showTime;

    public ShowResponseDTO(Long showId, String movieName, String theatreName, LocalDateTime showTime) {
        this.showId = showId;
        this.movieName = movieName;
        this.theatreName =theatreName;
        this.showTime = showTime;
    }
}
