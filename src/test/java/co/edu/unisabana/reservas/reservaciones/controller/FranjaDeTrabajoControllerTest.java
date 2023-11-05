package co.edu.unisabana.reservas.reservaciones.controller;

import co.edu.unisabana.reservas.reservaciones.config.TestSecurityConfig;
import co.edu.unisabana.reservas.reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@Transactional
@AutoConfigureMockMvc

@SpringBootTest
@ContextConfiguration(classes = TestSecurityConfig.class)
class FranjaDeTrabajoControllerTest {

    @MockBean
    private FranjaDeTrabajoService franjaDeTrabajoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCrearFranjaDeTrabajo() throws Exception {

        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        when(franjaDeTrabajoService.crearFranjaDeTrabajo(argThat(franja::equals))).thenReturn(franja);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/franja-de-trabajo").content(asJsonString(franja)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());


        verify(franjaDeTrabajoService, times(1)).crearFranjaDeTrabajo(argThat(franja::equals));
    }

    @Test
    void testConsultarDisponibilidad() throws Exception {
        LocalDate fecha = LocalDate.now();
        LocalTime horaDeseada = LocalTime.now();
        List<FranjaDeTrabajo> franjasDisponibles = new ArrayList<>();
        when(franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada)).thenReturn(franjasDisponibles);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/franja-de-trabajo/disponibilidad").param("fecha", fecha.toString()).param("horaDeseada", horaDeseada.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());


        verify(franjaDeTrabajoService, times(1)).consultarDisponibilidad(fecha, horaDeseada);
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}