package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IBodegaRepo;
import uce.edu.web.api.repository.model.Bodega;

@ApplicationScoped
public class BodegaServiceImpl implements IBodegaService {

    @Inject
    private IBodegaRepo iBodegaRepo;

    @Override
    public Bodega buscarPorCodigo(String codigo) {
        Bodega bodega = this.iBodegaRepo.seleccionarPorCodigo(codigo);
        if (bodega == null) {
            throw new RuntimeException("Bodega no encontrada");
        }
        return bodega;
    }

    @Override
    public List<Bodega> buscarTodos() {
        return this.iBodegaRepo.seleccionarTodos();
    }

    @Override
    public void guardar(Bodega bodega) {
        if (this.iBodegaRepo.seleccionarPorCodigo(bodega.getCodigo()) != null) {
            throw new RuntimeException("El código de bodega ya existe");
        }
        this.iBodegaRepo.insertar(bodega);
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        this.iBodegaRepo.actualizarPorCodigo(bodega);
    }

    @Override
    public void borrarPorCodigo(String codigo) {
        this.iBodegaRepo.eliminarPorCodigo(codigo);
    }

}
