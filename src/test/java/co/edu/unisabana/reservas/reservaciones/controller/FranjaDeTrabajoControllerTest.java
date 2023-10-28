package co.edu.unisabana.reservas.reservaciones.controller;
import co.edu.unisabana.reservas.reservaciones.ReservacionesApplication;
import co.edu.unisabana.reservas.reservaciones.domain.service.FranjaDeTrabajoService;
import co.edu.unisabana.reservas.reservaciones.persistence.entity.FranjaDeTrabajo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ReservacionesApplication.class)
@Transactional
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class FranjaDeTrabajoControllerTest {

    @MockBean
    private FranjaDeTrabajoService franjaDeTrabajoService;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

        @Test
    public void testCrearFranjaDeTrabajo() throws Exception {

        FranjaDeTrabajo franja = new FranjaDeTrabajo();
        when(franjaDeTrabajoService.crearFranjaDeTrabajo(argThat(franja::equals))).thenReturn(franja);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/franja-de-trabajo")
                        .content(asJsonString(franja))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated());


        verify(franjaDeTrabajoService, times(1)).crearFranjaDeTrabajo(argThat(franja::equals));
    }

    @Test
    public void testConsultarDisponibilidad() throws Exception {
        LocalDate fecha = LocalDate.now();
        LocalTime horaDeseada = LocalTime.now();
        List<FranjaDeTrabajo> franjasDisponibles = new ArrayList<>();
        when(franjaDeTrabajoService.consultarDisponibilidad(fecha, horaDeseada)).thenReturn(franjasDisponibles);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/franja-de-trabajo/disponibilidad")
                        .param("fecha", fecha.toString())
                        .param("horaDeseada", horaDeseada.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


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