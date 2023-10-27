package co.edu.unisabana.reservas.reservaciones.persistence.crud;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;

public interface CitaCrudRepository extends CrudRepository<Cita, Long> {
    List<Cita> findByFechaAndHoraInicioBetween(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);
}
