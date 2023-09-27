package co.edu.unisabana.reservas.Reservaciones.web.controller.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private long idCita;
    private long idPersonal;
    private long idServicio;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private Boolean estado;

    // Constructores (puedes tener un constructor vacío y uno con parámetros)

    // Getters y setters para todos los campos

    // Otros métodos si es necesario, como toString, equals, hashCode, etc.

    // No se incluye lógica de negocio aquí, solo campos y métodos relacionados con la transferencia de datos.
}
