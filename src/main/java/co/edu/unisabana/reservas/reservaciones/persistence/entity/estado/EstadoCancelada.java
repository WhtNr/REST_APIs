package co.edu.unisabana.reservas.reservaciones.persistence.entity.estado;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;

public class EstadoCancelada implements EstadoCita {
    @Override
    public void programarCita(Cita cita) {
        // No hay acción específica para programar en estado cancelada
    }

    @Override
    public void cancelarCita(Cita cita) {
        // No hay acción específica para cancelar en estado cancelada
    }

    @Override
    public void reprogramarCita(Cita cita) {
        cita.setEstado(new EstadoReprogramada());
    }

    @Override
    public String obtenerEstadoActualComoCadena() {
        return "Cancelada";
    }
}

