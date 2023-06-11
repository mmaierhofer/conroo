package com.conroo.bookings.booking;

import com.conroo.bookings.booking.dto.BookingRequestDTO;
import com.conroo.bookings.booking.exception.InvalidBookingException;
import com.conroo.bookings.container.Container;
import com.conroo.bookings.container.ContainerService;
import com.conroo.bookings.container.exception.ContainerNotFoundException;
import com.conroo.bookings.timeSlot.TimeSlot;
import com.conroo.bookings.timeSlot.TimeSlotService;
import com.conroo.bookings.timeSlot.exception.TimeSlotNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private final BookingRepository bookingRepository;
    @Autowired
    private final ContainerService containerService;
    @Autowired
    private final TimeSlotService timeSlotService;

    public List<Booking> getBookings () {
        LOGGER.info("Get All Bookings");

        return bookingRepository.findAll();
    }

    public Booking bookContainer (BookingRequestDTO bookingRequestDTO)
            throws ContainerNotFoundException, TimeSlotNotFoundException, InvalidBookingException {

        LOGGER.info(String.format("Book Container with id %d for Timeslot with id %d"
                , bookingRequestDTO.getContainerId(), bookingRequestDTO.getTimeSlotId()));

        // Throws ContainerNotFoundException if not found
        Container container = containerService.findById(bookingRequestDTO.getContainerId());
        // Throws TimeSlotNotFoundException if not found
        TimeSlot timeSlot = timeSlotService.findById(bookingRequestDTO.getTimeSlotId());

        if (!timeSlotIsValidForContainer(container, timeSlot)) {
            LOGGER.info(String.format("Booking is not valid for container with id %d and timeslot with id %d",
                    container.getId(), timeSlot.getId()));
            throw new InvalidBookingException(container.getId(), timeSlot.getId());
        }

        Booking booking = createBooking(timeSlot, container);
        containerService.updateAvailableFromTime(container.getId(), timeSlot.getSlotEnd());
        return booking;
    }

    private boolean timeSlotIsValidForContainer (Container container, TimeSlot timeSlot) {

        return timeSlot.getSlotBegin().isAfter(container.getAvailableFrom());
    }

    private Booking createBooking(TimeSlot timeSlot, Container container) {
        Booking booking = new Booking(LocalDateTime.now(), container, timeSlot);
        return bookingRepository.save(booking);
    }
}
