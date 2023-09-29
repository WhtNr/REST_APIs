package controller;

import co.edu.unisabana.reservas.Reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.FranjaDeTrabajo;
import co.edu.unisabana.reservas.Reservaciones.web.controller.FranjaDeTrabajoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = FranjaDeTrabajoController.class)

public class FranjaDeTrabajoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FranjaDeTrabajoService franjaDeTrabajoService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FranjaDeTrabajoController(franjaDeTrabajoService)).build();
    }



    @Test
    public void dadoFranjaDeTrabajoCuandoCrearEntoncesRetornaCreada() throws Exception {
        FranjaDeTrabajo franjaDeTrabajo = new FranjaDeTrabajo(); // Given: Crear una franja de trabajo de prueba
        when(franjaDeTrabajoService.crearFranjaDeTrabajo(franjaDeTrabajo)).thenReturn(franjaDeTrabajo);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/franja-de-trabajo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(franjaDeTrabajo)))
                .andExpect(MockMvcResultMatchers.status().isCreated()); // Then: Retorna 201 Created
    }

    @Test
    public void dadoIdFranjaDeTrabajoCuandoActualizarEntoncesRetornaOk() throws Exception {
        Long id = 1L; // Given: ID de la franja de trabajo existente
        FranjaDeTrabajo franjaDeTrabajo = new FranjaDeTrabajo(); // Given: Crear una franja de trabajo de prueba
        when(franjaDeTrabajoService.actualizarFranjaDeTrabajo(id, franjaDeTrabajo)).thenReturn(franjaDeTrabajo);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/franja-de-trabajo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(franjaDeTrabajo)))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Then: Retorna 200 OK
    }


    @Test
    public void dadoFechaYHoraDeseadaCuandoConsultarDisponibilidadExitosaEntoncesRetornaOk() throws Exception {
        // Datos de prueba
        LocalDate fecha = LocalDate.now();
        LocalTime horaDeseada = LocalTime.of(10, 0);

        // Simular el servicio para devolver una lista de franjas disponibles
        List<FranjaDeTrabajo> franjasDisponibles = new ArrayList<>();
        FranjaDeTrabajo franja1 = new FranjaDeTrabajo();
        FranjaDeTrabajo franja2 = new FranjaDeTrabajo();
        franjasDisponibles.add(franja1);
        franjasDisponibles.add(franja2);
        when(franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada)).thenReturn(franjasDisponibles);

        // Ejecutar la acción en el controlador
        mockMvc.perform(MockMvcRequestBuilders.get("/api/franja-de-trabajo/disponibilidad")
                        .param("fecha", fecha.toString())
                        .param("horaDeseada", horaDeseada.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Then: Retorna 200 OK
    }

    @Test
    public void dadoFechaYHoraDeseadaCuandoConsultarDisponibilidadConErrorEntoncesRetornaInternalServerError() throws Exception {
        // Datos de prueba
        LocalDate fecha = LocalDate.now();
        LocalTime horaDeseada = LocalTime.of(10, 0);

        // Simular el servicio para lanzar una excepción
        when(franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada)).thenThrow(new RuntimeException("Error en la consulta"));

        // Ejecutar la acción en el controlador
        mockMvc.perform(MockMvcRequestBuilders.get("/api/franja-de-trabajo/disponibilidad")
                        .param("fecha", fecha.toString())
                        .param("horaDeseada", horaDeseada.toString()))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()); // Then: Retorna 500 INTERNAL_SERVER_ERROR
    }

    // Método para convertir un objeto Java a JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
