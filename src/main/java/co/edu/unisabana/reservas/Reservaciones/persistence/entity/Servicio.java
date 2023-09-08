package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    private String nombre;

    private Integer duracion;

    private Integer precio;

    private String descripcion;


    @OneToMany(mappedBy = "servicio")
    private List<Turno> turnos;
}
