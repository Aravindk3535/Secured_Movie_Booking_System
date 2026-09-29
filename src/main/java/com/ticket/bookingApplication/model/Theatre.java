package com.ticket.bookingApplication.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreId;
    private String theatreName;
    private String location;

    public Theatre(String theatreName, String location) {
        this.theatreName = theatreName;
        this.location = location;
    }

}
