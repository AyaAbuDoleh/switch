package com.example.demo.entity;

import com.example.demo.entity.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car implements IEntity<Car>, Serializable {

    @Id
    @GeneratedValue
    @Column(name="id", updatable = false, nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name="date_created",  updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime dateCreated;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column(name="convertible")
    private Boolean convertible;

    @Column(name="rating")
    private Integer rating;

    @Column(name = "engine_type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name ="manufacture")
    private String manufacture;

    @Column(name="reserved",  columnDefinition = "boolean default false")
    private Boolean reserved = false;

}
