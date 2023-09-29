package co.edu.unisabana.reservas.Reservaciones.persistence.crud;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;

    
public interface FranjadeTrabajoCrudRepository extends CrudRepository<FranjaDeTrabajo, Long> {
    List<FranjaDeTrabajo> findByHoraInicioBefore(LocalTime horaInicio);
    List<FranjaDeTrabajo> findByFecha(LocalDate fechaLaborable);
}

