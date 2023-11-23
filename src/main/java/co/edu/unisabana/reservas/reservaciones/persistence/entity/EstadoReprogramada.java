package co.edu.unisabana.reservas.reservaciones.persistence.entity;

public class EstadoReprogramada implements EstadoCita {
    @Override
    public void programarCita(Cita cita) {
        cita.setEstado(new EstadoProgramada());
    }

    @Override
    public void cancelarCita(Cita cita) {
        // No hay acción específica para cancelar en estado reprogramada
    }

    @Override
    public void reprogramarCita(Cita cita) {
        // No hay acción específica para reprogramar en estado reprogramar
    }

    @Override
    public String obtenerEstadoActualComoCadena() {
        return "Reprogramada";
    }
}