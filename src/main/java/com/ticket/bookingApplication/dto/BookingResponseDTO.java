package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.model.Show;
import com.ticket.bookingApplication.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookingResponseDTO {
    private Long bookingId;
    private Long userId;
    private String status;
    private Double totalAmount;
    private List<Long> seat;
    private Show show;
    private LocalDateTime localDateTime;

    public BookingResponseDTO(Long userId, String status, Double totalAmount, List<Long> seat, Show show, LocalDateTime localDateTime){
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.seat = seat;
        this.show = show;
        this.localDateTime = localDateTime;
    }
}
