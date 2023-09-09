package co.edu.unisabana.reservas.Reservaciones.persistence.logica;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class ReprogramacionCitaRequest {
    private LocalDate nuevaFecha;
    private LocalTime nuevaHoraInicio;
    private LocalTime nuevaHoraFin;

    // Getters y setters
}
