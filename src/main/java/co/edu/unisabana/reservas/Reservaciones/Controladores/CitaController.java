package co.edu.unisabana.reservas.Reservaciones.Controladores;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.CitaService;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.ReprogramacionCitaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/programar")
    public ResponseEntity<String> programarCita(@RequestBody Cita cita) {
        LocalDate fecha = cita.getFecha();
        Time horaInicio = cita.getHoraInicio();
        // Puedes también obtener otros datos necesarios de la cita si es necesario

        if (citaService.verificarDisponibilidad(fecha, horaInicio)) {
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
    public ResponseEntity<String> cancelarCita(@PathVariable Integer idCita) {
        if (citaService.cancelarCita(idCita)) {
            return ResponseEntity.ok("Cita cancelada con éxito.");
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
