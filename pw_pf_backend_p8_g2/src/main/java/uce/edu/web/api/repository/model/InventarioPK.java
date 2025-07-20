package uce.edu.web.api.repository.model;

import java.io.Serializable;
import java.util.Objects;

public class InventarioPK implements Serializable {
    private Integer bodega;
    private Integer producto;

    public Integer getBodega() {
        return bodega;
    }

    public void setBodega(Integer bodega) {
        this.bodega = bodega;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InventarioPK that = (InventarioPK) o;
        return bodega.equals(that.bodega) && producto.equals(that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodega, producto);
    }

}
