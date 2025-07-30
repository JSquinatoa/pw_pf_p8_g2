package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.service.IInventarioService;

@Path("/inventarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvetarioController {

    @Inject
    private IInventarioService iInventarioService;

    @POST
    @Path("/{codigoBodega}/{codigoBarras}")
    @Operation(summary = "Guardar un producto en Bodega", description = "Esta capacidad me permite guardar un producto en bodega con un stock")
    public Response insertarStockProductoEnBodega(@PathParam("codigoBodega") String codigoBodega,
            @PathParam("codigoBarras") String codigoBarras, @QueryParam("stock") Integer stock) {
        this.iInventarioService.guardar(codigoBodega, codigoBarras, stock);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{codigoBodega}")
    @Operation(summary = "Eliminar un Producto de una Bodega", description = "Elimina la relación de un producto específico con una bodega específica")
    public Response eliminarProductoDeBodega(@PathParam("codigoBodega") String codigoBodega,
            @QueryParam("codigoBarras") String codigoBarras) {
        this.iInventarioService.borrar(codigoBodega, codigoBarras);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/descontar-stock/{codigoBodega}/{codigoBarras}")
    @Operation(summary = "Descontar stock de un producto en una bodega", description = "Permite descontar una cantidad específica de stock de un producto en una bodega")
    public Response descontarStock(@PathParam("codigoBodega") String codigoBodega,
            @PathParam("codigoBarras") String codigoBarras, @QueryParam("cantidad") Integer cantidad) {
        boolean descontado = this.iInventarioService.descontarStock(codigoBodega, codigoBarras, cantidad);
        return Response.status(Response.Status.OK).entity(descontado).build();
    }

    @POST
    @Path("/restaurar-stock/{codigoBodega}/{codigoBarras}")
    @Operation(summary = "Restaurar stock de un producto en una bodega", description = "Permite restaurar una cantidad específica de stock de un producto en una bodega")
    public Response restaurarStock(@PathParam("codigoBodega") String codigoBodega,
            @PathParam("codigoBarras") String codigoBarras, @QueryParam("cantidad") Integer cantidad) {
        this.iInventarioService.restaurarStock(codigoBodega, codigoBarras, cantidad);
        return Response.status(Response.Status.CREATED).build();
    }

}
