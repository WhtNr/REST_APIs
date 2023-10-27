package co.edu.unisabana.reservas.reservaciones.web.controller.DTO;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Personal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalDTO {

    private long idPersonal;
    private String nombre;
    private String apellido;
    private String especialidad;

    // Constructor
    public PersonalDTO(Personal personal) {
        this.idPersonal = personal.getIdPersonal();
        this.nombre = personal.getNombre();
        this.apellido = personal.getApellido();
        this.especialidad = personal.getEspecialidad();
    }


}