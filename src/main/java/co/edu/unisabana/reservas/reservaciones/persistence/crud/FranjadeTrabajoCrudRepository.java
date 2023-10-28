package co.edu.unisabana.reservas.reservaciones.persistence.crud;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;

    
public interface FranjadeTrabajoCrudRepository extends CrudRepository<FranjaDeTrabajo, Long> {
    List<FranjaDeTrabajo> findByHoraInicioBefore(LocalTime horaInicio);
    List<FranjaDeTrabajo> findByFechaLaborable(LocalDate fechaLaborable);
}

