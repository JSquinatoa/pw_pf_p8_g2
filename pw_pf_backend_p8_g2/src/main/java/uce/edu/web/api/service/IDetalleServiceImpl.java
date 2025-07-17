package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IDetalleRepo;
import uce.edu.web.api.repository.model.Detalle;

@ApplicationScoped
public class IDetalleServiceImpl implements IDetalleService {

    @Inject
    private IDetalleRepo detalleRepo;

    @Override
    public List<Detalle> buscarPorCodigoBarras(Integer id) {
        return this.detalleRepo.buscarPorCodigoBarras(id);
    }

    @Override
    public void guardar(Detalle detalle) {
        this.detalleRepo.insertar(detalle);
    }

   
    
}
