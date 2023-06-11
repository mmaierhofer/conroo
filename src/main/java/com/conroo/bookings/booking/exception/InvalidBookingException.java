package com.conroo.bookings.booking.exception;

public class InvalidBookingException extends Exception{

    public InvalidBookingException(Long containerId, Long timeSlotId) {
        super(String.format("Booking with containerId %d and timeSlotId %d is not valid", containerId, timeSlotId));
    }
}
