package co.edu.unisabana.reservas.reservaciones.persistence.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.estado.EstadoCita;
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
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "estado_actual")
    private String estadoActual;

    private LocalDate fecha;


    @OneToMany(mappedBy = "cita")
    private List<Asistencia> asistencias;

    @ManyToOne
    @JoinColumn(name = "id_personal", insertable = false, updatable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "id_servicio", insertable = false, updatable = false)
    private Servicio servicio;

    @Transient
    private EstadoCita estado;
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
        if (estado != null) {
            this.estadoActual = estado.obtenerEstadoActualComoCadena();
        }
    }

    public String getObtenerEstadoActualComoCadena() {
        return estadoActual;
    }

    public void programarCita() {
        estado.programarCita(this);
    }

    public void cancelarCita() {
        estado.cancelarCita(this);
    }

    public void reprogramarCita() {
        estado.reprogramarCita(this);
    }
}
