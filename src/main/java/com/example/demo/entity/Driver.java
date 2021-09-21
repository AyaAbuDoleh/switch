package com.example.demo.entity;

import com.example.demo.entity.enums.LicenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements IEntity<Car>, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name="first_name", nullable = false, updatable = false)
    private String firstName;

    @Column(name="last_name", nullable = false, updatable = false)
    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="birthday", nullable = false)
    private Date birthday;

    @Column(name ="license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(name ="license_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;

    @CreationTimestamp
    @Column(name="date_created",  updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime dateCreated;

    @OneToOne(targetEntity = Car.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_fk",  referencedColumnName = "id", unique = true)
    private Car car;

}
