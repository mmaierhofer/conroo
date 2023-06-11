package com.conroo.bookings.container;

import com.conroo.bookings.container.exception.ContainerFullyBookedException;
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
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ContainerServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContainerServiceTest.class);
    private static LocalDateTime beforeTime;
    private static LocalDateTime afterTime;
    private final String containerNumber = "A1B2";
    private Container container;
    private ContainerService underTest;
    @Mock
    private ContainerRepository containerRepository;
    @Mock
    private TimeSlotService timeSlotService;

    @BeforeAll
    public static void setup() {
        LOGGER.info("Setup ContainerService Test");
        beforeTime = LocalDateTime.of(2022, Month.APRIL, 1, 0, 0);
        afterTime = LocalDateTime.of(2022, Month.APRIL, 2, 0, 0);
    }

    @BeforeEach
    public void init() {
        underTest = new ContainerService(containerRepository, timeSlotService, new ModelMapper());
        container = new Container();
    }

    @Test
    public void whenContainerIsAvailable_thenReturnStatusResponseDTO() {
        LOGGER.info("Test when container booking is available");
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setSlotBegin(afterTime);
        container.setAvailableFrom(beforeTime);
        try {
            Mockito.when(containerRepository.findByContainerNumber(containerNumber)).thenReturn(Optional.ofNullable(container));
            Mockito.when(timeSlotService.getTimeSlots()).thenReturn(List.of(timeSlot));
            Assertions.assertDoesNotThrow(() -> underTest.getContainerStatus(containerNumber));
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }

    @Test
    public void whenNoTimeSlotsAvailable_thenThrowFullyBookedException() {
        LOGGER.info("Test when no time slot is available for container");
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setSlotBegin(beforeTime);
        container.setAvailableFrom(afterTime);
            try {
                Mockito.when(containerRepository.findByContainerNumber(containerNumber)).thenReturn(Optional.ofNullable(container));
                Mockito.when(timeSlotService.getTimeSlots()).thenReturn(List.of(timeSlot));
                underTest.getContainerStatus(containerNumber);
            } catch (Exception e) {
                Assertions.assertTrue(e instanceof ContainerFullyBookedException);
            }
    }
}
