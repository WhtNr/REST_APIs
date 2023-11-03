package co.edu.unisabana.reservas.reservaciones.web.controller.dto;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class FranjaDeTrabajoDTO {

    private Long id;
    private LocalDate fechaLaborable;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // Constructor
    public FranjaDeTrabajoDTO(FranjaDeTrabajo franjaDeTrabajo) {
        this.id = franjaDeTrabajo.getId();
        this.fechaLaborable = franjaDeTrabajo.getFechaLaborable();
        this.horaInicio = franjaDeTrabajo.getHoraInicio();
        this.horaFin = franjaDeTrabajo.getHoraFin();
    }


}