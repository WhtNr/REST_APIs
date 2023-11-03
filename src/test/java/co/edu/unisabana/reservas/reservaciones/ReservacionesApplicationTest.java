package co.edu.unisabana.reservas.reservaciones;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;


@SpringBootTest(classes = ReservacionesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservacionesApplicationTest {
    @Test
    void contextLoads() {
        try {
            ReservacionesApplication.main(new String[] {});
        } catch (Exception e) {

            fail("La aplicación no se cargó correctamente: " + e.getMessage());
        }
    }

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();


}

