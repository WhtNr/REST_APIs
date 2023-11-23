package co.edu.unisabana.reservas.reservaciones.domain.service;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.reservaciones.domain.repository.CitaRepository;
import co.edu.unisabana.reservas.reservaciones.domain.repository.FranjaDeTrabajoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class CitaService {

    private final CitaRepository citaRepository;
    private final FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    public CitaService(CitaRepository citaRepository, FranjaDeTrabajoRepository franjaDeTrabajoRepository) {
        this.citaRepository = citaRepository;
        this.franjaDeTrabajoRepository = franjaDeTrabajoRepository;
    }

    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {

        List<FranjaDeTrabajo> franjasDelDia = franjaDeTrabajoRepository.findByFecha(fecha);

        for (FranjaDeTrabajo franja : franjasDelDia) {
            if (horaInicio.isAfter(franja.getHoraInicio()) && horaFin.isBefore(franja.getHoraFin())) {

                List<Cita> citasExisten = citaRepository.findByFechaAndHoraInicioBetween(fecha, horaInicio, horaFin);

                return citasExisten.isEmpty();
            }
        }


        return false;
    }


    public void programarCita(Cita cita) {
        cita.setEstado(cita.getEstado());
        log.warn("Nueva cita guardada el dia {} de {} a las {}, estado {}", cita.getFecha(),cita.getHoraInicio(),cita.getHoraFin(),cita.getObtenerEstadoActualComoCadena());
        citaRepository.save(cita);

    }

    public boolean reprogramarCita(Long idCita, ReprogramacionCitaRequest request) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setFecha(request.getNuevaFecha());
            cita.setHoraInicio(request.getNuevaHoraInicio());
            cita.setHoraFin(request.getNuevaHoraFin());
            cita.setEstado(cita.getEstado());
            citaRepository.save(cita);
            log.warn("la cita {} es reprogramada al dia {} a las {} estado {} ", idCita, cita.getFecha(), cita.getHoraFin(),cita.getObtenerEstadoActualComoCadena());
            return true;

        } else {
            log.warn("La cita {} no se encontro", idCita);
            return false; // La cita no se encontró

        }
    }


}
