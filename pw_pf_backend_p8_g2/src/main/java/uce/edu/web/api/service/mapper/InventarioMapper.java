package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Inventario;
import uce.edu.web.api.repository.model.Producto;
import uce.edu.web.api.service.to.InventarioTo;

public class InventarioMapper {

    public static InventarioTo toTo(Inventario inventario) {
        if (inventario == null) {
            return null;
        }

        InventarioTo iTo = new InventarioTo();
        iTo.setStock(inventario.getStock());

        // Mapear códigos (no IDs) para que sean útiles en la API
        if (inventario.getBodega() != null) {
            iTo.setCodigoBodega(inventario.getBodega().getCodigo());
        }
        if (inventario.getProducto() != null) {
            iTo.setCodigoBarras(inventario.getProducto().getCodigoBarras());
            iTo.setNombreBodega(inventario.getBodega().getNombre());
            iTo.setNombreProducto(inventario.getProducto().getNombre());
        }

        return iTo;
    }

    public static Inventario toEntity(InventarioTo inventarioTo) {
        if (inventarioTo == null) {
            return null;
        }

        Inventario i = new Inventario();
        i.setStock(inventarioTo.getStock());
        if (inventarioTo.getCodigoBarras() != null && inventarioTo.getCodigoBodega() != null) {
            Bodega bodega = new Bodega();
            bodega.setCodigo(inventarioTo.getCodigoBodega());
            i.setBodega(bodega);

            Producto producto = new Producto();
            producto.setCodigoBarras(inventarioTo.getCodigoBarras());
            i.setProducto(producto);
        }
        return i;
    }
}