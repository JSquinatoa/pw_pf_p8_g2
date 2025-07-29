package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Cliente;

public interface IClienteRepo {

    public Cliente seleccionarPorCedula(String cedula);

    public List<Cliente> seleccionarTodos();

    public void actualizar(Cliente cliente);

    public void eliminarPorCedula(String cedula);

    public void insertar(Cliente cliente);

}
