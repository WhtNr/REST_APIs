package co.edu.unisabana.reservas.Reservaciones.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unisabana.reservas.Reservaciones.persistence.crud.FranjadeTrabajoCrudRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;

@Repository
public class FranjadeTrabajoRepository {
    
    private FranjadeTrabajoCrudRepository franjaDeTrabajoCrudRepository;

    public List<FranjaDeTrabajo> GetHoraInicioBefore(LocalTime horaInicio){
        return (List<FranjaDeTrabajo>) franjaDeTrabajoCrudRepository.findByHoraInicioBefore(horaInicio);
    }

    public List<FranjaDeTrabajo> GetFecha(LocalDate fechaLaborable){
        return (List<FranjaDeTrabajo>) franjaDeTrabajoCrudRepository.findByFecha(fechaLaborable);
    } 
}
