package co.edu.unisabana.reservas.Reservaciones;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(classes = ReservacionesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservacionesApplicationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();




}

