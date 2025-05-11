package com.rcphotography.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceType;
    private String fullName;
    private String email;
    private String phone; // ✅ new field

    private LocalDate datePreferred;
    private LocalTime timePreferred; // ✅ new field

    @Column(length = 1000)
    private String message;

    private LocalDate createdAt;
}
