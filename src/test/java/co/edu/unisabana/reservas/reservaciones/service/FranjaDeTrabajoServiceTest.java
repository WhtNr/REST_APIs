package co.edu.unisabana.reservas.reservaciones.service;

import co.edu.unisabana.reservas.reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.reservaciones.domain.repository.FranjaDeTrabajoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class FranjaDeTrabajoServiceTest {

    @Mock
    private FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    private FranjaDeTrabajoService franjaDeTrabajoService;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            franjaDeTrabajoService = new FranjaDeTrabajoService(franjaDeTrabajoRepository);
        } catch (Exception e) {

        }
    }

    @Test
    public void givenFranjaDeTrabajoCuandoCrearEntoncesDevuelveLaFranjaDeTrabajoCreada() {
        FranjaDeTrabajo franjaDeTrabajo = new FranjaDeTrabajo();
        when(franjaDeTrabajoRepository.save(franjaDeTrabajo)).thenReturn(franjaDeTrabajo);

        FranjaDeTrabajo resultado = franjaDeTrabajoService.crearFranjaDeTrabajo(franjaDeTrabajo);

        assertEquals(franjaDeTrabajo, resultado);
    }


    @Test
    public void testConsultarDisponibilidad() {
        LocalDate fecha = LocalDate.now();
        LocalTime horaDeseada = LocalTime.of(10, 0);
        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>();


        FranjaDeTrabajo franja1 = new FranjaDeTrabajo();
        franja1.setHoraInicio(LocalTime.of(9, 0));
        franja1.setHoraFin(LocalTime.of(11, 0));
        franjasDelDia.add(franja1);

        FranjaDeTrabajo franja2 = new FranjaDeTrabajo();
        franja2.setHoraInicio(LocalTime.of(14, 0));
        franja2.setHoraFin(LocalTime.of(16, 0));
        franjasDelDia.add(franja2);


        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);

        List<FranjaDeTrabajo> resultado = franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada);


        assertTrue(resultado.contains(franja1));
        Assertions.assertFalse(resultado.contains(franja2));
    }


    @Test
    public void testActualizarFranjaDeTrabajo() throws ChangeSetPersister.NotFoundException {
        Long id = 1L;
        FranjaDeTrabajo nuevaFranjaDeTrabajo = new FranjaDeTrabajo();
        FranjaDeTrabajo franjaExistente = new FranjaDeTrabajo();

        when(franjaDeTrabajoRepository.findById(id)).thenReturn(java.util.Optional.of(franjaExistente));
        when(franjaDeTrabajoRepository.save(franjaExistente)).thenReturn(franjaExistente);

        FranjaDeTrabajo resultado = franjaDeTrabajoService.actualizarFranjaDeTrabajo(id, nuevaFranjaDeTrabajo);

    }

}