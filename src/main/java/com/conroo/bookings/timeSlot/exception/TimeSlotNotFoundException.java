package com.conroo.bookings.timeSlot.exception;

public class TimeSlotNotFoundException extends Exception {

    public TimeSlotNotFoundException(Long id) {
        super(String.format("Container with id %d not found", id));
    }
}
