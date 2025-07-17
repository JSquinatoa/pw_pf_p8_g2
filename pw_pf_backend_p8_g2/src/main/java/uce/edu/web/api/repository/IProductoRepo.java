package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Producto;

public interface IProductoRepo {

    public Producto seleccionarPorCodigoBarras(Integer codigoBarras);

    public List<Producto> seleccionarTodos();

    public void actualizarPorCodigoBarras(Producto producto);

    public void actualizarParcialPorCodigoBarras(Producto producto);

    public void insertar(Producto producto);

    public void eliminarPorCodigoBarras(Integer codigoBarras);
    
}
