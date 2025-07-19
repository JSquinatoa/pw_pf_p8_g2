package uce.edu.web.api.service.to;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

public class ProductoImpuestoTo {
    private Producto producto;
    private Impuesto impuesto;

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Impuesto getImpuesto() {
        return impuesto;
    }
    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }
    
}
