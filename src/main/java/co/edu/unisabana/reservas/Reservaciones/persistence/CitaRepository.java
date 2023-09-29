package co.edu.unisabana.reservas.Reservaciones.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unisabana.reservas.Reservaciones.persistence.crud.CitaCrudRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;

@Repository
public class CitaRepository{
    
    private CitaCrudRepository citaCrudRepository;

    public List<Cita> GetCita(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin){
        return (List<Cita>) citaCrudRepository.findByFechaAndHoraInicioBetween(fecha, horaInicio, horaFin);
    }
}
