package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personal")
public class Personal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Integer idPersonal;

    private String nombre;

    private String apellido;

    private String especialidad;

    @OneToMany(mappedBy = "personal")
    private List<Turno> turnos;
}
