package co.edu.unisabana.reservas.Reservaciones.web.controller;

import co.edu.unisabana.reservas.Reservaciones.domain.service.CitaService;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CitaControllerTest {

    @InjectMocks
    private CitaController citaController;

    @Mock
    private CitaService citaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void programarCita_DisponibilidadNoVerificada_Conflicto() {
        // Creamos una instancia de Cita y configuramos la verificación de disponibilidad para que sea falsa
        Cita cita = new Cita();
        LocalDate fecha = LocalDate.of(2023, 9, 28);
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaFin = LocalTime.of(11, 0);

        when(citaService.verificarDisponibilidad(fecha, horaInicio, horaFin)).thenReturn(false);

        // Llamamos al método que queremos probar
        ResponseEntity<String> respuesta = citaController.programarCita(cita);

        // Verificamos que la respuesta sea un ResponseEntity con código de conflicto (409)
        assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
        assertEquals("La cita no está disponible.", respuesta.getBody());

        // Verificamos que el método programarCita en el servicio no se haya llamado
        verify(citaService, never()).programarCita(cita);
    }


}

