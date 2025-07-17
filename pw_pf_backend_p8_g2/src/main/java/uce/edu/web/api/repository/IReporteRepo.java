package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Reporte;

public interface IReporteRepo {

    public Reporte seleccionarReportePorId(Integer numDocu);
    public List<Reporte> seleccionarTodosReportes();
    public void insertarReporte(Reporte reporte);
    public void actualizarReportePorId(Reporte reporte);
    public void actualizarParcialReporte(Reporte reporte);
    public void borrarPorId(Integer numDocu);
}
 