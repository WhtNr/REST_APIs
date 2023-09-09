package co.edu.unisabana.reservas.Reservaciones.Controladores;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Asistencia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservasControlador {
    @GetMapping(path="/saludame")
    public String saludar(){
        return "Hola mundo, desde la maquina de Brayan";
    }

}
