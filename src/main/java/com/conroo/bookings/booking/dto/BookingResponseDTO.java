package com.conroo.bookings.booking.dto;

import com.conroo.bookings.container.Container;
import com.conroo.bookings.timeSlot.TimeSlot;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookingResponseDTO implements Serializable {

    private Long id;
    private Container container;
    private TimeSlot timeSlot;
}
