package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.SeatRequestDTO;
import com.ticket.bookingApplication.enums.SeatStatus;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.Seat;
import com.ticket.bookingApplication.model.Show;
import com.ticket.bookingApplication.repository.SeatRepository;
import com.ticket.bookingApplication.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceLayer {

    private final SeatRepository seatRepo;
    private final ShowRepository showRepo;

    public SeatServiceLayer(SeatRepository seatRepo, ShowRepository showRepo) {
        this.seatRepo = seatRepo;
        this.showRepo = showRepo;
    }

    public Seat getSeatById(Long id) {
        return seatRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with this id " + id));
    }

    public String createSeat(SeatRequestDTO requestDTO) {
        Show show = showRepo.findById(requestDTO.getShowId())
                        .orElseThrow(() -> new ResourceNotFoundException("Seat not found with this id " + requestDTO.getShowId()));
        List<Seat> seats = new ArrayList<>();
        for (String seat : requestDTO.getSeats()) {
            Seat addseat = new Seat();
            addseat.setSeatNumber(seat);
            addseat.setShow(show);
            addseat.setStatus(SeatStatus.AVAILABLE);
            seats.add(addseat);
        }
        seatRepo.saveAll(seats);
        return "Seat created Successfully";
    }

    public List<Seat> getAllSeats() {
        List<Seat> seats = seatRepo.findAll();
        return seats;
    }

    public String deleteSeat(Long id) {
        if (seatRepo.existsById(id)) {
            seatRepo.deleteById(id);
            return "Seat Deleted successfully";
        }
        throw new ResourceNotFoundException("Seat not found with this id " + id);
    }

    public Seat editSeat(Long id, SeatRequestDTO requestDTO) {
        if (seatRepo.existsById(id)) {
            Seat seat = seatRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found with this id " + requestDTO.getSeatId()));
            Show show = showRepo.findById(requestDTO.getShowId())
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found with this id " + requestDTO.getShowId()));
            seat.setSeatNumber(requestDTO.getSeatNumber());
            seat.setShow(show);
            seat.setStatus(SeatStatus.AVAILABLE);
            seatRepo.save(seat);
            return seat;
        }

        throw new ResourceNotFoundException("Seat not found with this id " + id);
    }
}
