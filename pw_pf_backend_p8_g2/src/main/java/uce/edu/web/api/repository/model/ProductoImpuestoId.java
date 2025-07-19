package uce.edu.web.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductoImpuestoId implements Serializable {

    @Column(name = "prod_codigoBarras")
    private Integer productoId;

    @Column(name = "imp_id")
    private Integer impuestoId;

    public ProductoImpuestoId() {
    }

    public ProductoImpuestoId(Integer productoId, Integer impuestoId) {
        this.productoId = productoId;
        this.impuestoId = impuestoId;
    }

    // Getters y Setters
    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(Integer impuestoId) {
        this.impuestoId = impuestoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductoImpuestoId that = (ProductoImpuestoId) o;

        if (!productoId.equals(that.productoId)) {
            return false;
        }
        return impuestoId.equals(that.impuestoId);
    }

    @Override
    public int hashCode() {
        int result = productoId.hashCode();
        result = 31 * result + impuestoId.hashCode();
        return result;
    }
}
