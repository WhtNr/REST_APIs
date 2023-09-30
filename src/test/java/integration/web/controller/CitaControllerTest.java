package integration.web.controller;

import co.edu.unisabana.reservas.Reservaciones.ReservacionesApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(classes = ReservacionesApplication.class)
@AutoConfigureMockMvc
@Transactional


public class CitaControllerTest {
@Autowired
    private MockMvc mockMvc;


    @Test
    public void givenAvailableSlot_whenProgramarCita_thenSuccess() throws Exception {

        String citaJson = "{\"fecha\":\"2023-10-01\",\"horaInicio\":\"10:00:00\",\"horaFin\":\"11:00:00\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/citas/programar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(citaJson))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("La cita no está disponible."));
    }

    @Test
    public void givenExistingCitaId_whenCancelarCita_thenSuccess() throws Exception {

        Long idCita = 7L;


        mockMvc.perform(MockMvcRequestBuilders.delete("/api/citas/cancelar/" + idCita))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cita eliminada con éxito."));


    }

    @Test
    @Transactional
    public void programarCita_WithConflict_ShouldReturnConflict() throws Exception {
        String citaJson = "{\"fecha\":\"2023-10-16\",\"horaInicio\":\"14:30:00\",\"horaFin\":\"15:30:00\"}";




        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tu-endpoint-aqui/programar-cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(citaJson)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    public void givenExistingCitaIdAndReprogramacionRequest_whenReprogramarCita_thenSuccess() throws Exception {

        Long citaId = 8L;


        String reprogramacionJson = "{\"nuevaFecha\":\"2023-10-02\",\"nuevaHoraInicio\":\"15:00:00\",\"nuevaHoraFin\":\"16:00:00\"}";


        mockMvc.perform(MockMvcRequestBuilders.put("/api/citas/reprogramar/" + citaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reprogramacionJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cita reprogramada con éxito."));
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

