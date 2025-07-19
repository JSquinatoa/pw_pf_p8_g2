package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProductoRepo;
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService {

    @Inject
    public IProductoRepo iProductoRepo;

    @Override
    public Producto buscarPorId(Integer codigoBarras) {
        return this.iProductoRepo.seleccionarPorId(codigoBarras);
    }

    @Override
    public List<Producto> buscarTodos() {
        return this.iProductoRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorId(Producto producto) {
        this.iProductoRepo.actualizarPorId(producto);
    }

    @Override
    public void actualizarParcialPorId(Producto producto) {
        this.iProductoRepo.actualizarParcialPorId(producto);
    }

    @Override
    public void borrarProductoConImpuestosPorId(Integer codigoBarras) {
        this.iProductoRepo.eliminarProductoConImpuestosPorId(codigoBarras);
    }

    @Override
    public void guardar(Producto producto) {
        this.iProductoRepo.insertar(producto);
    }

    @Override
    public List<Impuesto> buscarImpuestosPorProducto(Integer codigoBarras) {
        return this.iProductoRepo.seleccionarImpuestosPorProducto(codigoBarras);
    }

    @Override
    public void guardarImpuestoAProducto(Integer codigoBarras, Integer impuestoId) {
        this.iProductoRepo.insertarImpuestoAProducto(codigoBarras, impuestoId);
    }

    @Override
    public void borrarImpuestoDeProducto(Integer codigoBarras, Integer impuestoId) {
        this.iProductoRepo.eliminarImpuestoDeProducto(codigoBarras, impuestoId);
        
    }

    @Override
    public void actualizarImpuestoDeProducto(Integer codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo) {
        this.iProductoRepo.actualizarImpuestoDeProducto(codigoBarras, impuestoIdActual, impuestoIdNuevo);
    }
}
