package com.ticket.bookingApplication.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime localDateTime;

    public ErrorResponse(int status, String message, LocalDateTime localDateTime) {
        this.status = status;
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
