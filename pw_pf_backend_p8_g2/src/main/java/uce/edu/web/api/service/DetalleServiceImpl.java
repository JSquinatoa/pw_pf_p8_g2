package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IDetalleRepo;
import uce.edu.web.api.repository.model.Detalle;

@ApplicationScoped
public class DetalleServiceImpl implements IDetalleService {

    @Inject
    private IDetalleRepo iDetalleRepo;

    @Override
    public List<Detalle> buscarDetallesPorIdFactura(Integer fact_id) {
        return this.iDetalleRepo.seleccionarDetallesPorIdFactura(fact_id);
    }

    @Override
    public void guardar(Detalle detalle) {

        this.iDetalleRepo.insertar(detalle);
    }

}
