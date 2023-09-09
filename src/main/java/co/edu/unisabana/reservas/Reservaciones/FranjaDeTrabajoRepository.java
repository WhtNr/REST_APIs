package co.edu.unisabana.reservas.Reservaciones;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FranjaDeTrabajoRepository extends JpaRepository<FranjaDeTrabajo, Long> {
    // Método de consulta personalizado utilizando una convención de nombres
    List<FranjaDeTrabajo> findByHoraInicioBefore(LocalTime hora);

    // Método de consulta personalizado utilizando una consulta JPQL

    @Query("SELECT f FROM FranjaDeTrabajo f WHERE f.fechaLaborable = :fecha")
    List<FranjaDeTrabajo> findByFecha(@Param("fecha") LocalDate fecha);


}
