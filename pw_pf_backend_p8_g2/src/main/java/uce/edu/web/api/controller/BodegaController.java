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

    //Consultar por ID, Código, Todos

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar Bodega por ID", description = "Esta capacidad permite consultar una bodega por su ID")
    public Response consultarPorId(@PathParam("id") Integer id) {
        BodegaTo bodega = BodegaMapper.toTo(this.bodegaService.seleccionarPorId(id));
        return Response.status(227).entity(bodega).build();
    }

    @GET
    @Path("/codigo/{codigo}")
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

    //Actualizar por ID, Código, Parcial por ID, Parcial por Código

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar Bodega por ID", description = "Esta capacidad permite actualizar una bodega por su ID")
    public Response actualizarPorId(@PathParam("id") Integer id, @RequestBody BodegaTo bodegaTo) {
        bodegaTo.setId(id);
        this.bodegaService.actualizarPorId(BodegaMapper.toBodega(bodegaTo));
        return Response.status(204).build();
    }

    @PUT
    @Path("/codigo/{codigo}")
    @Operation(summary = "Actualizar Bodega por Código", description = "Esta capacidad permite actualizar una bodega por su código")
    public Response actualizarPorCodigo(@PathParam("codigo") Integer codigo, @RequestBody BodegaTo bodegaTo) {
        bodegaTo.setCodigo(codigo);
        this.bodegaService.actualizarPorCodigo(BodegaMapper.toBodega(bodegaTo));
        return Response.status(204).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar parcialmente Bodega por ID", description = "Esta capacidad permite actualizar parcialmente una bodega por su ID")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody BodegaTo bodegaTo) {
        bodegaTo.setId(id);
        this.bodegaService.actualizarParcialPorId(BodegaMapper.toBodega(bodegaTo));
        bodegaTo.setId(id);
        return Response.status(204).build();
    }

    @PATCH
    @Path("/codigo/{codigo}")
    @Operation(summary = "Actualizar parcialmente Bodega por Código", description = "Esta capacidad permite actualizar parcialmente una bodega por su código")
    public Response actualizarParcialPorCodigo(@PathParam("codigo") Integer codigo,@RequestBody BodegaTo bodegaTo) {
        bodegaTo.setCodigo(codigo);
        this.bodegaService.actualizarParcialPorCodigo(BodegaMapper.toBodega(bodegaTo));
        bodegaTo.setCodigo(codigo); 
        return Response.status(204).build();
    }


    //Insertar y Eliminar por ID, Código
    
    @POST
    @Path("")
    @Operation(summary = "Insertar nueva Bodega", description = "Esta capacidad permite insertar una nueva bodega")
    public Response insertar(BodegaTo bodegaTo) {
        this.bodegaService.insertar(BodegaMapper.toBodega(bodegaTo));
        return Response.status(201).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar Bodega por ID", description = "Esta capacidad permite eliminar una bodega por su ID")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.bodegaService.eliminarPorId(id);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/codigo/{codigo}")
    @Operation(summary = "Eliminar Bodega por Código", description = "Esta capacidad permite eliminar una bodega por su código")
    public Response eliminarPorCodigo(@PathParam("codigo") Integer codigo) {
        this.bodegaService.eliminarPorCodigo(codigo);
        return Response.status(204).build();
    }

}
