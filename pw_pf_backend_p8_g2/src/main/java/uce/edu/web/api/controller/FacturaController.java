package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.service.IDetalleService;
import uce.edu.web.api.service.IFacturaService;
import uce.edu.web.api.service.mapper.DetalleMapper;
import uce.edu.web.api.service.mapper.FacturaMapper;
import uce.edu.web.api.service.mapper.ReporteDTOMapper;
import uce.edu.web.api.service.to.DetalleTo;
import uce.edu.web.api.service.to.FacturaTo;
import uce.edu.web.api.service.to.ReporteDTOTo;

@Path("/facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaController {

    @Inject
    private IFacturaService iFacturaService;

    @Inject 
    private IDetalleService iDetalleService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar Factura por ID", description = "Esta capacidad me permite buscar una factura por su ID.")
    public Response buscarPorId(@PathParam("id") Integer id) {
        FacturaTo fTo = FacturaMapper.toTo(this.iFacturaService.buscarPorId(id));
        return Response.status(Response.Status.OK).entity(fTo).build();
    }

    @POST
    @Path("")
    public Response guardar(FacturaTo facturaTo){
        this.iFacturaService.guardar(FacturaMapper.toEntity(facturaTo));
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/reportes")
    @Operation(summary = "Obtener Todos los Reportes", description = "Esta capacidad me permite obtener todos los reportes.")
    public Response obtenerReportes(@Context UriInfo uriInfo) {
        List<ReporteDTOTo> rDTOToList = this.iFacturaService.buscarReportes().stream().map(ReporteDTOMapper::toTo)
                .peek(rDTOTo -> rDTOTo.buildURI(uriInfo))
                .collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(rDTOToList).build();
    }

    @GET
    @Path("{id}/detalles")
    @Operation(summary = "Buscar Detalles por ID de Factura", description = "Esta capacidad me permite buscar los detalles de una factura por su ID.")
    public Response buscarDetallesPorIdFactura(@PathParam("id") Integer id) {
        List<DetalleTo> dToList = this.iDetalleService.buscarDetallesPorIdFactura(id).stream().map(DetalleMapper::toTo).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(dToList).build();
    }

}
