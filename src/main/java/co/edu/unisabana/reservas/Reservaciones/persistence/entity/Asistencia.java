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
    private long idAsistencia;

    @Column(name = "id_cliente")
    private long idCliente;

    @Column(name = "id_cita")
    private long idCita;

    private String estado;


    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_cita", insertable = false, updatable = false)
    private Cita cita;
}
