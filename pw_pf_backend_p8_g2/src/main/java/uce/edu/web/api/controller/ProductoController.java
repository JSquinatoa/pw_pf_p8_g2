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
import uce.edu.web.api.service.IInventarioService;
import uce.edu.web.api.service.IProductoService;
import uce.edu.web.api.service.mapper.BodegaMapper;
import uce.edu.web.api.service.mapper.ImpuestoMapper;
import uce.edu.web.api.service.mapper.ProductoMapper;
import uce.edu.web.api.service.to.BodegaTo;
import uce.edu.web.api.service.to.ImpuestoTo;
import uce.edu.web.api.service.to.ProductoTo;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoController {

    @Inject
    private IProductoService iProductoService;

    @Inject
    private IInventarioService iInventarioService;

    @GET
    @Path("/{codigoBarras}")
    @Operation(summary = "Consultar Productos por codigo de barras", description = "Esta capacidad permite consultar un producto por su código de barras")
    public Response buscarPorIdProducto(@PathParam("codigoBarras") String codigoBarras, @Context UriInfo uriInfo) {
        ProductoTo prodTo = ProductoMapper.toTo(this.iProductoService.buscarPorCodigoBarras(codigoBarras));
        prodTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(prodTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas los Productos", description = "Esta capacidad permite consultar todos las productos")
    public Response buscarTodosProductos(@Context UriInfo uriInfo) {
        List<ProductoTo> prodToList = this.iProductoService.buscarTodos().stream()
                .map(ProductoMapper::toTo)
                .peek(productoTo -> productoTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(prodToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear Producto", description = "Esta capacidad permite crear un producto")
    public Response guardarProducto(@RequestBody ProductoTo productoTo) {
        this.iProductoService.guardar(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Completo por Id", description = "Esta capacidad permite actualizar completamente un producto por su Id")
    public Response actualizarPorIdProducto(@RequestBody ProductoTo productoTo,
            @PathParam("codigoBarras") String codigoBarras) {
        ProductoTo pTo = ProductoMapper.toTo(this.iProductoService.buscarPorCodigoBarras(codigoBarras));
        productoTo.setId(pTo.getId());
        productoTo.setCodigoBarras(pTo.getCodigoBarras());
        this.iProductoService.actualizarPorCodigoBarras(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{codigoBarras}")
    @Operation(summary = "Actualizar Producto Parcial por Código de Barras")
    public Response actualizarParcialPorIdProducto(
            @PathParam("codigoBarras") String codigoBarras,
            @RequestBody ProductoTo productoTo) {

        ProductoTo pTo = ProductoMapper.toTo(this.iProductoService.buscarPorCodigoBarras(codigoBarras));

        if (pTo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (productoTo.getCodigoBarras() != null) {
            pTo.setCodigoBarras(productoTo.getCodigoBarras());
        }
        if (productoTo.getNombre() != null) {
            pTo.setNombre(productoTo.getNombre());
        }
        if (productoTo.getCategoria() != null) {
            pTo.setCategoria(productoTo.getCategoria());
        }
        if (productoTo.getPrecio() != null) {
            pTo.setPrecio(productoTo.getPrecio());
        }

        this.iProductoService.actualizarPorCodigoBarras(ProductoMapper.toEntity(pTo));

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{codigoBarras}")
    @Operation(summary = "Borrar Producto por Id", description = "Esta capacidad permite borrar un producto por su Id")
    public Response borrarProductoConImpuestosPorIdProducto(@PathParam("codigoBarras") String codigoBarras) {
        this.iProductoService.borrarPorCodigoBarras(codigoBarras);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{codigoBarras}/impuestos")
    @Operation(summary = "Consultar Impuestos de un Producto", description = "Esta capacidad permite consultar los impuestos asociados a un producto por su Id")
    public Response obtenerImpuestosPorProducto(@PathParam("codigoBarras") String codigoBarras) {
        List<ImpuestoTo> impuToList = this.iProductoService.buscarImpuestosPorProducto(codigoBarras).stream()
                .map(ImpuestoMapper::toTo).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(impuToList).build();
    }

    @POST
    @Path("/{codigoBarras}/impuestos/{impuestoId}")
    @Operation(summary = "Asociar un impuesto a un producto", description = "Esta capacidad permite asocia un impuesto existente a un producto existente")
    public Response asociarImpuestoAProducto(@PathParam("codigoBarras") String codigoBarras,
            @PathParam("impuestoId") Integer impuestoId) {
        try {
            iProductoService.guardarImpuestoAProducto(codigoBarras, impuestoId);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{codigoBarras}/impuestos/{impuestoId}")
    @Operation(summary = "Eliminar impuesto de un producto", description = "Esta capacidad elimina la asociación entre un producto y un impuesto específico")
    public Response eliminarImpuestoDeProducto(@PathParam("codigoBarras") String codigoBarras,
            @PathParam("impuestoId") Integer impuestoId) {
        iProductoService.borrarImpuestoDeProducto(codigoBarras, impuestoId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{codigoBarras}/impuestos/{impuestoIdActual}")
    @Operation(summary = "Actualizar impuesto de un producto", description = "Reemplaza un impuesto asociado a un producto por otro impuesto")
    public Response actualizarImpuestoDeProducto(
            @PathParam("codigoBarras") String codigoBarras,
            @PathParam("impuestoIdActual") Integer impuestoIdActual,
            @QueryParam("nuevoImpuestoId") Integer nuevoImpuestoId) {
        iProductoService.actualizarImpuestoDeProducto(codigoBarras, impuestoIdActual, nuevoImpuestoId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{codigoBarras}/bodegas")
    @Operation(summary = "Obtener Bodegas por Producto", description = "Esta capacidad permite consultar todas las bodegas de un Producto específica")
    public Response obtenerBodegasPorProducto(@PathParam("codigoBarras") String codigoBarras,
            @Context UriInfo uriInfo) {
        List<BodegaTo> bodegas = this.iInventarioService.buscarBodegasPorProducto(codigoBarras).stream()
                .map(BodegaMapper::toTo)
                .peek(productoTo -> productoTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(bodegas).build();
    }

}
