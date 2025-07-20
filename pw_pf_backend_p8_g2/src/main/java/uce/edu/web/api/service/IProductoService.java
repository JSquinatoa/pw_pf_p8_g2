package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

public interface IProductoService {

    // Producto

    Producto buscarPorCodigoBarras(String codigoBarras);

    List<Producto> buscarTodos();

    void guardar(Producto producto);

    void actualizarPorCodigoBarras(Producto producto);

    void borrarPorCodigoBarras(String codigoBarras);

    // Producto impuesto

    public List<Impuesto> buscarImpuestosPorProducto(String codigoBarras);

    public void guardarImpuestoAProducto(String codigoBarras, Integer impuestoId);

    public void borrarImpuestoDeProducto(String codigoBarras, Integer impuestoId);

    public void actualizarImpuestoDeProducto(String codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo);

}
