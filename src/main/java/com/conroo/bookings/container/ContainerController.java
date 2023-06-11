package com.conroo.bookings.container;

import com.conroo.bookings.container.dto.ContainerStatusRequestDTO;
import com.conroo.bookings.container.dto.ContainerStatusResponseDTO;
import com.conroo.bookings.container.exception.ContainerFullyBookedException;
import com.conroo.bookings.container.exception.ContainerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/containers")
public class ContainerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContainerController.class);
    @Autowired
    ContainerService containerService;

    @PostMapping
    public ResponseEntity<?> getContainerStatus(@RequestBody @Valid ContainerStatusRequestDTO requestDto) {
        try {
            ContainerStatusResponseDTO responseDto = containerService.getContainerStatus(requestDto.getContainerNumber());
            return ResponseEntity.ok(responseDto);
        } catch (ContainerNotFoundException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(e.getMessage()));
        } catch (ContainerFullyBookedException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format(e.getMessage()));
        }
    }
}
