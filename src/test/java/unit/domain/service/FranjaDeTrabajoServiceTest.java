package unit.domain.service;

import co.edu.unisabana.reservas.Reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.Reservaciones.domain.repository.FranjaDeTrabajoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FranjaDeTrabajoServiceTest {

    @Mock
    private FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    private FranjaDeTrabajoService franjaDeTrabajoService;

    @BeforeEach
    public void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            franjaDeTrabajoService = new FranjaDeTrabajoService(franjaDeTrabajoRepository);
        } catch (Exception e) {
            // Maneja cualquier excepción si ocurre
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
        LocalTime horaDeseada = LocalTime.of(10, 0); // Hora deseada
        List<FranjaDeTrabajo> franjasDelDia = new ArrayList<>(); // Crear una lista de franjas de trabajo de prueba
        // Agregar franjas de trabajo a la lista

        when(franjaDeTrabajoRepository.findByFecha(fecha)).thenReturn(franjasDelDia);

        List<FranjaDeTrabajo> resultado = franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada);

        // Realiza las aserciones necesarias para verificar que el método funciona correctamente.


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