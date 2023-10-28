package co.edu.unisabana.reservas.reservaciones.domain.service;

import co.edu.unisabana.reservas.reservaciones.domain.repository.FranjaDeTrabajoRepository;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FranjaDeTrabajoService {

    private final FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    public FranjaDeTrabajoService(FranjaDeTrabajoRepository franjaDeTrabajoRepository) {
        this.franjaDeTrabajoRepository = franjaDeTrabajoRepository;
    }

    public FranjaDeTrabajo crearFranjaDeTrabajo(FranjaDeTrabajo franjaDeTrabajo) {
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

        return franjaDeTrabajoRepository.save(franjaExistente);
    }



}
