package com.conroo.bookings.container.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerResponseDTO {

    private Long id;
    private String containerNumber;
    private int Weight;
    private int length;
    private String type;
}
