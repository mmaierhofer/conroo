package com.conroo.bookings.booking;

import com.conroo.bookings.booking.dto.BookingRequestDTO;
import com.conroo.bookings.booking.exception.InvalidBookingException;
import com.conroo.bookings.container.Container;
import com.conroo.bookings.container.ContainerService;
import com.conroo.bookings.timeSlot.TimeSlot;
import com.conroo.bookings.timeSlot.TimeSlotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class BookingServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private static LocalDateTime beforeTime;
    private static LocalDateTime afterTime;
    private BookingRequestDTO bookingRequestDTO;
    private Container container;
    private TimeSlot timeSlot;
    private BookingService underTest;
    @Mock
    private TimeSlotService timeSlotService;
    @Mock
    private ContainerService containerService;
    @Mock
    private BookingRepository bookingRepository;

    @BeforeAll
    public static void setup() {
        LOGGER.info("Setup BookingService Test");
        beforeTime = LocalDateTime.of(2022, Month.APRIL, 1, 0, 0);
        afterTime = LocalDateTime.of(2022, Month.APRIL, 2, 0, 0);
    }

    @BeforeEach
    public void init() {
        container = new Container();
        timeSlot = new TimeSlot();
        bookingRequestDTO = new BookingRequestDTO(1L, "", 1L);
        underTest = new BookingService(bookingRepository, containerService, timeSlotService);
    }

    @Test
    public void whenTimeSlotIsValidForContainer_thenCreateBooking() {
        LOGGER.info("Test when time slot is valid for container");
        container.setAvailableFrom(beforeTime);
        timeSlot.setSlotBegin(afterTime);
        try {
            Mockito.when(containerService.findById(bookingRequestDTO.getContainerId())).thenReturn(container);
            Mockito.when(timeSlotService.findById(bookingRequestDTO.getTimeSlotId())).thenReturn(timeSlot);
            Mockito.when(bookingRepository.save(any(Booking.class)))
                    .thenReturn(new Booking(LocalDateTime.now(), container, timeSlot));
            Assertions.assertDoesNotThrow(() -> underTest.bookContainer(bookingRequestDTO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Test
    public void whenTimeSlotIsNotValidForContainer_thenThrowException() {
        LOGGER.info("Test when time slot is not valid for container");
        container.setAvailableFrom(afterTime);
        timeSlot.setSlotBegin(beforeTime);
        try {
            Mockito.when(containerService.findById(bookingRequestDTO.getContainerId())).thenReturn(container);
            Mockito.when(timeSlotService.findById(bookingRequestDTO.getTimeSlotId())).thenReturn(timeSlot);
            underTest.bookContainer(bookingRequestDTO);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidBookingException);
        }
    }

    @Test
    public void whenTimeSlotIsSameForContainer_thenThrowException() {
        LOGGER.info("Test when time slot time is same time as container");
        container.setAvailableFrom(beforeTime);
        timeSlot.setSlotBegin(beforeTime);
        try {
            Mockito.when(containerService.findById(bookingRequestDTO.getContainerId())).thenReturn(container);
            Mockito.when(timeSlotService.findById(bookingRequestDTO.getTimeSlotId())).thenReturn(timeSlot);
            underTest.bookContainer(bookingRequestDTO);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidBookingException);
        }

    }

}
