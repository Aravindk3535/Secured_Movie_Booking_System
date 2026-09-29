package com.ticket.bookingApplication.dto;

import com.ticket.bookingApplication.model.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShowRequestDTO extends Show {

    private Long movieId;
    private Long theatreId;

}
