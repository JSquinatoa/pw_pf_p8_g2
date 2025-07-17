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

    @Inject
    private IImpuestoService impuestoService;

    @GET
    @Path("/{codigoBarras}")
    @Operation(summary = "Consultar Productos por Id", description = "Esta capacidad permite consultar un producto por su Id")
    public Response buscarPorCodigoBarras(@PathParam("codigoBarras") Integer codigoBarras, @Context UriInfo uriInfo) {
        ProductoTo productoTo = ProductoMapper.toTo(this.productoService.buscarPorCodigoBarras(codigoBarras));
        productoTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(productoTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas los Productos", description = "Esta capacidad permite consultar todos las productos")
    public Response buscarTodos(@Context UriInfo uriInfo) {
        List<ProductoTo> prodToList = this.productoService.buscarTodos().stream().map(ProductoMapper::toTo)
                .collect(Collectors.toList());
        for (ProductoTo prodTo : prodToList) {
            prodTo.buildURI(uriInfo);
        }
        return Response.status(Response.Status.OK).entity(prodToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear Producto", description = "Esta capacidad permite crear un producto")
    public Response crear(@RequestBody ProductoTo productoTo) {
        this.productoService.guardar(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Completo por Id", description = "Esta capacidad permite actualizar completamente un producto por su Id")
    public Response actualizarPorCodigoBarras(@PathParam("codigoBarras") Integer codigoBarras,
            @RequestBody ProductoTo productoTo) {
        productoTo.setCodigoBarras(codigoBarras);
        this.productoService.actualizarParcialPorCodigoBarras(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Parcial por Código de Barras")
    public Response actualizarParcialPorCodigoBarras(
            @PathParam("codigoBarras") Integer codigoBarras,
            @RequestBody ProductoTo productoTo) {

        ProductoTo productoExistenteTo = ProductoMapper.toTo(
                this.productoService.buscarPorCodigoBarras(codigoBarras));

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

        this.productoService.actualizarParcialPorCodigoBarras(
                ProductoMapper.toEntity(productoExistenteTo));

        return Response.status(Response.Status.OK)
                .build();
    }

    @DELETE
    @Path("/{codigoBarras}")
    @Operation(summary = "Borrar Producto por Id", description = "Esta capacidad permite borrar un producto por su Id")
    public Response borrarPorCodigoBarras(@PathParam("codigoBarras") Integer codigoBarras) {
        this.productoService.borrarPorCodigoBarras(codigoBarras);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{codigoBarras}/impuestos")
    @Operation(summary = "Consultar Impuestos de un Producto", description = "Esta capacidad permite consultar los impuestos asociados a un producto por su Id")
    public Response buscarImpuestosPorProductoId(@PathParam("codigoBarras") Integer codigoBarras) {

        List<ImpuestoTo> impsToList = this.impuestoService.buscarTodos().stream().map(ImpuestoMapper::toTo)
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(impsToList).build();
    }

}
