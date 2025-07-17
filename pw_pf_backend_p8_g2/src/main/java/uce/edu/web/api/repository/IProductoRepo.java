package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Producto;

public interface IProductoRepo {

    public Producto seleccionarPorId(Integer id);

    public Producto seleccionarPorCodigoBarras(String codigoBarras);

    public List<Producto> seleccionarTodos();

    public void actualizarPorId(Producto producto);

    public void actualizarParcialPorId(Producto producto);

    public void insertar(Producto producto);

    public void eliminarPorId(Integer id);
    
}
