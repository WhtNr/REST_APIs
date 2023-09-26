package unit.persistence.logica;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import co.edu.unisabana.reservas.Reservaciones.persistence.logica.CitaService;
import co.edu.unisabana.reservas.Reservaciones.repositorio.CitaRepository;
import co.edu.unisabana.reservas.Reservaciones.repositorio.FranjaDeTrabajoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CitaServiceTest {
    @Mock
    private CitaRepository citaRepository;
    @Mock
    private FranjaDeTrabajoRepository franjaDeTrabajoRepository;

    @InjectMocks
    private CitaService citaservice;

    @Test
    void GivenWhenThen(){
        Cita cita = new Cita();

        citaservice.programarCita(cita);

        Mockito.verify(citaRepository).save(cita);

    }

}
