package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.ProductoImpuesto;
import uce.edu.web.api.service.to.ProductoImpuestoTo;

public class ProductoImpuestoMapper {

    public static ProductoImpuestoTo toTo(ProductoImpuesto productoImpuesto) {
        if (productoImpuesto == null) {
            return null;
        }
        ProductoImpuestoTo ProImpto = new ProductoImpuestoTo();
        ProImpto.setProducto(productoImpuesto.getProducto());
        ProImpto.setImpuesto(productoImpuesto.getImpuesto());
        return ProImpto;
    }

    public static ProductoImpuesto toEntity(ProductoImpuestoTo productoImpuestoTo) {
        if (productoImpuestoTo == null) {
            return null;
        }
        ProductoImpuesto ProImp = new ProductoImpuesto();
        ProImp.setProducto(productoImpuestoTo.getProducto());
        ProImp.setImpuesto(productoImpuestoTo.getImpuesto());
       return ProImp;
    }

}
