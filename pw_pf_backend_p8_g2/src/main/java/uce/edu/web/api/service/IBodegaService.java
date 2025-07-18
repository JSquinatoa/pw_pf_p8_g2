package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaService {

    Bodega buscarPorId(Integer id);

    Bodega buscarPorCodigo(Integer codigo);

    List<Bodega> buscarTodos();

    void actualizarPorId(Bodega bodega);

    void actualizarPorCodigo(Bodega bodega);

    void actualizarParcialPorId(Bodega bodega);

    void actualizarParcialPorCodigo(Bodega bodega);

    void guardar(Bodega bodega);

    void borrarPorId(Integer id);

    void borrarPorCodigo(Integer codigo);
}
