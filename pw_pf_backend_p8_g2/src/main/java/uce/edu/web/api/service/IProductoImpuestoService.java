package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.ProductoImpuesto;

public interface IProductoImpuestoService {
    List<ProductoImpuesto> buscarTodos();

    List<ProductoImpuesto> buscarPorProducto(Integer prod_codigoBarras);

    List<ProductoImpuesto> buscarPorImpuesto(Integer imp_id);

    void crear(ProductoImpuesto productoImpuesto);

    void actualizar(ProductoImpuesto productoImpuesto);

    void actualizarParcial(ProductoImpuesto productoImpuesto);

    void eliminar(Integer prod_codigoBarras, Integer imp_id);

    boolean existeRelacion(Integer prod_codigoBarras, Integer imp_id);
}