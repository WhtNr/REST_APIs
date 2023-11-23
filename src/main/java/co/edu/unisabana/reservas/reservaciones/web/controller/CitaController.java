package co.edu.unisabana.reservas.reservaciones.web.controller;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.reservaciones.domain.repository.CitaRepository;
import co.edu.unisabana.reservas.reservaciones.domain.service.CitaService;
import co.edu.unisabana.reservas.reservaciones.domain.service.ReprogramacionCitaRequest;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.EstadoCancelada;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.EstadoReprogramada;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.EstadoProgramada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;
    private final CitaRepository citaRepository;

    public CitaController(CitaService citaService, CitaRepository citaRepository) {
        this.citaService = citaService;
        this.citaRepository = citaRepository;
    }
    @PostMapping("/programar")
    public ResponseEntity<String> programarCita(@RequestBody Cita cita) {
        LocalDate fecha = cita.getFecha();
        LocalTime horaInicio = cita.getHoraInicio();
        LocalTime horaFin = cita.getHoraFin();

        if (citaService.verificarDisponibilidad(fecha, horaInicio, horaFin)) {
            try {
                cita.setEstado(new EstadoReprogramada());
                cita.programarCita();
                citaService.programarCita(cita);
                return ResponseEntity.ok("Cita programada con éxito.");
            } catch (Exception e) {
                log.error("Error al programar la cita {}", cita.getFecha());
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
            Cita cita = citaOptional.get();
            cita.setEstado(new EstadoCancelada());
            cita.cancelarCita();
            citaRepository.deleteById(idCita);
            log.warn("Se elimina la cita {} ",idCita);
            return ResponseEntity.ok("Cita eliminada con éxito.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cita con el ID proporcionado.");
        }
    }

    @PutMapping("/reprogramar/{idCita}")
    public ResponseEntity<String> reprogramarCita(@PathVariable Long idCita, @RequestBody ReprogramacionCitaRequest request) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            // Cambia el estado antes de reprogramar la cita
            cita.setEstado(new EstadoProgramada());
            cita.reprogramarCita(); // Esto llama al método retrogradarCita en el estado actual
            citaService.reprogramarCita(idCita, request);
            return ResponseEntity.ok("Cita reprogramada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no se encontró.");
        }
    }




}
