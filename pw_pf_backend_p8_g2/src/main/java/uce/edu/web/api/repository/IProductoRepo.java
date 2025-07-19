package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

public interface IProductoRepo {

    // Prodcuto

    public Producto seleccionarPorId(Integer codigoBarras);

    public List<Producto> seleccionarTodos();

    public void actualizarPorId(Producto producto);

    public void actualizarParcialPorId(Producto producto);

    public void eliminarProductoConImpuestosPorId(Integer codigoBarras);

    public void insertar(Producto producto);

    // Producto impuesto

    public List<Impuesto> seleccionarImpuestosPorProducto(Integer codigoBarras);

    public void insertarImpuestoAProducto(Integer codigoBarras, Integer impuestoId);

    public void eliminarImpuestoDeProducto(Integer codigoBarras, Integer impuestoId);

    public void actualizarImpuestoDeProducto(Integer codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo);

}
