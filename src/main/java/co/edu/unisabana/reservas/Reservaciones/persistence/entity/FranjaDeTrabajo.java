package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
public class FranjaDeTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_laborable")
    private LocalDate fechaLaborable;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
