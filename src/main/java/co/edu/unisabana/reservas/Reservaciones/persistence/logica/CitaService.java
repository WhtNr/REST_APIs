package co.edu.unisabana.reservas.Reservaciones.persistence.logica;

import co.edu.unisabana.reservas.Reservaciones.Repositorios.CitaRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public boolean verificarDisponibilidad(LocalDate fecha, Time horaDeseada) {
        List<Cita> citasExisten = citaRepository.findByFechaAndHoraInicioBetween(fecha, horaDeseada, horaDeseada);

        return citasExisten.isEmpty();
    }

    public void programarCita(Cita cita) {

        cita.setEstado(true);
        citaRepository.save(cita);

    }
    public boolean cancelarCita(long idCita) {

        Optional<Cita> citaOptional = citaRepository.findById(idCita);

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setEstado(false);
            citaRepository.save(cita);
            return true;
        } else {
            return false;
        }
    }

    public boolean reprogramarCita(Long idCita, ReprogramacionCitaRequest request) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setFecha(Date.valueOf(request.getNuevaFecha()).toLocalDate());
            cita.setHoraInicio(Time.valueOf(request.getNuevaHoraInicio()));
            cita.setHoraFin(Time.valueOf(request.getNuevaHoraFin()));
            citaRepository.save(cita);
            return true;
        } else {
            return false; // La cita no se encontró
        }
    }


}
