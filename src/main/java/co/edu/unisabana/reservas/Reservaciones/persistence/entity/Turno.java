package co.edu.unisabana.reservas.Reservaciones.persistence.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turno")
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Integer idTurno;

    @Column(name = "id_personal")
    private Integer idPersonal;

    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    private Date fecha;

    private Boolean estado;


    @OneToMany(mappedBy = "turno")
    private List<Asistencia> asistencias;

    @ManyToOne
    @JoinColumn(name = "id_personal", insertable = false, updatable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
}
