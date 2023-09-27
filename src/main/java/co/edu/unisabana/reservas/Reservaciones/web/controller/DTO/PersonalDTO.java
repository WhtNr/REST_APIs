package co.edu.unisabana.reservas.Reservaciones.web.controller.DTO;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Personal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

    // Método estático para convertir una lista de entidades Personal a una lista de DTOs
    public static List<PersonalDTO> toPersonalDTOList(List<Personal> personalList) {
        return personalList.stream().map(PersonalDTO::new).collect(Collectors.toList());
    }
}