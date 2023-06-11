package com.conroo.bookings.booking;

import com.conroo.bookings.booking.dto.BookingRequestDTO;
import com.conroo.bookings.booking.dto.BookingResponseDTO;
import com.conroo.bookings.booking.exception.InvalidBookingException;
import com.conroo.bookings.container.exception.ContainerNotFoundException;
import com.conroo.bookings.timeSlot.exception.TimeSlotNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private final BookingService bookingService;
    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping
    ResponseEntity<List<BookingResponseDTO>> listBookings() {
        List<Booking> bookings = bookingService.getBookings();

        return new ResponseEntity<>(
                bookings.stream().map(
                        task -> modelMapper.map(task, BookingResponseDTO.class)
                ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> bookContainer(@RequestBody @Valid BookingRequestDTO bookingRequestDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapper.map(
                            bookingService.bookContainer(bookingRequestDTO), BookingResponseDTO.class
                    ));
        } catch (ContainerNotFoundException | TimeSlotNotFoundException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(e.getMessage()));
        } catch (InvalidBookingException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format(e.getMessage()));
        }
    }
}
