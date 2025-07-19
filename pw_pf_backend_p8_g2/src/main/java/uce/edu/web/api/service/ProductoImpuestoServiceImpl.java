package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.IImpuestoRepo;
import uce.edu.web.api.repository.IProductoImpuestoRepo;
import uce.edu.web.api.repository.IProductoRepo;
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;
import uce.edu.web.api.repository.model.ProductoImpuesto;

@ApplicationScoped
public class ProductoImpuestoServiceImpl implements IProductoImpuestoService {
    
    @Inject
    private IProductoImpuestoRepo productoImpuestoRepo;

    @Inject
    private IProductoRepo productoRepo;
    
    @Inject
    private IImpuestoRepo impuestoRepo;
        @Override
    public List<ProductoImpuesto> buscarTodos() {
        return productoImpuestoRepo.buscarTodos();
    }

    @Override
    public List<ProductoImpuesto> buscarPorProducto(Integer prod_codigoBarras) {
        return productoImpuestoRepo.buscarPorProducto(prod_codigoBarras);
    }

    @Override
    public List<ProductoImpuesto> buscarPorImpuesto(Integer imp_id) {
        return productoImpuestoRepo.buscarPorImpuesto(imp_id);
    }

    @Override
    @Transactional
    public void crear(ProductoImpuesto productoImpuesto) {
        Integer codigoBarras = productoImpuesto.getProducto().getCodigoBarras();
        Integer impuestoId = productoImpuesto.getImpuesto().getId();
        Producto producto = productoRepo.seleccionarPorCodigoBarras(codigoBarras);
        Impuesto impuesto = impuestoRepo.seleccionarPorId(impuestoId);
        productoImpuesto.setProducto(producto);
        productoImpuesto.setImpuesto(impuesto);
        productoImpuestoRepo.insertar(productoImpuesto);
    }

    @Override
    @Transactional
    public void eliminar(Integer prod_codigoBarras, Integer imp_id) {
        productoImpuestoRepo.eliminar(prod_codigoBarras, imp_id);
    }

    @Override
    public boolean existeRelacion(Integer prod_codigoBarras, Integer imp_id) {
        return productoImpuestoRepo.existeRelacion(prod_codigoBarras, imp_id);
    }

    @Override
    public void actualizar(ProductoImpuesto productoImpuesto) {
        productoImpuestoRepo.actualizar(productoImpuesto);
    }

    @Override
    public void actualizarParcial(ProductoImpuesto productoImpuesto) {
        productoImpuestoRepo.actualizarParcial(productoImpuesto);
    }
    
}