package unit.persistence.logica;

import co.edu.unisabana.reservas.Reservaciones.domain.repository.CitaRepository;
import co.edu.unisabana.reservas.Reservaciones.domain.repository.FranjaDeTrabajoRepository;
import co.edu.unisabana.reservas.Reservaciones.domain.service.CitaService;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    @InjectMocks
    private CitaService citaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testVerificarDisponibilidad_Disponible() {
        LocalDate fecha = LocalDate.now();
        LocalTime horaInicio = LocalTime.of(10, 0);
        LocalTime horaFin = LocalTime.of(11, 0);

        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>();
        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        franja.setHoraInicio(LocalTime.of(9, 0));
        franja.setHoraFin(LocalTime.of(12, 0));
        franjasDelDia.add(franja);

        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);
        when(citaRepository.findByFechaAndHoraInicioBetween(fecha, horaInicio, horaFin)).thenReturn(new ArrayList<>());

        boolean disponible = citaService.verificarDisponibilidad(fecha, horaInicio, horaFin);

        assertTrue(disponible);
    }

    @Test
    public void testVerificarDisponibilidad_NoDisponible() {
        LocalDate fecha = LocalDate.now();
        LocalTime horaInicio = LocalTime.of(10, 0);
        LocalTime horaFin = LocalTime.of(11, 0);

        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>();
        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        franja.setHoraInicio(LocalTime.of(9, 0));
        franja.setHoraFin(LocalTime.of(12, 0));
        franjasDelDia.add(franja);

        List<Cita> citasExisten = new ArrayList<>();
        citasExisten.add(new Cita());

        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);
        when(citaRepository.findByFechaAndHoraInicioBetween(fecha, horaInicio, horaFin)).thenReturn(citasExisten);

        boolean disponible = citaService.verificarDisponibilidad(fecha, horaInicio, horaFin);

        assertFalse(disponible);
    }

    // Agrega más pruebas para los otros métodos según sea necesario
}
