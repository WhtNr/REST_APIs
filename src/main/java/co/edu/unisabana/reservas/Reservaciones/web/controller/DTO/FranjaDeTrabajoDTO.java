package co.edu.unisabana.reservas.Reservaciones.web.controller.DTO;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // Método estático para convertir una lista de entidades FranjaDeTrabajo a una lista de DTOs
    public static List<FranjaDeTrabajoDTO> toFranjaDeTrabajoDTOList(List<FranjaDeTrabajo> franjas) {
        return franjas.stream().map(FranjaDeTrabajoDTO::new).collect(Collectors.toList());
    }
}