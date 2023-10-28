package co.edu.unisabana.reservas.reservaciones;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ReservacionesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")

public class ReservacionesApplicationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();




}

