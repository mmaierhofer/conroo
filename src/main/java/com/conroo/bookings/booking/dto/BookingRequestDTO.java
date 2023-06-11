package com.conroo.bookings.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    @NotNull(message = "Container ID must be not null")
    private Long containerId;
    private String containerNumber;
    @NotNull(message = "TimeSlot ID must be not null")
    private Long timeSlotId;
}
