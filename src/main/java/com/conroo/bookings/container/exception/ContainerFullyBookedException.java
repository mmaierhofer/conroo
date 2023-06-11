package com.conroo.bookings.container.exception;

public class ContainerFullyBookedException extends Exception{

    public ContainerFullyBookedException(Long id) {
        super(String.format("Container with id %d has no available slots", id));
    }
}
