package uce.edu.web.api.service.mapper;

import java.util.ArrayList;
import java.util.List;

import uce.edu.web.api.repository.model.Cliente;
import uce.edu.web.api.repository.model.Detalle;
import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.service.to.DetalleTo;
import uce.edu.web.api.service.to.FacturaTo;

public class FacturaMapper {

    public static FacturaTo toTo(Factura factura) {
        if (factura == null) {
            return null;
        }
        FacturaTo fTo = new FacturaTo();
        fTo.setId(factura.getId());
        fTo.setRucEmpresa(factura.getRucEmpresa());
        fTo.setNumeroDocumento(factura.getNumeroDocumento());
        fTo.setEstablecimiento(factura.getEstablecimiento());
        fTo.setPuntoEmision(factura.getPuntoEmision());
        fTo.setFechaEmision(factura.getFechaEmision());
        fTo.setCedulaCliente(factura.getCliente().getCedula());
        fTo.setCantidadItems(factura.getCantidadItems());
        fTo.setSubTotal(factura.getSubTotal());
        fTo.setTotalImpuestos(factura.getTotalImpuestos());
        fTo.setTotal(factura.getTotal());
        fTo.setEstado(factura.getEstado());

        return fTo;
    }

    public static Factura toEntity(FacturaTo facturaTo) {
        Factura factura = new Factura();
        factura.setId(facturaTo.getId());
        factura.setRucEmpresa(facturaTo.getRucEmpresa());
        factura.setNumeroDocumento(facturaTo.getNumeroDocumento());
        factura.setEstablecimiento(facturaTo.getEstablecimiento());
        factura.setPuntoEmision(facturaTo.getPuntoEmision());
        factura.setFechaEmision(facturaTo.getFechaEmision());
        factura.setCantidadItems(facturaTo.getCantidadItems());
        factura.setSubTotal(facturaTo.getSubTotal());
        factura.setTotalImpuestos(facturaTo.getTotalImpuestos());
        factura.setTotal(facturaTo.getTotal());
        factura.setEstado(facturaTo.getEstado());

        Cliente c = new Cliente();
        c.setCedula(facturaTo.getCedulaCliente());
        factura.setCliente(c);

        List<Detalle> detalles = new ArrayList<>();
        for (DetalleTo detalle : facturaTo.getDetalleTos()) {
            Detalle d = DetalleMapper.toEntity(detalle);
            d.setFactura(factura);
            detalles.add(d);
        }

        factura.setDetalles(detalles);

        return factura;
    }

}
