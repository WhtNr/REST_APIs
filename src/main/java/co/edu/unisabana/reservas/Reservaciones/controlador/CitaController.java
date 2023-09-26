package co.edu.unisabana.reservas.Reservaciones.controlador;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.CitaService;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.ReprogramacionCitaRequest;
import co.edu.unisabana.reservas.Reservaciones.repositorio.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaRepository citaRepository;

    @PostMapping("/programar")
    public ResponseEntity<String> programarCita(@RequestBody Cita cita) {
        LocalDate fecha = cita.getFecha();
        LocalTime horaInicio = cita.getHoraInicio();
        LocalTime horaFin = cita.getHoraFin();

        if (citaService.verificarDisponibilidad(fecha, horaInicio, horaFin)) {
            try {
                citaService.programarCita(cita);
                return ResponseEntity.ok("Cita programada con éxito.");
            } catch (Exception e) {
                // Manejar cualquier excepción que pueda ocurrir durante la programación de la cita
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Ocurrió un error al programar la cita.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La cita no está disponible.");
        }
    }


    @DeleteMapping("/cancelar/{idCita}")
    public ResponseEntity<String> cancelarCita(@PathVariable Long idCita) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);

        if (citaOptional.isPresent()) {
            citaRepository.deleteById(idCita); // Elimina la cita por su ID
            return ResponseEntity.ok("Cita eliminada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cita con el ID proporcionado.");
        }
    }

    @PutMapping("/reprogramar/{idCita}")
    public ResponseEntity<String> reprogramarCita(@PathVariable Long idCita, @RequestBody ReprogramacionCitaRequest request) {
        if (citaService.reprogramarCita(idCita, request)) {
            return ResponseEntity.ok("Cita reprogramada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no se encontró.");
        }
    }




}
