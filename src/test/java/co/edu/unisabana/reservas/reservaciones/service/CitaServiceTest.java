package co.edu.unisabana.reservas.reservaciones.service;

import co.edu.unisabana.reservas.reservaciones.domain.repository.CitaRepository;
import co.edu.unisabana.reservas.reservaciones.domain.repository.FranjaDeTrabajoRepository;
import co.edu.unisabana.reservas.reservaciones.domain.service.CitaService;
import co.edu.unisabana.reservas.reservaciones.domain.service.ReprogramacionCitaRequest;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void reprogramarCita_CitaExiste_DebeRetornarTrue() {
        when(citaRepository.findById(anyLong())).thenReturn(Optional.of(new Cita()));

        boolean result = citaService.reprogramarCita(1L, new ReprogramacionCitaRequest());

        assertTrue(result);
    }

    @Test
    public void reprogramarCita_CitaNoExiste_DebeRetornarFalse() {
        when(citaRepository.findById(anyLong())).thenReturn(Optional.empty());
        boolean result = citaService.reprogramarCita(1L, new ReprogramacionCitaRequest());
        assertFalse(result);
    }

    @Test
    public void verificarDisponibilidad_FranjaDisponible_DebeRetornarTrue() {

        LocalDate fecha = LocalDate.of(2023, 9, 28);
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaFin = LocalTime.of(11, 0);
        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        franja.setFechaLaborable(fecha);
        franja.setHoraInicio(LocalTime.of(8, 0));
        franja.setHoraFin(LocalTime.of(12, 0));
        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>();
        franjasDelDia.add(franja);


        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);


        when(citaRepository.findByFechaAndHoraInicioBetween(eq(fecha), any(LocalTime.class), any(LocalTime.class)))
                .thenReturn(new ArrayList<>());


        boolean resultado = citaService.verificarDisponibilidad(fecha, horaInicio, horaFin);


        assertTrue(resultado);
    }

    @Test
    public void verificarDisponibilidad_FranjaNoDisponible_DebeRetornarFalse() {

        LocalDate fecha = LocalDate.of(2023, 9, 28);
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaFin = LocalTime.of(11, 0);
        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        franja.setFechaLaborable(fecha);
        franja.setHoraInicio(LocalTime.of(8, 0));
        franja.setHoraFin(LocalTime.of(12, 0));
        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>();
        franjasDelDia.add(franja);


        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);


        Cita citaExistente = new Cita();
        when(citaRepository.findByFechaAndHoraInicioBetween(eq(fecha), any(LocalTime.class), any(LocalTime.class)))
                .thenReturn(List.of(citaExistente));


        boolean resultado = citaService.verificarDisponibilidad(fecha, horaInicio, horaFin);


        assertFalse(resultado);
    }

    @Test
    public void programarCita_CitaNoProgramada_EstadoCambiadoYGuardado() {

        Cita cita = new Cita();
        cita.setEstado(false);


        when(citaRepository.save(cita)).thenReturn(cita);


        citaService.programarCita(cita);


        assertTrue(cita.getEstado());


        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void programarCita_CitaYaProgramada_EstadoNoCambiadoYGuardado() {

        Cita cita = new Cita();
        cita.setEstado(true);


        when(citaRepository.save(cita)).thenReturn(cita);

        citaService.programarCita(cita);


        assertTrue(cita.getEstado());


        verify(citaRepository, times(1)).save(cita);
    }


}
