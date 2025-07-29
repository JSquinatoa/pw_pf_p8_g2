package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Detalle;
import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.service.to.DetalleTo;

public class DetalleMapper {

    public static DetalleTo toTo(Detalle detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleTo dTo = new DetalleTo();
        dTo.setId(detalle.getId());
        dTo.setFact_id(detalle.getFactura().getId());
        dTo.setCodigoBarras(detalle.getCodigoBarras());
        dTo.setNombre(detalle.getNombre());
        dTo.setCantidad(detalle.getCantidad());
        dTo.setPrecio(detalle.getPrecio());
        dTo.setSubTotal(detalle.getSubTotal());

        return dTo;
    }

    public static Detalle toEntity(DetalleTo detalleTo) {
        Detalle detalle = new Detalle();
        detalle.setId(detalleTo.getId());
        detalle.setCodigoBarras(detalleTo.getCodigoBarras());
        detalle.setNombre(detalleTo.getNombre());
        detalle.setCantidad(detalleTo.getCantidad());
        detalle.setPrecio(detalleTo.getPrecio());
        detalle.setSubTotal(detalleTo.getSubTotal());

        Factura factura = new Factura();
        factura.setId(detalleTo.getFact_id());
        detalle.setFactura(factura);

        return detalle;
    }
}
