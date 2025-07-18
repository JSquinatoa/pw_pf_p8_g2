package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.service.ICabeceraService;
import uce.edu.web.api.service.IDetalleService;
import uce.edu.web.api.service.IReporteService;
import uce.edu.web.api.service.mapper.CabeceraMapper;
import uce.edu.web.api.service.mapper.DetalleMapper;
import uce.edu.web.api.service.mapper.ReporteMapper;
import uce.edu.web.api.service.to.CabeceraTo;
import uce.edu.web.api.service.to.DetalleTo;
import uce.edu.web.api.service.to.ReporteTo;

@Path("/reportes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteController {

    @Inject
    private IReporteService iReporteService;

    @Inject
    private ICabeceraService iCabeceraService;

    @Inject
    private IDetalleService iDetalleService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer numDocu, @Context UriInfo uriInfo) {
        ReporteTo reporteTo = ReporteMapper.toTo(this.iReporteService.buscarPorId(numDocu));
        reporteTo.buildURI(uriInfo);
        return Response.status(200).entity(reporteTo).build();
    }

    @GET
    public Response consultarTodos() {
        List<ReporteTo> reportes = this.iReporteService.buscarTodos().stream().map(ReporteMapper::toTo)
                .collect(Collectors.toList());
        return Response.status(200).entity(reportes).build();
    }

    @POST
    @Path("/{numDocu}")
    public Response modificarPorId(@RequestBody ReporteTo reporteTo, @PathParam("numDocu") Integer numDocu) {
        reporteTo.setNumDocu(numDocu);
        this.iReporteService.actualizarPorId(ReporteMapper.toEntity(reporteTo));
        return Response.status(200).build();
    }

    @PATCH
    @Path("/{numDocu}")
    public Response modificarParcialPorId(@RequestBody ReporteTo reporteTo, @PathParam("numDocu") Integer numDocu) {
        reporteTo.setNumDocu(numDocu);
        ReporteTo rTo = ReporteMapper.toTo(this.iReporteService.buscarPorId(numDocu));

        if (reporteTo.getIdCli() != null) {
            rTo.setIdCli(reporteTo.getIdCli());
        }
        if (reporteTo.getNombreCliente() != null) {
            rTo.setNombreCliente(reporteTo.getNombreCliente());
        }
        if (reporteTo.getCantidadItems() != null) {
            rTo.setCantidadItems(reporteTo.getCantidadItems());
        }
        if (reporteTo.getTotal() != null) {
            rTo.setTotal(reporteTo.getTotal());
        }
        if (reporteTo.getTotalImpuestos() != null) {
            rTo.setTotalImpuestos(reporteTo.getTotalImpuestos());
        }        
        this.iReporteService.actualizarParcialPorId(ReporteMapper.toEntity(rTo));
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{numDocu}")
    public void borrarPorId(@PathParam("numDocu") Integer numDocu){
        this.iReporteService.borrarPorId(numDocu);
    }

    @GET
    @Path("/{numDocu}/cabecera")
    public CabeceraTo obtenerCabeceraPorId(@PathParam("numDocu") Integer numDocu) {
        return CabeceraMapper.toTo(this.iCabeceraService.buscarCabeceraPorIdReporte(numDocu));

    }

    @GET
    @Path("/{numDocu}/detalle")
    public List<DetalleTo> obtenerDetallePorId(@PathParam("numDocu") Integer numDocu) {
        return this.iDetalleService.buscarDetallesPorIdReporte(numDocu).stream().map(DetalleMapper::toTo).collect(Collectors.toList());
    }


}



