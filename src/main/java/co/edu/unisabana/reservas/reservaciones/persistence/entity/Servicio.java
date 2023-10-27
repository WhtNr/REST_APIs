package co.edu.unisabana.reservas.reservaciones.persistence.entity;

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
    private long idServicio;

    private String nombre;

    private Integer duracion;

    private long precio;

    private String descripcion;


    @OneToMany(mappedBy = "servicio")
    private List<Cita> citas;
}
