package co.edu.unisabana.reservas.Reservaciones.persistence.logica;

import co.edu.unisabana.reservas.Reservaciones.FranjaDeTrabajoRepository;
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

    // Método para crear una nueva configuración de franja de trabajo
    public FranjaDeTrabajo crearFranjaDeTrabajo(FranjaDeTrabajo franjaDeTrabajo) {
        // Agrega lógica de validación si es necesario
        // Por ejemplo, verifica que no haya solapamientos con franjas de trabajo existentes
        // Si pasa las validaciones, guarda la franja de trabajo en la base de datos
        return franjaDeTrabajoRepository.save(franjaDeTrabajo);
    }

    public List<FranjaDeTrabajo> consultarDisponibilidad(LocalDate fecha, LocalTime horaDeseada) {
        // Lógica para consultar la disponibilidad en función de la fecha y la hora
        // Supongamos que tienes una base de datos que almacena franjas de trabajo

        // Primero, obtén todas las franjas de trabajo para el día especificado
        List<FranjaDeTrabajo> franjasDelDia = franjaDeTrabajoRepository.findByFecha(fecha);

        // Ahora, filtra las franjas disponibles para la hora deseada
        List<FranjaDeTrabajo> franjasDisponibles = franjasDelDia.stream()
                .filter(franja -> horaDeseada.isAfter(franja.getHoraInicio()) && horaDeseada.isBefore(franja.getHoraFin()))
                .toList();

        return franjasDisponibles;
    }

    // Método para actualizar una configuración de franja de trabajo existente
    public FranjaDeTrabajo actualizarFranjaDeTrabajo(Long id, FranjaDeTrabajo nuevaFranjaDeTrabajo) throws ChangeSetPersister.NotFoundException {
        // Busca la franja de trabajo existente por su ID en la base de datos
        FranjaDeTrabajo franjaExistente = franjaDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Actualiza los campos necesarios de la franja de trabajo existente con los datos de la nueva franja
        franjaExistente.setFechaLaborable(nuevaFranjaDeTrabajo.getFechaLaborable());
        franjaExistente.setHoraInicio(nuevaFranjaDeTrabajo.getHoraInicio());
        franjaExistente.setHoraFin(nuevaFranjaDeTrabajo.getHoraFin());

        // Guarda la franja de trabajo actualizada en la base de datos
        return franjaDeTrabajoRepository.save(franjaExistente);
    }

    // Método para consultar una configuración de franja de trabajo por su ID
    public FranjaDeTrabajo obtenerFranjaDeTrabajoPorId(Long id) throws ChangeSetPersister.NotFoundException {
        return franjaDeTrabajoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    // Método para consultar todas las configuraciones de franja de trabajo
    public List<FranjaDeTrabajo> obtenerTodasLasFranjasDeTrabajo() {
        return franjaDeTrabajoRepository.findAll();
    }

    // Otros métodos de consulta y lógica de negocios según tus necesidades
}
