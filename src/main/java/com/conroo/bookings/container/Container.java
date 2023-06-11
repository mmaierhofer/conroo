package com.conroo.bookings.container;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "container")
@Data
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "container_number")
    private String containerNumber;

    @Column(name = "container_weight")
    private Integer containerWeight;

    private Short length;

    private String type;

    @Column(name = "available_from")
    private LocalDateTime availableFrom;
}

