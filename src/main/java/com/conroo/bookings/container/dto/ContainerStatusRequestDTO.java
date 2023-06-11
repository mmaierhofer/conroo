package com.conroo.bookings.container.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContainerStatusRequestDTO {

    @NotNull(message = "Container number must be not null")
    private String containerNumber;
}
