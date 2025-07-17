package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Detalle;
import uce.edu.web.api.service.to.DetalleTo;

public class DetalleMapper {
    
    public static DetalleTo toTo(Detalle detalle){
        DetalleTo detalleTo = new DetalleTo();
            detalleTo.setCodigoBarras(detalle.getCodigoBarras());
            detalleTo.setNombre(detalle.getNombre());
            detalleTo.setPrecio(detalle.getPrecio());
            detalleTo.setSubTotal(detalle.getSubTotal());

        return detalleTo;

    }

    public static Detalle toEntity(DetalleTo detalleTo){
        Detalle detalle = new Detalle();
            detalle.setCodigoBarras(detalleTo.getCodigoBarras());
            detalle.setNombre(detalleTo.getNombre());
            detalle.setPrecio(detalleTo.getPrecio());
            detalle.setSubTotal(detalleTo.getSubTotal());

        return detalle;
    }
}
