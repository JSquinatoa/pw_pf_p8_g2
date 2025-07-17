package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Reporte;

public interface IReporteService {

    public Reporte buscarReportePorId(Integer numDocu);
    public List<Reporte> buscarTodosReportes();
    public void guardarReporte(Reporte reporte);
    public void actualizarReportePorId(Reporte reporte);
    public void actualizarParcialReporte(Reporte reporte);
    public void borrarPorId(Integer numDocu);

}
