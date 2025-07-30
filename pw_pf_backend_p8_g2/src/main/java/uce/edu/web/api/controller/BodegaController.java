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
import uce.edu.web.api.service.IBodegaService;
import uce.edu.web.api.service.IInventarioService;
import uce.edu.web.api.service.mapper.BodegaMapper;
import uce.edu.web.api.service.mapper.ProductoMapper;
import uce.edu.web.api.service.to.BodegaTo;
import uce.edu.web.api.service.to.ProductoTo;

@Path("/bodegas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BodegaController {

    @Inject
    private IBodegaService iBodegaService;

    @Inject 
    private IInventarioService iInventarioService;

    @GET
    @Path("/{codigo}")
    @Operation(summary = "Consultar Bodega por Código", description = "Esta capacidad permite consultar una bodega por su código")
    public Response consularPorCodigo(@PathParam("codigo") String codigo, @Context UriInfo uriInfo) {
        BodegaTo bTo = BodegaMapper.toTo(this.iBodegaService.buscarPorCodigo(codigo));
        bTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(bTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todas las Bodegas", description = "Esta capacidad permite consultar todas las bodegas")
    public Response consultarTodos(@Context UriInfo uriInfo) {
        List<BodegaTo> bodegas = this.iBodegaService.buscarTodos().stream()
                .map(BodegaMapper::toTo)
                .peek(bodegaTo -> bodegaTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(bodegas).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Insertar nueva Bodega", description = "Esta capacidad permite insertar una nueva bodega")
    public Response guardar(@RequestBody BodegaTo bodegaTo) {
        this.iBodegaService.guardar(BodegaMapper.toBodega(bodegaTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigo}")
    @Operation(summary = "Actualizar Bodega por Código", description = "Esta capacidad permite actualizar una bodega por su código")
    public Response actualizarPorCodigo(@PathParam("codigo") String codigo, @RequestBody BodegaTo bodegaTo) {
        BodegaTo bTo = BodegaMapper.toTo(this.iBodegaService.buscarPorCodigo(codigo));
        bodegaTo.setId(bTo.getId());
        bodegaTo.setCodigo(bTo.getCodigo());
        this.iBodegaService.actualizar(BodegaMapper.toBodega(bodegaTo));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{codigo}")
    @Operation(summary = "Actualizar Bodega por Código", description = "Esta capacidad permite actualizar una bodega por su código")
    public Response actualizarParcialPorCodigo(@PathParam("codigo") String codigo, @RequestBody BodegaTo bodegaTo) {
        BodegaTo bTo = BodegaMapper.toTo(this.iBodegaService.buscarPorCodigo(codigo));
        bodegaTo.setId(bTo.getId());
        bodegaTo.setCodigo(bTo.getCodigo());
        if (bodegaTo.getNombre() != null) {
            bTo.setNombre(bodegaTo.getNombre());
        }
        if (bodegaTo.getUbicacion() != null) {
            bTo.setUbicacion(bodegaTo.getUbicacion());
        }
        this.iBodegaService.actualizar(BodegaMapper.toBodega(bTo));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{codigo}")
    @Operation(summary = "Eliminar Bodega por Código", description = "Esta capacidad permite eliminar una bodega por su código")
    public Response eliminarPorCodigo(@PathParam("codigo") String codigo) {
        this.iBodegaService.borrarPorCodigo(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{codigo}/productos")
    @Operation(summary = "Obtener productos por Bodega", description = "Esta capacidad permite consultar todos los productos de una bodega específica")
    public Response obtenerProductosPorBodega(@PathParam("codigo") String codigo, @Context UriInfo uriInfo) {
        List<ProductoTo> productos = this.iInventarioService.buscarProductosPorBodega(codigo).stream()
                .map(ProductoMapper::toTo)
                .peek(productoTo -> productoTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(productos).build();
    }

}
