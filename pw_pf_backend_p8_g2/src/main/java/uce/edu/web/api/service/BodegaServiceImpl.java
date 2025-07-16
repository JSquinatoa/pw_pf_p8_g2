package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IBodegaRepo;
import uce.edu.web.api.repository.model.Bodega;

@ApplicationScoped
public class BodegaServiceImpl implements IBodegaService {

    @Inject
    private IBodegaRepo bodegaRepo;

    @Override
    public Bodega seleccionarPorCodigo(Integer codigo) {
        return bodegaRepo.seleccionarPorCodigo(codigo);
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        return bodegaRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        bodegaRepo.actualizarPorCodigo(bodega);
    }

    @Override
    public void insertar(Bodega bodega) {
        bodegaRepo.insertar(bodega);
    }

    @Override
    public void eliminarPorCodigo(Integer codigo) {
        bodegaRepo.eliminarPorCodigo(codigo);
    }

    @Override
    public void actualizarParcialPorCodigo(Integer codigo, Bodega bodega) {
        Bodega existingBodega = bodegaRepo.seleccionarPorCodigo(codigo);
        if (existingBodega != null) {
            if (bodega.getNombre() != null) {
                existingBodega.setNombre(bodega.getNombre());
            }
            if (bodega.getUbicacion() != null) {
                existingBodega.setUbicacion(bodega.getUbicacion());
            }
            bodegaRepo.actualizarPorCodigo(existingBodega);
        }
    }

}
