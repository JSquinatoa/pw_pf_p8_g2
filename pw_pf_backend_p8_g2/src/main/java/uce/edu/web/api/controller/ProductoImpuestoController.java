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
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;
import uce.edu.web.api.service.IProductoImpuestoService;
import uce.edu.web.api.service.mapper.ProductoImpuestoMapper;
import uce.edu.web.api.service.to.ProductoImpuestoTo;

@Path("/productos-impuestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoImpuestoController {

    @Inject
    private IProductoImpuestoService productoImpuestoService;

    @GET
    @Path("")
    @Operation(summary = "Listar todas las relaciones", description = "Obtiene todas las relaciones entre productos e impuestos")
    public Response listarTodos() {
        List<ProductoImpuestoTo> lista = this.productoImpuestoService.buscarTodos()
                .stream()
                .map(ProductoImpuestoMapper::toTo)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @GET
    @Path("/producto/{codigoBarras}")
    @Operation(summary = "Buscar por producto", description = "Obtiene todas las relaciones para un producto específico")
    public Response buscarPorProducto(@PathParam("codigoBarras") Integer codigoBarras) {
        List<ProductoImpuestoTo> lista = this.productoImpuestoService.buscarPorProducto(codigoBarras)
                .stream()
                .map(ProductoImpuestoMapper::toTo)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @GET
    @Path("/impuesto/{idImpuesto}")
    @Operation(summary = "Buscar por impuesto", description = "Obtiene todas las relaciones para un impuesto específico")
    public Response buscarPorImpuesto(@PathParam("idImpuesto") Integer idImpuesto) {
        List<ProductoImpuestoTo> lista = this.productoImpuestoService.buscarPorImpuesto(idImpuesto)
                .stream()
                .map(ProductoImpuestoMapper::toTo)
                .collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear nueva relación", description = "Establece una nueva relación entre un producto y un impuesto")
    public Response crear(@RequestBody ProductoImpuestoTo productoImpuestoTo) {
        this.productoImpuestoService.crear(ProductoImpuestoMapper.toEntity(productoImpuestoTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/existe/producto/{codigoBarras}/impuesto/{idImpuesto}")
    @Operation(summary = "Verificar existencia", description = "Verifica si existe una relación entre un producto y un impuesto")
    public Response existeRelacion(
            @PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("idImpuesto") Integer idImpuesto) {
        boolean existe = this.productoImpuestoService.existeRelacion(codigoBarras, idImpuesto);
        return Response.ok(existe).build();
    }

    @PUT
    @Path("/producto/{codigoBarras}/impuesto/{idImpuesto}")
    @Operation(summary = "Actualizar relación completa", description = "Actualiza todos los campos de una relación producto-impuesto")
    public Response actualizarRelacion(
            @RequestBody ProductoImpuestoTo productoImpuestoTo,
            @PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("idImpuesto") Integer idImpuesto) {

        productoImpuestoTo.getProducto().setCodigoBarras(codigoBarras);
        productoImpuestoTo.getImpuesto().setId(idImpuesto);

        this.productoImpuestoService.actualizar(ProductoImpuestoMapper.toEntity(productoImpuestoTo));

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/producto/{codigoBarras}/impuesto/{idImpuesto}")
    @Operation(summary = "Actualizar relación parcialmente", description = "Actualiza solo los campos especificados de una relación producto-impuesto")
    public Response actualizarParcial(
            @PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("idImpuesto") Integer idImpuesto,
            @RequestBody ProductoImpuestoTo productoImpuestoTo) {
        try {
            boolean existe = this.productoImpuestoService.existeRelacion(codigoBarras, idImpuesto);

            if (!existe) {
                return Response.status(Response.Status.NOT_FOUND)
                               .build();
            }

            if (productoImpuestoTo.getProducto() == null) {
                productoImpuestoTo.setProducto(new Producto());
            }
            productoImpuestoTo.getProducto().setCodigoBarras(codigoBarras);

            if (productoImpuestoTo.getImpuesto() == null) {
                productoImpuestoTo.setImpuesto(new Impuesto());
            }
            productoImpuestoTo.getImpuesto().setId(idImpuesto);

            this.productoImpuestoService.actualizarParcial(ProductoImpuestoMapper.toEntity(productoImpuestoTo));

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno al actualizar la relación: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/producto/{codigoBarras}/impuesto/{idImpuesto}")
    @Operation(summary = "Eliminar relación", description = "Elimina una relación específica entre producto e impuesto")
    public Response eliminar(
            @PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("idImpuesto") Integer idImpuesto) {
        this.productoImpuestoService.eliminar(codigoBarras, idImpuesto);
        return Response.noContent().build();
    }
}