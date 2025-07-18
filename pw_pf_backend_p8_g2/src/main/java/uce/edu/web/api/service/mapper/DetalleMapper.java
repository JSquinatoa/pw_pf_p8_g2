package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Detalle;
import uce.edu.web.api.repository.model.Reporte;
import uce.edu.web.api.service.to.DetalleTo;

public class DetalleMapper {

    public static DetalleTo toTo(Detalle detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleTo detalleTo = new DetalleTo();
        detalleTo.setCodigoBarras(detalle.getCodigoBarras());
        detalleTo.setNombre(detalle.getNombre());
        detalleTo.setCantidad(detalle.getCantidad());
        detalleTo.setPrecio(detalle.getPrecio());
        detalleTo.setSubTotal(detalle.getSubTotal());

        if (detalle.getReporte() != null) {
            detalleTo.setNumDocu(detalle.getReporte().getNumDocu());
        }

        return detalleTo;
    }

    public static Detalle toEntity(DetalleTo detalleTo) {
        Detalle detalle = new Detalle();
        detalle.setCodigoBarras(detalleTo.getCodigoBarras());
        detalle.setNombre(detalleTo.getNombre());
        detalle.setCantidad(detalleTo.getCantidad());
        detalle.setPrecio(detalleTo.getPrecio());
        detalle.setSubTotal(detalleTo.getSubTotal());

        if (detalleTo.getNumDocu() != null) {
            Reporte reporte = new Reporte();
            reporte.setNumDocu(detalleTo.getNumDocu());
            detalle.setReporte(reporte); 
        }

        return detalle;
    }
}
