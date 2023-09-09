package co.edu.unisabana.reservas.Reservaciones.persistence.logica;

import co.edu.unisabana.reservas.Reservaciones.Repositorios.FranjaDeTrabajoRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FranjaDeTrabajoService {

    @Autowired
    private FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    public FranjaDeTrabajo crearFranjaDeTrabajo(FranjaDeTrabajo franjaDeTrabajo) {
        return franjaDeTrabajoRepository.save(franjaDeTrabajo);
    }

    public List<FranjaDeTrabajo> consultarDisponibilidad(LocalDate fecha, LocalTime horaDeseada) {

        List<FranjaDeTrabajo> franjasDelDia = franjaDeTrabajoRepository.findByFecha(fecha);

        List<FranjaDeTrabajo> franjasDisponibles = franjasDelDia.stream()
                .filter(franja -> horaDeseada.isAfter(franja.getHoraInicio()) && horaDeseada.isBefore(franja.getHoraFin()))
                .toList();

        return franjasDisponibles;
    }

    public FranjaDeTrabajo actualizarFranjaDeTrabajo(Long id, FranjaDeTrabajo nuevaFranjaDeTrabajo) throws ChangeSetPersister.NotFoundException {

        FranjaDeTrabajo franjaExistente = franjaDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        franjaExistente.setFechaLaborable(nuevaFranjaDeTrabajo.getFechaLaborable());
        franjaExistente.setHoraInicio(nuevaFranjaDeTrabajo.getHoraInicio());
        franjaExistente.setHoraFin(nuevaFranjaDeTrabajo.getHoraFin());

        return franjaDeTrabajoRepository.save(franjaExistente);
    }

    public FranjaDeTrabajo obtenerFranjaDeTrabajoPorId(Long id) throws ChangeSetPersister.NotFoundException {
        return franjaDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public List<FranjaDeTrabajo> obtenerTodasLasFranjasDeTrabajo() {
        return franjaDeTrabajoRepository.findAll();
    }

}