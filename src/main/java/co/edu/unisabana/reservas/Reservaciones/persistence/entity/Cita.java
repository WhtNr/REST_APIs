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
@Table(name = "cita")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private long idCita;

    @Column(name = "id_personal")
    private long idPersonal;

    @Column(name = "id_servicio")
    private long idServicio;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    private Date fecha;

    private Boolean estado;


    @OneToMany(mappedBy = "cita")
    private List<Asistencia> asistencias;

    @ManyToOne
    @JoinColumn(name = "id_personal", insertable = false, updatable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
}