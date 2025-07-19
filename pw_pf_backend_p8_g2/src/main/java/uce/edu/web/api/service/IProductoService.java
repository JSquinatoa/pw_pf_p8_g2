package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

public interface IProductoService {

    // Producto

    public Producto buscarPorId(Integer codigoBarras);

    public List<Producto> buscarTodos();

    public void actualizarPorId(Producto producto);

    public void actualizarParcialPorId(Producto producto);

    public void borrarProductoConImpuestosPorId(Integer codigoBarras);

    public void guardar(Producto producto);

    // Prodcuto Impuesto

    public List<Impuesto> buscarImpuestosPorProducto(Integer codigoBarras);

    public void guardarImpuestoAProducto(Integer codigoBarras, Integer impuestoId);

    public void borrarImpuestoDeProducto(Integer codigoBarras, Integer impuestoId);

    public void actualizarImpuestoDeProducto(Integer codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo);

}
