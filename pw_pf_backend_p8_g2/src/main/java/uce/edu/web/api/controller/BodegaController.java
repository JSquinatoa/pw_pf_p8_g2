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
import uce.edu.web.api.service.IBodegaService;
import uce.edu.web.api.service.mapper.BodegaMapper;
import uce.edu.web.api.service.to.BodegaTo;

@Path("/bodegas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BodegaController {

    @Inject
    private IBodegaService bodegaService;

    @GET
    @Path("/{codigo}")
    @Operation(summary = "Consultar Bodega por Código", description = "Esta capacidad permite consultar una bodega por su código")
    public Response consularPorCodigo(@PathParam("codigo") Integer codigo) {
        BodegaTo bodega = BodegaMapper.toTo(this.bodegaService.seleccionarPorCodigo(codigo));
        return Response.status(227).entity(bodega).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas las Bodegas", description = "Esta capacidad permite consultar todas las bodegas")
    public Response consultarTodos() {
        List<BodegaTo> bodegas = this.bodegaService.seleccionarTodos().stream()
                .map(BodegaMapper::toTo)
                .collect(Collectors.toList());
        return Response.status(227).entity(bodegas).build();
    }

    @PUT
    @Path("/{codigo}")
    @Operation(summary = "Actualizar Bodega por Código", description = "Esta capacidad permite actualizar una bodega por su código")
    public Response actualizarPorCodigo(@PathParam("codigo") Integer codigo, BodegaTo bodegaTo) {
        bodegaTo.setCodigo(codigo);
        this.bodegaService.actualizarPorCodigo(BodegaMapper.toBodega(bodegaTo));
        return Response.status(204).build();
    }

    @PATCH
    @Path("/{codigo}")
    @Operation(summary = "Actualizar parcialmente Bodega por Código", description = "Esta capacidad permite actualizar parcialmente una bodega por su código")
    public Response actualizarParcialPorCodigo(@PathParam("codigo") Integer codigo, BodegaTo bodegaTo) {
        this.bodegaService.actualizarParcialPorCodigo(codigo, BodegaMapper.toBodega(bodegaTo));
        return Response.status(204).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Insertar nueva Bodega", description = "Esta capacidad permite insertar una nueva bodega")
    public Response insertar(BodegaTo bodegaTo) {
        this.bodegaService.insertar(BodegaMapper.toBodega(bodegaTo));
        return Response.status(201).build();
    }

    @DELETE
    @Path("/{codigo}")
    @Operation(summary = "Eliminar Bodega por Código", description = "Esta capacidad permite eliminar una bodega por su código")
    public Response eliminarPorCodigo(@PathParam("codigo") Integer codigo) {
        this.bodegaService.eliminarPorCodigo(codigo);
        return Response.status(204).build();
    }

}
