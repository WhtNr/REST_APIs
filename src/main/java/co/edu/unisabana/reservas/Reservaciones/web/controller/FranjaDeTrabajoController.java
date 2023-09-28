package co.edu.unisabana.reservas.Reservaciones.web.controller;

import co.edu.unisabana.reservas.Reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
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

    private final FranjaDeTrabajoService franjaDeTrabajoService;

    public FranjaDeTrabajoController(FranjaDeTrabajoService franjaDeTrabajoService) {
        this.franjaDeTrabajoService = franjaDeTrabajoService;
    }

    @PostMapping
    public ResponseEntity<FranjaDeTrabajo> crearFranjaDeTrabajo(@RequestBody FranjaDeTrabajo franjaDeTrabajo) {
        FranjaDeTrabajo nuevaFranja = franjaDeTrabajoService.crearFranjaDeTrabajo(franjaDeTrabajo);
        return new ResponseEntity<>(nuevaFranja, HttpStatus.CREATED);
    }
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
            System.out.println("Solicitud de disponibilidad recibida para fecha: " + fecha + " y hora deseada: " + horaDeseada);

            List<FranjaDeTrabajo> franjasDisponibles = franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada);

            System.out.println("Franjas disponibles encontradas: " + franjasDisponibles.size());

            return new ResponseEntity<>(franjasDisponibles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
