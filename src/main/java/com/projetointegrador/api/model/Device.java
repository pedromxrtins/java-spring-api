package com.projetointegrador.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;

    private Boolean state;

    @Column(name = "state_exaustor")
    private Boolean stateExaustor;

    @Column(name = "state_ventilador")
    private Boolean stateVentilador;

    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
