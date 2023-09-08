package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asistencia")
public class Asistencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_turno")
    private Integer idTurno;

    private String estado;


    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_turno", insertable = false, updatable = false)
    private Turno turno;
}
