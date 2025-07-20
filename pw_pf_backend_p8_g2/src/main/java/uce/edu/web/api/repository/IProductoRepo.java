package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

public interface IProductoRepo {

    // Producto

    Producto seleccionarPorCodigoBarras(String codigoBarras);

    List<Producto> seleccionarTodos();

    void insertar(Producto producto);

    void actualizarPorCodigoBarras(Producto producto); 

    void eliminarPorCodigoBarras(String codigoBarras); 

    // Producto impuesto

    public List<Impuesto> seleccionarImpuestosPorProducto(String codigoBarras);

    public void insertarImpuestoAProducto(String codigoBarras, Integer impuestoId);

    public void eliminarImpuestoDeProducto(String codigoBarras, Integer impuestoId);

    public void actualizarImpuestoDeProducto(String codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo);

}
