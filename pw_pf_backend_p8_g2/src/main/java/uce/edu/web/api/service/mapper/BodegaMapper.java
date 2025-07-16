package uce.edu.web.api.service.mapper;

import uce.edu.web.api.service.to.BodegaTo;
import uce.edu.web.api.repository.model.Bodega;

public class BodegaMapper {

    public static BodegaTo toTo(Bodega bodega) {
        if (bodega == null) {
            return null;
        }
        BodegaTo bodegaTo = new BodegaTo();
        bodegaTo.setCodigo(bodega.getCodigo());
        bodegaTo.setNombre(bodega.getNombre());
        bodegaTo.setUbicacion(bodega.getUbicacion());
        return bodegaTo;
    }

    public static Bodega toBodega(BodegaTo bodegaTo) {
        if (bodegaTo == null) {
            return null;
        }
        Bodega bodega = new Bodega();
        bodega.setCodigo(bodegaTo.getCodigo());
        bodega.setNombre(bodegaTo.getNombre());
        bodega.setUbicacion(bodegaTo.getUbicacion());
        return bodega;
    }

}
