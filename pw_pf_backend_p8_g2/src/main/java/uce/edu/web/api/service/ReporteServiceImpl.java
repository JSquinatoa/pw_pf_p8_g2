package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IReporteRepo;
import uce.edu.web.api.repository.model.Reporte;

@ApplicationScoped
public class ReporteServiceImpl implements IReporteService{

    @Inject
    private IReporteRepo reporteRepo;

    @Override
    public Reporte buscarReportePorId(Integer numDocu) {
        return reporteRepo.seleccionarReportePorId(numDocu);
    }

    @Override
    public List<Reporte> buscarTodosReportes() {
        return reporteRepo.seleccionarTodosReportes();
    }

    @Override
    public void guardarReporte(Reporte reporte) {
         reporteRepo.insertarReporte(reporte);
    }

    @Override
    public void actualizarReportePorId(Reporte reporte) {
        reporteRepo.actualizarReportePorId(reporte);
    }

    @Override
    public void actualizarParcialReporte(Reporte reporte) {
        reporteRepo.actualizarParcialReporte(reporte);
    }

    @Override
    public void borrarPorId(Integer numDocu) {
        reporteRepo.borrarPorId(numDocu);
    }

}
