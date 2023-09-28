package co.edu.unisabana.reservas.Reservaciones;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ReservacionesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservacionesApplicationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	/*@Test
	public void testApplicationStartup() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
		HttpStatus statusCode = (HttpStatus) response.getStatusCode();
		assertEquals(HttpStatus.OK.value(), statusCode.value());
	}
}*/



}

