package com.conroo.bookings.container;

import com.conroo.bookings.container.dto.ContainerResponseDTO;
import com.conroo.bookings.container.dto.ContainerStatusResponseDTO;
import com.conroo.bookings.container.exception.ContainerFullyBookedException;
import com.conroo.bookings.container.exception.ContainerNotFoundException;
import com.conroo.bookings.timeSlot.TimeSlot;
import com.conroo.bookings.timeSlot.TimeSlotService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContainerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContainerService.class);
    @Autowired
    ContainerRepository containerRepository;
    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    ModelMapper modelMapper;

    public ContainerStatusResponseDTO getContainerStatus(String containerNumber)
            throws ContainerNotFoundException, ContainerFullyBookedException {

        LOGGER.info(String.format("Get status for Container with id %s", containerNumber));

        Container container = findByContainerNumber(containerNumber);
        ContainerResponseDTO containerDTO = modelMapper.map(container, ContainerResponseDTO.class);
        List<TimeSlot> availableTimeSlots = filterTimeSlotsByDate(container.getAvailableFrom(),timeSlotService.getTimeSlots());
        if (availableTimeSlots.isEmpty()) {
            LOGGER.info(String.format("No available slot found for container with id %s", containerNumber));
            throw new ContainerFullyBookedException(container.getId());
        }
        return new ContainerStatusResponseDTO(containerDTO, availableTimeSlots);

    }

    private List<TimeSlot> filterTimeSlotsByDate(LocalDateTime startDate, List<TimeSlot> timeSlots) {

        return timeSlots.stream().filter(s -> s.getSlotBegin().isAfter(startDate)).collect(Collectors.toList());
    }

    public Container findById(Long containerId) throws ContainerNotFoundException {

        LOGGER.info(String.format("Find Container with id %d", containerId));

        Optional<Container> optionalContainer = containerRepository.findById(containerId);
        if (optionalContainer.isPresent()) {
            return optionalContainer.get();
        }
        throw new ContainerNotFoundException(containerId);
    }

    public Container findByContainerNumber(String containerNumber) throws ContainerNotFoundException {

        LOGGER.info(String.format("Find Container with name %s", containerNumber));

        Optional<Container> optionalContainer = containerRepository.findByContainerNumber(containerNumber);
        if (optionalContainer.isPresent()) {
            return optionalContainer.get();
        }
        throw new ContainerNotFoundException(containerNumber);
    }

    public void updateAvailableFromTime(Long containerId, LocalDateTime newAvailableFromTime)
            throws ContainerNotFoundException{

        LOGGER.info(String.format("Update available time from Container with id %d", containerId));

        Container container = findById(containerId);
        container.setAvailableFrom(newAvailableFromTime);
        containerRepository.save(container);
    }
}
