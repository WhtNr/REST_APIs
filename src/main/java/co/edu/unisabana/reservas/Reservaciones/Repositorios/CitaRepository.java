package co.edu.unisabana.reservas.Reservaciones.Repositorios;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
