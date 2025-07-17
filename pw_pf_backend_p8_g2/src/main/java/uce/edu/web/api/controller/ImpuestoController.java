package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.service.IImpuestoService;
import uce.edu.web.api.service.mapper.ImpuestoMapper;
import uce.edu.web.api.service.to.ImpuestoTo;

@Path("/impuestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImpuestoController {

    @Inject
    private IImpuestoService impuestoService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar Impuesto por Id", description = "Permite consultar un impuesto por su Id")
    public Response buscarPorId(@PathParam("id") Integer id) {
        Impuesto impuesto = this.impuestoService.buscarPorId(id);

        if (impuesto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ImpuestoTo impuestoTo = ImpuestoMapper.toTo(impuesto);

        return Response.ok(impuestoTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todos los Impuestos", description = "Permite consultar todos los impuestos")
    public Response buscarTodos() {
        List<Impuesto> listaEntidades = this.impuestoService.buscarTodos();

        List<ImpuestoTo> impuestosTo = listaEntidades.stream()
                .map(ImpuestoMapper::toTo)
                .collect(Collectors.toList());

        return Response.ok(impuestosTo).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear Impuesto", description = "Permite crear un impuesto")
    public Response crear(ImpuestoTo impuestoTo) {
        Impuesto impuesto = ImpuestoMapper.toEntity(impuestoTo);

        this.impuestoService.guardar(impuesto);

        ImpuestoTo createdImpuestoTo = ImpuestoMapper.toTo(impuesto);

        return Response.status(Response.Status.CREATED).entity(createdImpuestoTo).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar Impuesto por Id", description = "Permite actualizar un impuesto por su Id")
    public Response actualizarPorId(@PathParam("id") Integer id, ImpuestoTo impuestoTo) {
        impuestoTo.setId(id);

        Impuesto impuestoActualizar = ImpuestoMapper.toEntity(impuestoTo);

        this.impuestoService.actualizarPorId(impuestoActualizar);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar Impuesto Parcial por Id", description = "Permite actualizar parcialmente un impuesto por su Id")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, ImpuestoTo impuestoTo) {
        Impuesto impuestoExistente = this.impuestoService.buscarPorId(id);

        if (impuestoExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (impuestoTo.getNombre() != null) {
            impuestoExistente.setNombre(impuestoTo.getNombre());
        }
        if (impuestoTo.getPorcentaje() != null) {
            impuestoExistente.setPorcentaje(impuestoTo.getPorcentaje());
        }

        this.impuestoService.actualizarPorId(impuestoExistente);

        ImpuestoTo updatedImpuestoTo = ImpuestoMapper.toTo(impuestoExistente);
        return Response.status(Response.Status.OK).entity(updatedImpuestoTo).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar Impuesto por Id", description = "Permite borrar un impuesto por su Id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.impuestoService.eliminarPorId(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
