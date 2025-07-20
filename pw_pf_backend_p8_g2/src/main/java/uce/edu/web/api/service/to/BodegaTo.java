package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.BodegaController;

public class BodegaTo {


    private Integer id;
    private String codigo;
    private String nombre;
    private String ubicacion;
    private Map<String, String> _links = new HashMap<>();

    // GET y SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(UriInfo uriInfo) {
        URI todosProductos = uriInfo.getBaseUriBuilder().path(BodegaController.class)
                .path(BodegaController.class, "obtenerProductosPorBodega").build(id);
        _links.put("productos", todosProductos.toString());
    }

}
