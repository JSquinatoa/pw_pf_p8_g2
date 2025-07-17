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
    public Bodega seleccionarPorId(Integer id) {
        return this.bodegaRepo.seleccionarPorId(id);
    }

    @Override
    public Bodega seleccionarPorCodigo(Integer codigo) {
        return this.bodegaRepo.seleccionarPorCodigo(codigo);
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        return this.bodegaRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorId(Bodega bodega) {
        this.bodegaRepo.actualizarPorId(bodega);
    }
    

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        this.bodegaRepo.actualizarPorCodigo(bodega);
    }

    @Override
    public void actualizarParcialPorId(Bodega bodega) {
        this.bodegaRepo.actualizarParcialPorId(bodega);
    }

    @Override
    public void actualizarParcialPorCodigo(Bodega bodega) {
        this.bodegaRepo.actualizarParcialPorCodigo(bodega);
    }

    @Override
    public void insertar(Bodega bodega) {
        this.bodegaRepo.insertar(bodega);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.bodegaRepo.eliminarPorId(id);
    }

    @Override
    public void eliminarPorCodigo(Integer codigo) {
        this.bodegaRepo.eliminarPorCodigo(codigo);
    }


}
