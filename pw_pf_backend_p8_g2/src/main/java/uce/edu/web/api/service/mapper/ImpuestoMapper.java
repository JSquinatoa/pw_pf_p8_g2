package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.service.to.ImpuestoTo;

public class ImpuestoMapper {

    public static ImpuestoTo toTo(Impuesto impuesto) {
        if (impuesto == null) {
            return null;
        }
        ImpuestoTo to = new ImpuestoTo();
        to.setId(impuesto.getId());
        to.setNombre(impuesto.getNombre());
        to.setPorcentaje(impuesto.getPorcentaje());
        return to;
    }

    public static Impuesto toEntity(ImpuestoTo impuestoTo) {
        if (impuestoTo == null) {
            return null;
        }
        Impuesto impuesto = new Impuesto();
        impuesto.setId(impuestoTo.getId());
        impuesto.setNombre(impuestoTo.getNombre());
        impuesto.setPorcentaje(impuestoTo.getPorcentaje());
        return impuesto;
    }
}