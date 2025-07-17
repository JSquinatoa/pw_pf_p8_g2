package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaRepo {

    public Bodega seleccionarPorCodigo(Integer codigo);

    public List<Bodega> seleccionarTodos();

    public void actualizarPorCodigo(Bodega bodega);

    public void actualizarParcialPorCodigo(Integer codigo, Bodega bodega);

    public void insertar(Bodega bodega);

    public void eliminarPorCodigo(Integer codigo);

}
