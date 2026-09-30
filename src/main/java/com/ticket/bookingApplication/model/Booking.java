package com.ticket.bookingApplication.model;

import com.ticket.bookingApplication.dto.BookingRequestDTO;
import com.ticket.bookingApplication.enums.BookingStatus;
import com.ticket.bookingApplication.enums.PaymentStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name = "bookings",
       uniqueConstraints = @UniqueConstraint(columnNames = {"show_id", "seat_number"}
))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showId", nullable = false)
    private Show show;
    private LocalDateTime localDateTime;
    @ElementCollection
    @CollectionTable(name = "user_seats", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "seat_id")
    private List<Long> seats;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking(Long userId, Show show, List<Long> seat, Double totalAmount) {
        this.userId = userId;
        this.show = show;
        this.seats =seat;
        this.totalAmount = totalAmount;
    }

    public void updateBookingDetails(BookingRequestDTO bookingRequestDTO) {
        setShow(bookingRequestDTO.getShow());
        setSeats(bookingRequestDTO.getSeatId());
        setUserId(bookingRequestDTO.getUserId());
        setStatus(BookingStatus.PAYMENT_PENDING);
        setTotalAmount(bookingRequestDTO.getAmount());
    }


}
