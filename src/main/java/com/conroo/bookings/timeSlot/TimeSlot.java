package com.conroo.bookings.timeSlot;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "time_slot")
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_begin")
    private LocalDateTime slotBegin;

    @Column(name = "slot_end")
    private LocalDateTime slotEnd;
}
