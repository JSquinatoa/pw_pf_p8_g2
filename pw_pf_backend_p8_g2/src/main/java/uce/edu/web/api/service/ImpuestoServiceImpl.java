package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IImpuestoRepo;
import uce.edu.web.api.repository.model.Impuesto;

@ApplicationScoped
public class ImpuestoServiceImpl implements IImpuestoService {
    @Inject
    private IImpuestoRepo impuestoRepo;

    @Override
    public Impuesto buscarPorId(Integer id) {
        return this.impuestoRepo.seleccionarPorId(id);
    }

    @Override
    public List<Impuesto> buscarTodos() {
        return this.impuestoRepo.seleccionarTodos();
    }

    @Override
    public void guardar(Impuesto impuesto) {
        this.impuestoRepo.insertar(impuesto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.impuestoRepo.eliminarPorId(id);
    }

    @Override
    public void actualizarPorId(Impuesto impuesto) {
        this.impuestoRepo.actualizarPorId(impuesto);
    }

    @Override
    public void actualizarParcialPorId(Impuesto impuesto) {
        this.impuestoRepo.actualizarParcialPorId(impuesto);
    }

}
