package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProductoController;

public class ProductoTo {

    private Integer id;
    private String codigoBarras;
    private String nombre;
    private String categoria;
    private Double precio;
    private Map<String, String> _links = new HashMap<>();
    private List<Integer> impuestos;

    // GET y SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public List<Integer> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<Integer> impuestos) {
        this.impuestos = impuestos;
    }

    public void buildURI(UriInfo uriInfo) {
        URI todosImpuestos = uriInfo.getBaseUriBuilder().path(ProductoController.class)
                .path(ProductoController.class, "obtenerImpuestosPorProducto").build(codigoBarras);
        _links.put("impuestos", todosImpuestos.toString());

        URI todasBodegas = uriInfo.getBaseUriBuilder().path(ProductoController.class)
                .path(ProductoController.class, "obtenerBodegasPorProducto").build(codigoBarras);
        _links.put("bodegas", todasBodegas.toString());
    }
}
