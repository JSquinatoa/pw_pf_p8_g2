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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.service.IProductoService;
import uce.edu.web.api.service.mapper.ImpuestoMapper;
import uce.edu.web.api.service.mapper.ProductoMapper;
import uce.edu.web.api.service.to.ImpuestoTo;
import uce.edu.web.api.service.to.ProductoTo;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoController {

    @Inject
    private IProductoService productoService;

    @GET
    @Path("/{codigoBarras}")
    @Operation(summary = "Consultar Productos por id (codigo de barras)", description = "Esta capacidad permite consultar un producto por su Id")
    public Response buscarPorIdProducto(@PathParam("codigoBarras") Integer codigoBarras, @Context UriInfo uriInfo) {
        ProductoTo prodTo = ProductoMapper.toTo(this.productoService.buscarPorId(codigoBarras));
        prodTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(prodTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas los Productos", description = "Esta capacidad permite consultar todos las productos")
    public Response buscarTodosProductos(@Context UriInfo uriInfo) {
        List<ProductoTo> prodToList = this.productoService.buscarTodos().stream()
                .map(ProductoMapper::toTo)
                .peek(productoTo -> productoTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(prodToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear Producto", description = "Esta capacidad permite crear un producto")
    public Response guardarProducto(@RequestBody ProductoTo productoTo) {
        this.productoService.guardar(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Completo por Id", description = "Esta capacidad permite actualizar completamente un producto por su Id")
    public Response actualizarPorIdProducto(@RequestBody ProductoTo productoTo,
            @PathParam("codigoBarras") Integer codigoBarras) {
        productoTo.setCodigoBarras(codigoBarras);
        this.productoService.actualizarPorId(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Parcial por Código de Barras")
    public Response actualizarParcialPorIdProducto(
            @PathParam("codigoBarras") Integer codigoBarras,
            @RequestBody ProductoTo productoTo) {

        ProductoTo productoExistenteTo = ProductoMapper.toTo(
                this.productoService.buscarPorId(codigoBarras));

        if (productoExistenteTo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (productoTo.getNombre() != null) {
            productoExistenteTo.setNombre(productoTo.getNombre());
        }
        if (productoTo.getCategoria() != null) {
            productoExistenteTo.setCategoria(productoTo.getCategoria());
        }
        if (productoTo.getStock() != null) {
            productoExistenteTo.setStock(productoTo.getStock());
        }
        if (productoTo.getPrecio() != null) {
            productoExistenteTo.setPrecio(productoTo.getPrecio());
        }

        this.productoService.actualizarParcialPorId(
                ProductoMapper.toEntity(productoExistenteTo));

        return Response.status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("/{codigoBarras}")
    @Operation(summary = "Borrar Producto por Id", description = "Esta capacidad permite borrar un producto por su Id")
    public Response borrarProductoConImpuestosPorIdProducto(@PathParam("codigoBarras") Integer codigoBarras) {
        this.productoService.borrarProductoConImpuestosPorId(codigoBarras);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{codigoBarras}/impuestos")
    @Operation(summary = "Consultar Impuestos de un Producto", description = "Esta capacidad permite consultar los impuestos asociados a un producto por su Id")
    public Response obtenerImpuestosPorProducto(@PathParam("codigoBarras") Integer codigoBarras) {
        List<ImpuestoTo> impuToList = this.productoService.buscarImpuestosPorProducto(codigoBarras).stream()
                .map(ImpuestoMapper::toTo).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(impuToList).build();
    }

    @POST
    @Path("/{codigoBarras}/impuestos/{impuestoId}")
    @Operation(summary = "Asociar un impuesto a un producto", description = "Esta capacidad permite asocia un impuesto existente a un producto existente")
    public Response asociarImpuestoAProducto(@PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("impuestoId") Integer impuestoId) {
        try {
            productoService.guardarImpuestoAProducto(codigoBarras, impuestoId);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{codigoBarras}/impuestos/{impuestoId}")
    @Operation(summary = "Eliminar impuesto de un producto", description = "Esta capacidad elimina la asociación entre un producto y un impuesto específico")
    public Response eliminarImpuestoDeProducto(@PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("impuestoId") Integer impuestoId) {
        productoService.borrarImpuestoDeProducto(codigoBarras, impuestoId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{codigoBarras}/impuestos/{impuestoIdActual}")
    @Operation(summary = "Actualizar impuesto de un producto", description = "Reemplaza un impuesto asociado a un producto por otro impuesto")
    public Response actualizarImpuestoDeProducto(
            @PathParam("codigoBarras") Integer codigoBarras,
            @PathParam("impuestoIdActual") Integer impuestoIdActual,
            @QueryParam("nuevoImpuestoId") Integer nuevoImpuestoId) {     
            productoService.actualizarImpuestoDeProducto(codigoBarras, impuestoIdActual, nuevoImpuestoId);
            return Response.status(Response.Status.NO_CONTENT).build(); 
    }

}
