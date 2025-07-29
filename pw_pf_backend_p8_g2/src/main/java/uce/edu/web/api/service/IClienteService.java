package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Cliente;

public interface IClienteService {

    public Cliente buscarPorCedula(String cedula);

    public List<Cliente> buscarTodos();

    public void actualizar(Cliente cliente);

    public void borrarPorCedula(String cedula);

    public void guardar(Cliente cliente);

}
