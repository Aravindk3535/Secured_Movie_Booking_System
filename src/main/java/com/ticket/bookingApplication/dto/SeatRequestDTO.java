package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.model.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeatRequestDTO extends Seat {
    private Long showId;
    private List<String> seats;
}
