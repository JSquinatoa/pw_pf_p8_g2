package uce.edu.web.api.service.to;

import java.util.HashMap;
import java.util.Map;

public class ClienteTo {

    private String cedula;
    private String nombre;
    private String apellido;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String correo;
    public Map<String, String> facturas = new HashMap<>();

    // GET y SET
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Map<String, String> getFacturas() {
        return facturas;
    }

    public void setFacturas(Map<String, String> facturas) {
        this.facturas = facturas;
    }

}
