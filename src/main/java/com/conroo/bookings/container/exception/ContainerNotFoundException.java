package com.conroo.bookings.container.exception;

public class ContainerNotFoundException extends Exception{
    public ContainerNotFoundException(Long id) {
        super(String.format("Container with id %d not found", id));
    }

    public ContainerNotFoundException(String containerNumber) {
        super(String.format("Container with id %d not found", containerNumber));
    }
}
