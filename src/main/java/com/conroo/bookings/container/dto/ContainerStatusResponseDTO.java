package com.conroo.bookings.container.dto;

import com.conroo.bookings.timeSlot.TimeSlot;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerStatusResponseDTO {

    private ContainerResponseDTO container;
    private List<TimeSlot> availableTimeSlots;
}
