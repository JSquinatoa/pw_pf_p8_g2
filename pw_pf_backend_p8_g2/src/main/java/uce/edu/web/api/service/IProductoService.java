package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Producto;

public interface IProductoService {

    public Producto buscarPorId(Integer id);

    public Producto buscarPorCodigoBarras(String codigoBarras);

    public List<Producto> buscarTodos();

    public void actualizarPorId(Producto producto);

    public void actualizarParcialPorId(Producto producto);

    public void borrarPorId(Integer id);

    public void guardar(Producto producto);

}
