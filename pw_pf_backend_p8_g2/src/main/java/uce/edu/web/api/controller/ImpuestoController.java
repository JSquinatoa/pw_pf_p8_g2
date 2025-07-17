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
    public Response buscarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ImpuestoTo impuestoTo = ImpuestoMapper.toTo(this.impuestoService.buscarPorId(id));
        return Response.status(Response.Status.OK).entity(impuestoTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todos los Impuestos", description = "Permite consultar todos los impuestos")
    public Response buscarTodos(@Context UriInfo uriInfo) {
        List<ImpuestoTo> impuestosTo = this.impuestoService.buscarTodos().stream()
                .map(ImpuestoMapper::toTo)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(impuestosTo).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear Impuesto", description = "Permite crear un impuesto")
    public Response crear(@RequestBody ImpuestoTo impuestoTo) {
        this.impuestoService.guardar(ImpuestoMapper.toEntity(impuestoTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar Impuesto Completo por Id", description = "Permite actualizar completamente un impuesto por su Id")
    public Response actualizarPorId(@PathParam("id") Integer id, @RequestBody ImpuestoTo impuestoTo) {
        impuestoTo.setId(id);
        this.impuestoService.actualizarPorId(ImpuestoMapper.toEntity(impuestoTo));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar Impuesto Parcial por Id", description = "Permite actualizar parcialmente un impuesto por su Id")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody ImpuestoTo impuestoTo) {
        ImpuestoTo impuestoExistenteTo = ImpuestoMapper.toTo(this.impuestoService.buscarPorId(id));

        if (impuestoExistenteTo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (impuestoTo.getNombre() != null) {
            impuestoExistenteTo.setNombre(impuestoTo.getNombre());
        }
        if (impuestoTo.getPorcentaje() != null) {
            impuestoExistenteTo.setPorcentaje(impuestoTo.getPorcentaje());
        }

        this.impuestoService.actualizarPorId(ImpuestoMapper.toEntity(impuestoExistenteTo));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar Impuesto por Id", description = "Permite borrar un impuesto por su Id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.impuestoService.eliminarPorId(id);
        return Response.status(Response.Status.OK).build();
    }
}