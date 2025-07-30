package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaRepo {

    public Bodega seleccionarPorCodigo(String codigo);

    public List<Bodega> seleccionarTodos();

    public void insertar(Bodega bodega);

    public void actualizar(Bodega bodega);

    public void eliminarPorCodigo(String codigo);

}
