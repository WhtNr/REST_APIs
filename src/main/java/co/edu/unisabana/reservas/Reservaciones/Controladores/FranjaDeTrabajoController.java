package co.edu.unisabana.reservas.Reservaciones.Controladores;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.FranjaDeTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/franja-de-trabajo")
public class FranjaDeTrabajoController {

    @Autowired
    private FranjaDeTrabajoService franjaDeTrabajoService;

    // Endpoint para crear una nueva configuración de franja de trabajo
    @PostMapping
    public ResponseEntity<FranjaDeTrabajo> crearFranjaDeTrabajo(@RequestBody FranjaDeTrabajo franjaDeTrabajo) {
        FranjaDeTrabajo nuevaFranja = franjaDeTrabajoService.crearFranjaDeTrabajo(franjaDeTrabajo);
        return new ResponseEntity<>(nuevaFranja, HttpStatus.CREATED);
    }

    // Endpoint para actualizar la configuración de franja de trabajo existente
    @PutMapping(path="/{id}")
    public ResponseEntity<FranjaDeTrabajo> actualizarFranjaDeTrabajo(@PathVariable Long id, @RequestBody FranjaDeTrabajo franjaDeTrabajo) throws ChangeSetPersister.NotFoundException {
        FranjaDeTrabajo franjaActualizada = franjaDeTrabajoService.actualizarFranjaDeTrabajo(id, franjaDeTrabajo);
        return new ResponseEntity<>(franjaActualizada, HttpStatus.OK);
    }
    @GetMapping("/disponibilidad")
    public ResponseEntity<List<FranjaDeTrabajo>> consultarDisponibilidad(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaDeseada) {

        try {
            // Agregar registros para rastrear la ejecución
            System.out.println("Solicitud de disponibilidad recibida para fecha: " + fecha + " y hora deseada: " + horaDeseada);

            // Lógica para consultar la disponibilidad en función de la fecha y la hora
            List<FranjaDeTrabajo> franjasDisponibles = franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada);

            // Agregar registros para verificar los resultados
            System.out.println("Franjas disponibles encontradas: " + franjasDisponibles.size());

            return new ResponseEntity<>(franjasDisponibles, HttpStatus.OK);
        } catch (Exception e) {
            // Manejar excepciones e imprimir detalles en los registros
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Otros métodos del controlador para consultar y gestionar la configuración de franja de trabajo
}
