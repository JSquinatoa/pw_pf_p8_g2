package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.Reporte;
import uce.edu.web.api.service.to.ReporteTo;

public class ReporteMapper {

    public static ReporteTo toTo(Reporte reporte){
        if (reporte == null) {
            return null;            
        }
        ReporteTo rTo = new ReporteTo();
        rTo.setNumDocu(reporte.getNumDocu());
        rTo.setIdCli(reporte.getIdCli());
        rTo.setNombreCliente(reporte.getNombreCliente());
        rTo.setCantidadItems(reporte.getCantidadItems());
        rTo.setTotal(reporte.getTotal());
        rTo.setTotalImpuestos(reporte.getTotalImpuestos());
        return rTo ;
    }

    public static Reporte toEntity(ReporteTo reporteTo){
        Reporte r = new Reporte();
        r.setNumDocu(reporteTo.getNumDocu());
        r.setIdCli(reporteTo.getIdCli());
        r.setNombreCliente(reporteTo.getNombreCliente());
        r.setCantidadItems(reporteTo.getCantidadItems());
        r.setTotal(reporteTo.getTotal());
        r.setTotalImpuestos(reporteTo.getTotalImpuestos());
        return r;
    }

}
