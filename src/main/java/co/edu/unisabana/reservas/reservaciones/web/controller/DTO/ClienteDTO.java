package co.edu.unisabana.reservas.reservaciones.web.controller.DTO;

import co.edu.unisabana.reservas.reservaciones.persistence.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private long idCliente;
    private String nombre;
    private String apellido;
    private long celular;
    private String correo;

    // Constructor
    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.celular = cliente.getCelular();
        this.correo = cliente.getCorreo();
    }



}
