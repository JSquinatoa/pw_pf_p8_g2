package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaService {

    Bodega buscarPorCodigo(String codigo);

    List<Bodega> buscarTodos();

    void guardar(Bodega bodega);

    void actualizar(Bodega bodega);

    void borrarPorCodigo(String codigo);

}
