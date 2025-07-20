package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaRepo {

    Bodega seleccionarPorCodigo(String codigo);

    List<Bodega> seleccionarTodos();

    void insertar(Bodega bodega);

    void actualizarPorCodigo(Bodega bodega);

    void eliminarPorCodigo(String codigo);

}
