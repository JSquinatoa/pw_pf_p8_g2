package uce.edu.web.api.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.service.IInventarioService;
import uce.edu.web.api.service.mapper.InventarioMapper;
import uce.edu.web.api.service.to.InventarioTo;

@Path("/inventarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvetarioController {

    @Inject
    private IInventarioService iInventarioService;

    @GET
    @Path("/{codigoBodega}")
    @Operation(summary = "Obtener el stock de un producto en la bodega", description = "Esta capacidad me permite obtener el stock de un producto registrada en una bodega")
    public Response consultarStockProductoEnBodega(@PathParam("codigoBodega") String codigoBodega,
            @QueryParam("codigoBarras") String codigoBarras) {
        Integer stockActual = this.iInventarioService.buscarStock(codigoBodega, codigoBarras);
        return Response.status(Response.Status.OK).entity(stockActual).build();
    }

    @GET
    @Path("/productos-por-bodega/{codigoBodega}")
    @Operation(summary = "Obtener productos y stock por bodega", description = "Obtiene todos los productos y su stock asociado a una bodega específica.")
    public Response consultarProductosPorBodega(@PathParam("codigoBodega") String codigoBodega) {
        List<InventarioTo> productosEnBodega = this.iInventarioService.buscarProductosInventarioBodega(codigoBodega);
        if (productosEnBodega == null || productosEnBodega.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron productos para la bodega " + codigoBodega).build();
        }
        return Response.status(Response.Status.OK).entity(productosEnBodega).build();
    }

    @POST
    @Path("/{codigoBodega}/{codigoBarras}")
    @Operation(summary = "Guardar un producto en Bodega", description = "Esta capacidad me permite guardar un producto en bodega con un stock")
    public Response insertarStockProductoEnBodega(@PathParam("codigoBodega") String codigoBodega,
            @PathParam("codigoBarras") String codigoBarras, @QueryParam("stock") Integer stock) {
        this.iInventarioService.guardar(codigoBodega, codigoBarras, stock);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigoBodega}/{codigoBarras}")
    @Operation(summary = "Actulizar el stock de un Producto en Bodega", description = "Esta capacidad me permite actulizar el sotck de un producto en una Bodega")
    public Response actualizarStockProductoEnBodega(@PathParam("codigoBodega") String codigoBodega,
            @PathParam("codigoBarras") String codigoBarras, @QueryParam("nuevoStock") Integer nuevoStock) {
        this.iInventarioService.actualizarStock(codigoBodega, codigoBarras, nuevoStock);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{codigoBodega}")
    @Operation(summary = "Eliminar un Producto de una Bodega", description = "Elimina la relación de un producto específico con una bodega específica")
    public Response eliminarProductoDeBodega(@PathParam("codigoBodega") String codigoBodega,
            @QueryParam("codigoBarras") String codigoBarras) {
        this.iInventarioService.borrar(codigoBodega, codigoBarras);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
