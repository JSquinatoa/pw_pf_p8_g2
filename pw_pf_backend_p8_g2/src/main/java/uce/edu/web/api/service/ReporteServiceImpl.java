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
    public Reporte buscarPorId(Integer numDocu) {
        return reporteRepo.seleccionarPorId(numDocu);
    }

    @Override
    public List<Reporte> buscarTodos() {
        return reporteRepo.seleccionarTodos();
    }

    @Override
    public void guardar(Reporte reporte) {
         reporteRepo.insertar(reporte);
    }

    @Override
    public void actualizarPorId(Reporte reporte) {
        reporteRepo.actualizarPorId(reporte);
    }

    @Override
    public void actualizarParcialPorId(Reporte reporte) {
        reporteRepo.actualizarParcialPorId(reporte);
    }

    @Override
    public void borrarPorId(Integer numDocu) {
        reporteRepo.eliminarPorId(numDocu);
    }

}
