package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Cliente;
import uce.edu.web.api.service.to.ClienteTo;

public class ClienteMapper {

    public static ClienteTo toTo(Cliente cliente) {
        ClienteTo cTo = new ClienteTo();
        cTo.setCedula(cliente.getCedula());
        cTo.setNombre(cliente.getNombre());
        cTo.setApellido(cliente.getApellido());
        cTo.setRazonSocial(cliente.getRazonSocial());
        cTo.setDireccion(cliente.getDireccion());
        cTo.setTelefono(cliente.getTelefono());
        cTo.setCorreo(cliente.getCorreo());
        return cTo;
    }

    public static Cliente toEntity(ClienteTo clienteTo) {
        Cliente c = new Cliente();
        c.setCedula(clienteTo.getCedula());
        c.setNombre(clienteTo.getNombre());
        c.setApellido(clienteTo.getApellido());
        c.setRazonSocial(clienteTo.getRazonSocial());
        c.setDireccion(clienteTo.getDireccion());
        c.setTelefono(clienteTo.getTelefono());
        c.setCorreo(clienteTo.getCorreo());
        return c;
    }

}
