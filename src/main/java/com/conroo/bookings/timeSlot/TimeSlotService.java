package com.conroo.bookings.timeSlot;

import com.conroo.bookings.container.ContainerService;
import com.conroo.bookings.timeSlot.exception.TimeSlotNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TimeSlotService.class);

    @Autowired
    private final TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getTimeSlots() {

        LOGGER.info("Get all TimeSlots");

        return timeSlotRepository.findAll();
    }

    public TimeSlot findById(Long id) throws TimeSlotNotFoundException{

        LOGGER.info(String.format("Get Timeslot with id %d", id));

        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(id);

        if (optionalTimeSlot.isPresent()) {
            return optionalTimeSlot.get();
        }

        throw new TimeSlotNotFoundException(id);
    }
}
