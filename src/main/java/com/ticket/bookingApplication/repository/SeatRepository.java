package com.ticket.bookingApplication.repository;

import com.ticket.bookingApplication.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllBySeatIdIn(List<Long> seatIds);

}
