package com.conroo.bookings.booking;

import com.conroo.bookings.container.Container;
import com.conroo.bookings.timeSlot.TimeSlot;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;

    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeSlot;

    public Booking(LocalDateTime createdDate, Container container, TimeSlot timeSlot) {
        this.createdDate = createdDate;
        this.container = container;
        this.timeSlot = timeSlot;
    }

}

