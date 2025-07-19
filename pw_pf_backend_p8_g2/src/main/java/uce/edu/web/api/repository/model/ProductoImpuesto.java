package uce.edu.web.api.repository.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_impuesto")
public class ProductoImpuesto {

    @EmbeddedId
    private ProductoImpuestoId id;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "prod_codigoBarras", insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne
    @MapsId("impuestoId")
    @JoinColumn(name = "imp_id", insertable = false, updatable = false)
    private Impuesto impuesto;

    public ProductoImpuesto() {
        this.id = new ProductoImpuestoId();
    }

    public ProductoImpuesto(Producto producto, Impuesto impuesto) {
        this.id = new ProductoImpuestoId(producto.getCodigoBarras(), impuesto.getId());
        this.producto = producto;
        this.impuesto = impuesto;
    }

    // Getters y Setters
    public ProductoImpuestoId getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        if (producto != null) {
            this.id.setProductoId(producto.getCodigoBarras());
        }
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
        if (impuesto != null) {
            this.id.setImpuestoId(impuesto.getId());
        }
    }
}
