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
    @Path("/{id}")
    @Operation(summary = "Consultar Productos por Id", description = "Esta capacidad permite consultar un producto por su Id")
    public Response buscarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        /*
         * Producto producto = this.productoService.buscarPorId(id);
         * if (producto == null) {
         * return Response.status(Response.Status.NOT_FOUND).build();
         * }
         */
        ProductoTo productoTo = ProductoMapper.toTo(this.productoService.buscarPorId(id));
        productoTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(productoTo).build();
    }

    @GET
    @Path("/{codigoBarras}")
    @Operation(summary = "Consultar Productos por Código", description = "Esta capacidad permite consultar un producto por su código")
    public Response buscarPorCodigo(@PathParam("codigoBarras") String codigoBarras, @Context UriInfo uriInfo) {
        /*
         * Producto producto = this.productoService.buscarPorCodigoBarras(codigoBarras);
         * if (producto == null) {
         * return Response.status(Response.Status.NOT_FOUND).build();
         * }
         */
        ProductoTo productoTo = ProductoMapper.toTo(this.productoService.buscarPorCodigoBarras(codigoBarras));
        productoTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(productoTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas los Productos", description = "Esta capacidad permite consultar todos las productos")
    public Response buscarTodos(@Context UriInfo uriInfo) {
        /*
         * List<Producto> productos = this.productoService.buscarTodos();
         * List<ProductoTo> productosTo = productos.stream()
         * .map(ProductoMapper::toTo)
         * .peek(pTo -> pTo.buildURI(uriInfo))
         * .collect(Collectors.toList());
         */
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
    public Response crear(ProductoTo productoTo) {
        /*
         * Producto producto = ProductoMapper.toProducto(productoTo);
         * this.productoService.guardar(producto);
         * ProductoTo createdProductoTo = ProductoMapper.toTo(producto);
         * createdProductoTo.buildURI(uriInfo);
         */
        this.productoService.guardar(ProductoMapper.toEntity(productoTo));

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar Producto Completo por Id", description = "Esta capacidad permite actualizar completamente un producto por su Id")
    public Response actualizarPorId(@PathParam("id") Integer id, ProductoTo productoTo) {
        productoTo.setId(id);
        this.productoService.actualizarPorId(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar Producto Parcial por Id", description = "Esta capacidad permite actualizar parcialmente un producto por su Id")
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody ProductoTo productoTo) {
        productoTo.setId(id);
        ProductoTo prodTo = ProductoMapper.toTo(this.productoService.buscarPorId(id));

/*         if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } */

        if (productoTo.getCodigoBarras() != null) {
            prodTo.setCodigoBarras(productoTo.getCodigoBarras());
        }
        if (productoTo.getNombre() != null) {
            prodTo.setNombre(productoTo.getNombre());
        }
        if (productoTo.getCategoria() != null) {
            prodTo.setCategoria(productoTo.getCategoria());
        }
        if (productoTo.getStock() != null) {
            prodTo.setStock(productoTo.getStock());
        }
        if (productoTo.getPrecio() != null) {
            prodTo.setPrecio(productoTo.getPrecio());
        }

/*         this.productoService.actualizarPorId(producto);
        ProductoTo updatedProductoTo = ProductoMapper.toTo(producto);
        updatedProductoTo.buildURI(uriInfo); */
        this.productoService.actualizarParcialPorId(ProductoMapper.toEntity(productoTo));
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar Producto por Id", description = "Esta capacidad permite borrar un producto por su Id")
    public Response borrarporId(@PathParam("id") Integer id) {
/*         Producto productoExistente = this.productoService.buscarPorId(id);
        if (productoExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } */
        this.productoService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}/impuestos")
    @Operation(summary = "Consultar Impuestos de un Producto", description = "Esta capacidad permite consultar los impuestos asociados a un producto por su Id")
    public Response buscarImpuestosPorProductoId(@PathParam("id") Integer id) {
/*         Producto producto = this.productoService.buscarPorId(id);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } */
        List<ImpuestoTo> impsToList = this.impuestoService.buscarTodos().stream().map(ImpuestoMapper::toTo).collect(Collectors.toList());
        /* producto.getImpuestos().stream()
                .map(ImpuestoMapper::toTo)
                .collect(Collectors.toList()); */

        return Response.status(Response.Status.OK).entity(impsToList).build();
    }

}
