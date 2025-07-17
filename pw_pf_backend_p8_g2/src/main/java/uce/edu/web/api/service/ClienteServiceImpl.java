package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IClienteRepo;
import uce.edu.web.api.repository.model.Cliente;

@ApplicationScoped
public class ClienteServiceImpl implements IClienteService {

    @Inject
    private IClienteRepo iClienteRepo;

    @Override
    public Cliente buscarPorCedula(String cedula) {
        return this.iClienteRepo.seleccionarPorCedula(cedula);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return this.iClienteRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorCedula(Cliente cliente) {
        this.iClienteRepo.actualizarPorCedula(cliente);
    }

    @Override
    public void actualizarParcialPorCedula(Cliente cliente) {
        this.iClienteRepo.actualizarParcialPorCedula(cliente);
    }

    @Override
    public void borrarPorCedula(String cedula) {
        this.iClienteRepo.eliminarPorCedula(cedula);
    }

    @Override
    public void guardar(Cliente cliente) {
        this.iClienteRepo.insertar(cliente);
    }

}
