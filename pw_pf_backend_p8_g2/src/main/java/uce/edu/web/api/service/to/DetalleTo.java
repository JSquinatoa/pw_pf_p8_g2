package uce.edu.web.api.service.to;


public class DetalleTo {
    
    private Integer codigoBarras;
    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Double subTotal;

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
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    

}
