package co.edu.unisabana.reservas.Reservaciones.domain.repository;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByFechaAndHoraInicioBetween(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);

}
