package com.projetointegrador.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double ammoniaLevel;
    private Double temperature;
    private Double humidity;

    @Column(nullable = false, updatable = false)
    private LocalDateTime recordedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
