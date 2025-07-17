package uce.edu.web.api.service.to;

import java.util.Map;
import java.util.HashMap;

public class BodegaTo {

    private Integer codigo;
    private String nombre;
    private String ubicacion;

    private Map<String, String> _links = new HashMap<>();

    // GET y SET
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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

}
