package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaService {

    Bodega seleccionarPorId(Integer id);

    Bodega seleccionarPorCodigo(Integer codigo);

    List<Bodega> seleccionarTodos();

    void actualizarPorId(Bodega bodega);

    void actualizarPorCodigo(Bodega bodega);

    void actualizarParcialPorId(Bodega bodega);

    void actualizarParcialPorCodigo(Bodega bodega);

    void insertar(Bodega bodega);

    void eliminarPorId(Integer id);

    void eliminarPorCodigo(Integer codigo);
}
