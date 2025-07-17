package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Cabecera;
import uce.edu.web.api.service.to.CabeceraTo;

public class CabeceraMapper {

    public static CabeceraTo toTo(Cabecera cabecera) {
        if (cabecera == null) {
            return null;
        }
        CabeceraTo cTo = new CabeceraTo();
        cTo.setId(cabecera.getId());
        cTo.setRucEmpresa(cabecera.getRucEmpresa());
        cTo.setNumDocumeto(cabecera.getNumDocumeto());
        cTo.setEstablecimiento(cabecera.getEstablecimiento());
        cTo.setPuntoEmision(cabecera.getPuntoEmision());
        cTo.setFechaEmision(cabecera.getFechaEmision());
        cTo.setIdCliente(cabecera.getIdCliente());
        cTo.setNombreCliente(cabecera.getNombreCliente());
        cTo.setDireccion(cabecera.getDireccion());
        cTo.setCorreo(cabecera.getCorreo());
        return cTo;
    }

    public static Cabecera toEntity(CabeceraTo cabeceraTo) {
        Cabecera c = new Cabecera();
        c.setId(cabeceraTo.getId());
        c.setRucEmpresa(cabeceraTo.getRucEmpresa());
        c.setNumDocumeto(cabeceraTo.getNumDocumeto());
        c.setEstablecimiento(cabeceraTo.getEstablecimiento());
        c.setPuntoEmision(cabeceraTo.getPuntoEmision());
        c.setFechaEmision(cabeceraTo.getFechaEmision());
        c.setIdCliente(cabeceraTo.getIdCliente());
        c.setNombreCliente(cabeceraTo.getNombreCliente());
        c.setDireccion(cabeceraTo.getDireccion());
        c.setCorreo(cabeceraTo.getCorreo());
        return c;
    }

}
