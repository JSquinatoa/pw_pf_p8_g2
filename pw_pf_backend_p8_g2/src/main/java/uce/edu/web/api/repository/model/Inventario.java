package uce.edu.web.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventario")
@IdClass(InventarioPK.class)
public class Inventario {

    @Column(name = "inve_stock")
    private Integer stock;

    // Relaciones
    @Id
    @ManyToOne
    @JoinColumn(name = "bode_id", referencedColumnName = "bode_id")
    private Bodega bodega;

    @Id
    @ManyToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    private Producto producto;

    // GET y SET
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
