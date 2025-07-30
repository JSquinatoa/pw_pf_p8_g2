package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;

public interface IBodegaService {

    public Bodega buscarPorCodigo(String codigo);

    public List<Bodega> buscarTodos();

    public void guardar(Bodega bodega);

    public void actualizar(Bodega bodega);

    public void borrarPorCodigo(String codigo);

}
