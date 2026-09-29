package com.ticket.bookingApplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "seats",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"show_id", "seat_number"}
      )
)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    @Column(name = "seat_number")
    private String seatNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_Id")
    private Show show;
    private boolean isBooked;

}

