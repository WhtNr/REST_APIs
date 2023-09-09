package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private long idCliente;

    private String nombre;

    private String apellido;

    private long celular;

    private String correo;


    @OneToMany(mappedBy = "cliente")
    private List<Asistencia> asistencias;
}
