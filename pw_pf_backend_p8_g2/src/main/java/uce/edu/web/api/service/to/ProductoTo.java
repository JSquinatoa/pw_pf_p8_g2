package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProductoController;

public class ProductoTo {

    private Integer codigoBarras;
    private String nombre;
    private String categoria;
    private Integer stock;
    private Double precio;
    private Map<String, String> impuestos = new HashMap<>();

    // GET y SET
    public Integer getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Integer codigoBarras) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Map<String, String> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Map<String, String> impuestos) {
        this.impuestos = impuestos;
    }

    public void buildURI(UriInfo uriInfo) {
        URI todosImpuestos = uriInfo.getBaseUriBuilder().path(ProductoController.class)
                .path(ProductoController.class, "obtenerImpuestosPorProducto").build(codigoBarras);
        impuestos.put("impuestos", todosImpuestos.toString());

    }

}
