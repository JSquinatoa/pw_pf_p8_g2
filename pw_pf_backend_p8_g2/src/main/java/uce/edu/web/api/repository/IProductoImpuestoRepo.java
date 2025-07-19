package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.ProductoImpuesto;

public interface IProductoImpuestoRepo {

    public List<ProductoImpuesto> buscarTodos();

    public List<ProductoImpuesto> buscarPorProducto(Integer prod_codigoBarras);

    public List<ProductoImpuesto> buscarPorImpuesto(Integer imp_id);

    public boolean existeRelacion(Integer prod_codigoBarras, Integer imp_id);

    public void insertar(ProductoImpuesto productoImpuesto);

    public void actualizar(ProductoImpuesto productoImpuesto);

    public void actualizarParcial(ProductoImpuesto productoImpuesto);

    public void eliminar(Integer prod_codigoBarras, Integer imp_id);
}
