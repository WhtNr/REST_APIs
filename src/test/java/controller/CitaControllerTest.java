package controller;
import co.edu.unisabana.reservas.Reservaciones.ReservacionesApplication;
import co.edu.unisabana.reservas.Reservaciones.domain.repository.CitaRepository;
import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = ReservacionesApplication.class)
@AutoConfigureMockMvc
@Transactional

public class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenAvailableSlot_whenProgramarCita_thenSuccess() throws Exception {
        // Datos de la cita a programar
        String citaJson = "{\"fecha\":\"2023-10-01\",\"horaInicio\":\"10:00:00\",\"horaFin\":\"11:00:00\"}";

        // Realizar la solicitud POST para programar la cita
        mockMvc.perform(MockMvcRequestBuilders.post("/api/citas/programar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(citaJson))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("La cita no está disponible."));
    }

    @Test
    public void givenExistingCitaId_whenCancelarCita_thenSuccess() throws Exception {
        // Dado un ID de cita existente
        Long idCita = 7L;

        // Cuando se envía una solicitud DELETE para cancelar la cita
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/citas/cancelar/" + idCita))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cita eliminada con éxito."));


    }

    // Método para convertir un objeto a una cadena JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}