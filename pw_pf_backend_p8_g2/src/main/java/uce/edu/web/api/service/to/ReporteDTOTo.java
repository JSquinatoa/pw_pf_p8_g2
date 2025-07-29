package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.FacturaController;

public class ReporteDTOTo {

    private Integer id;
    private Integer numeroDocumento;
    private String cedulaCliente;
    private String nombreCliente;
    private Integer cantidadItems;
    private Double totalImpuestos;
    private Double total;
    private Map<String, String> _links = new HashMap<>();

    // GET y SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(UriInfo uriInfo) {
        URI factura = uriInfo.getBaseUriBuilder().path(FacturaController.class)
                .path(FacturaController.class, "buscarPorId").build(id);
        this._links.put("facturaCompleta", factura.toString());

        // Link para trear todos los productos de la factura

        URI detalles = uriInfo.getBaseUriBuilder().path(FacturaController.class)
                .path(FacturaController.class, "buscarDetallesPorIdFactura").build(id);
        this._links.put("detallesCompra", detalles.toString());
    }
}
