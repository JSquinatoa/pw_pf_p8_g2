package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IDetalleRepo;
import uce.edu.web.api.repository.model.Detalle;

@ApplicationScoped
public class DetalleServiceImpl implements IDetalleService {

    @Inject
    private IDetalleRepo detalleRepo;

    @Override
    public List<Detalle> buscarDetallesPorIdReporte(Integer numDocu) {
        return this.detalleRepo.seleccionarDetallesPorIdReporte(numDocu);
    }

    @Override
    public void guardar(Detalle detalle) {
        this.detalleRepo.insertar(detalle);
    }

}
