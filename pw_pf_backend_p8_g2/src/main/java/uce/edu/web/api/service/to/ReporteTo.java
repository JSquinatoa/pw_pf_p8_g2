package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ReporteController;

public class ReporteTo {

    private Integer numDocu;
    private Integer idCli;
    private String nombreCliente;
    private Integer cantidadItems;
    private Double totalImpuestos;
    private Double total;
    private Map<String, String> _linksDetalle = new HashMap<>();
    private Map<String, String> _linksCabecera = new HashMap<>();

    // GET y SET
    public Integer getNumDocu() {
        return numDocu;
    }

    public void setNumDocu(Integer id) {
        this.numDocu = id;
    }

    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer id_cliente) {
        this.idCli = id_cliente;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public Double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(Double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Map<String, String> get_linksDetalle() {
        return _linksDetalle;
    }

    public void set_linksDetalle(Map<String, String> _links) {
        this._linksDetalle = _links;
    }

    public Map<String, String> get_linksCabecera() {
        return _linksCabecera;
    }

    public void set_linksCabecera(Map<String, String> _linksCabecera) {
        this._linksCabecera = _linksCabecera;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void buildURI(UriInfo uriInfo) {
        URI todasCabeceras = uriInfo.getBaseUriBuilder()
                .path(ReporteController.class).path(ReporteController.class, "obtenerCabeceraPorId").build(numDocu);
        _linksDetalle.put("cabecera", todasCabeceras.toString());

        URI todasDetalle = uriInfo.getBaseUriBuilder()
                .path(ReporteController.class).path(ReporteController.class, "obtenerDetallePorId").build(numDocu);
        _linksCabecera.put("detalle", todasDetalle.toString());
    }

}
