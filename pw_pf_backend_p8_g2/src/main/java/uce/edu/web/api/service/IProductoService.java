package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Producto;

public interface IProductoService {

    public Producto buscarPorCodigoBarras(Integer codigoBarras);

    public List<Producto> buscarTodos();

    public void actualizarPorCodigoBarras(Producto producto);

    public void actualizarParcialPorCodigoBarras(Producto producto);

    public void borrarPorCodigoBarras(Integer codigoBarras);

    public void guardar(Producto producto); 

}
