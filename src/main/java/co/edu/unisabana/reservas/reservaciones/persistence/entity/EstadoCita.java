package co.edu.unisabana.reservas.reservaciones.persistence.entity;

public interface EstadoCita {
    void programarCita (Cita cita);
    void cancelarCita (Cita cita);
    void reprogramarCita (Cita cita);
    String obtenerEstadoActualComoCadena();


}
