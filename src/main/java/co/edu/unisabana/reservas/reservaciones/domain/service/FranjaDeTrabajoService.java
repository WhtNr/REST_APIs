package co.edu.unisabana.reservas.reservaciones.domain.service;

import co.edu.unisabana.reservas.reservaciones.domain.repository.FranjaDeTrabajoRepository;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.reservaciones.web.controller.dto.FranjaDeTrabajoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j

public class FranjaDeTrabajoService {

    private final FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    public FranjaDeTrabajoService(FranjaDeTrabajoRepository franjaDeTrabajoRepository) {
        this.franjaDeTrabajoRepository = franjaDeTrabajoRepository;
    }

    public FranjaDeTrabajo crearFranjaDeTrabajo(FranjaDeTrabajo franjaDeTrabajo) {
        log.warn("Se creo la franja de trabajo el dia {} de {} hasta las {}",franjaDeTrabajo.getFechaLaborable(),franjaDeTrabajo.getHoraInicio(),franjaDeTrabajo.getHoraFin());
        return franjaDeTrabajoRepository.save(franjaDeTrabajo);
    }

    public List<FranjaDeTrabajo> consultarDisponibilidad(LocalDate fecha, LocalTime horaDeseada) {
        List<FranjaDeTrabajo> franjasDelDia = franjaDeTrabajoRepository.findByFecha(fecha);

        return franjasDelDia.stream()
                .filter(franja -> horaDeseada.isAfter(franja.getHoraInicio()) && horaDeseada.isBefore(franja.getHoraFin()))
                .toList();
    }

    public FranjaDeTrabajo actualizarFranjaDeTrabajo(Long id, FranjaDeTrabajo nuevaFranjaDeTrabajo) throws ChangeSetPersister.NotFoundException {

        FranjaDeTrabajo franjaExistente = franjaDeTrabajoRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        franjaExistente.setFechaLaborable(nuevaFranjaDeTrabajo.getFechaLaborable());
        franjaExistente.setHoraInicio(nuevaFranjaDeTrabajo.getHoraInicio());
        franjaExistente.setHoraFin(nuevaFranjaDeTrabajo.getHoraFin());
        log.warn("Se actualizo la franja de trabajo {}",id);
        return franjaDeTrabajoRepository.save(franjaExistente);
    }



}
