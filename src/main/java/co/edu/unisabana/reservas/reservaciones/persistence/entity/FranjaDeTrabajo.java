package co.edu.unisabana.reservas.reservaciones.persistence.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class FranjaDeTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_laborable")
    private LocalDate fechaLaborable;
    private LocalTime horaInicio;
    private LocalTime horaFin;



}
