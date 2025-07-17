package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaRepo {

    public Bodega seleccionarPorId(Integer id);

    public Bodega seleccionarPorCodigo(Integer codigo);

    public List<Bodega> seleccionarTodos();

    public void actualizarPorId(Bodega bodega);

    public void actualizarPorCodigo(Bodega bodega);

    public void actualizarParcialPorId( Bodega bodega);

    public void actualizarParcialPorCodigo(Bodega bodega);

    public void insertar(Bodega bodega);

    public void eliminarPorId(Integer id);

    public void eliminarPorCodigo(Integer codigo);

}
