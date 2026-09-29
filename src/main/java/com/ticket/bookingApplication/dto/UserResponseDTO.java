package com.ticket.bookingApplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private Long userId;
    private String userName;
    private String email;

    public UserResponseDTO(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
