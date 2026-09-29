package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.model.Booking;
import com.ticket.bookingApplication.model.Show;

public class ModelConvertor {
    public static BookingResponseDTO bookingResponseDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getUserId(),
                booking.getStatus(),
                booking.getTotalAmount(),
                booking.getSeats(),
                booking.getShow(),
                booking.getLocalDateTime()
        );
    }

    public static ShowResponseDTO showResponseDTO(Show show) {
        return new ShowResponseDTO(
                show.getShowId(),
                show.getMovie().getTittle(),
                show.getTheatre().getTheatreName(),
                show.getLocalDateTime(),
                show.getTicketPrice()
        );
    }
}
