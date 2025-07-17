package uce.edu.web.api.service;

import java.util.List;
import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaService {

    Bodega seleccionarPorCodigo(Integer codigo);

    List<Bodega> seleccionarTodos();

    void actualizarPorCodigo(Bodega bodega);

    void actualizarParcialPorCodigo(Integer codigo, Bodega bodega);

    void insertar(Bodega bodega);

    void eliminarPorCodigo(Integer codigo);
}
