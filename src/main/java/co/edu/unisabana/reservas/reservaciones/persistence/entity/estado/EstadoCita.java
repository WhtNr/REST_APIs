package co.edu.unisabana.reservas.reservaciones.persistence.entity.estado;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;

public interface EstadoCita {
    void programarCita (Cita cita);
    void cancelarCita (Cita cita);
    void reprogramarCita (Cita cita);
    String obtenerEstadoActualComoCadena();


}
