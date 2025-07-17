package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.service.IClienteService;
import uce.edu.web.api.service.mapper.ClienteMapper;
import uce.edu.web.api.service.to.ClienteTo;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    private IClienteService iClienteService;

    @GET
    @Path("/{cedula}")
    @Operation(summary = "Consultar un cliente por cedula", description = "Esta capacidad permite consultar un cliente por cedula")
    public Response consultarPorCedula(@PathParam("cedula") String cedula, @Context UriInfo uriInfo) {
        ClienteTo clieTo = ClienteMapper.toTo(this.iClienteService.buscarPorCedula(cedula));
        return Response.status(Response.Status.OK).entity(clieTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todos los clientes", description = "Esta capacidad permite consultar todos los clientes")
    public Response consultarTodos(@Context UriInfo uriInfo) {
        List<ClienteTo> clieToList = this.iClienteService.buscarTodos().stream().map(ClienteMapper::toTo)
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(clieToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Guarda un CLiente", description = "Esta capacidad permite guardar un cliente")
    public Response guardar(@RequestBody ClienteTo clienteTo) {
        this.iClienteService.guardar(ClienteMapper.toEntity(clienteTo));
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{cedula}")
    @Operation(summary = "Actualizar un Cliente por la cedula", description = "Esta capacidad me permite actualizar un cliente por su cedula")
    public Response actualizarPorCedula(@RequestBody ClienteTo clienteTo, @PathParam("cedula") String cedula) {
        clienteTo.setCedula(cedula);
        this.iClienteService.actualizarPorCedula(ClienteMapper.toEntity(clienteTo));
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{cedula}")
    @Operation(summary = "Actualizar un Cliente Parcialmente", description = "Esta capacidad me permite actualizar parcialemnte a un CLiente por su cedula")
    public Response actualizarParcialmentePorCedula(@RequestBody ClienteTo clienteTo,
            @PathParam("cedula") String cedula) {
                
        clienteTo.setCedula(cedula);
        ClienteTo cTo = ClienteMapper.toTo(this.iClienteService.buscarPorCedula(cedula));

        if (clienteTo.getNombre() != null) {
            cTo.setNombre(clienteTo.getNombre());
        }
        if (clienteTo.getApellido() != null) {
            cTo.setApellido(clienteTo.getApellido());
        }
        if (clienteTo.getRazonSocial() != null) {
            cTo.setRazonSocial(clienteTo.getRazonSocial());
        }
        if (clienteTo.getDireccion() != null) {
            cTo.setDireccion(clienteTo.getDireccion());
        }
        if (clienteTo.getTelefono() != null) {
            cTo.setTelefono(clienteTo.getTelefono());
        }
        if (clienteTo.getCorreo() != null) {
            cTo.setCorreo(clienteTo.getCorreo());
        }

        this.iClienteService.actualizarParcialPorCedula(ClienteMapper.toEntity(cTo));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{cedula}")
    @Operation(summary = "Borrar un cliente por su cedula", description = "Esta capacidad me permite eliminar a un Cliente por su cedula")
    public Response borrarPorCedula(@PathParam("cedula") String cedula) {
        this.iClienteService.borrarPorCedula(cedula);
        return Response.status(Response.Status.OK).build();
    }

}
