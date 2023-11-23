package co.edu.unisabana.reservas.reservaciones.persistence.entity.estado;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;

public class EstadoProgramada implements EstadoCita {
    @Override
    public void programarCita(Cita cita) {
        // No hay acción específica para programar en estado programada
    }

    @Override
    public void cancelarCita(Cita cita) {
        cita.setEstado(new EstadoCancelada());
    }

    @Override
    public void reprogramarCita(Cita cita) {
        cita.setEstado(new EstadoReprogramada());
    }
    @Override
    public String obtenerEstadoActualComoCadena() {
        return "Programada";
    }
}
