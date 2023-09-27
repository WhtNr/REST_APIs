package co.edu.unisabana.reservas.Reservaciones.web.controller.DTO;

import co.edu.unisabana.reservas.Reservaciones.persistence.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

    // Método estático para convertir una lista de entidades Cliente a una lista de DTOs
    public static List<ClienteDTO> toClienteDTOList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

}
