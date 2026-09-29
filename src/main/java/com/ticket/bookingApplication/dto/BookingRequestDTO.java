package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.model.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookingRequestDTO {

    private Long userId;
    private List<Long> seatId;
    private Show show;
    private LocalDateTime localDateTime;
    private Double amount;

}
