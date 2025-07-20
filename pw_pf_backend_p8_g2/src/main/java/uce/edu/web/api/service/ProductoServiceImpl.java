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
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        Producto producto = this.iProductoRepo.seleccionarPorCodigoBarras(codigoBarras);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrada");
        }
        return producto;
    }

    @Override
    public List<Producto> buscarTodos() {
        return this.iProductoRepo.seleccionarTodos();
    }

    @Override
    public void guardar(Producto producto) {
        if (this.iProductoRepo.seleccionarPorCodigoBarras(producto.getCodigoBarras()) != null) {
            throw new RuntimeException("El código de barras del prroducto ya existe");
        }
        this.iProductoRepo.insertar(producto);
    }

    @Override
    public void actualizarPorCodigoBarras(Producto producto) {
        this.iProductoRepo.actualizarPorCodigoBarras(producto);
    }

    @Override
    public void borrarPorCodigoBarras(String codigoBarras) {
        this.iProductoRepo.eliminarPorCodigoBarras(codigoBarras);
    }

    // Relaciones ProductoFinal

    @Override
    public List<Impuesto> buscarImpuestosPorProducto(String codigoBarras) {
        return this.iProductoRepo.seleccionarImpuestosPorProducto(codigoBarras);
    }

    @Override
    public void guardarImpuestoAProducto(String codigoBarras, Integer impuestoId) {
        this.iProductoRepo.insertarImpuestoAProducto(codigoBarras, impuestoId);
    }

    @Override
    public void borrarImpuestoDeProducto(String codigoBarras, Integer impuestoId) {
        this.iProductoRepo.eliminarImpuestoDeProducto(codigoBarras, impuestoId);
    }

    @Override
    public void actualizarImpuestoDeProducto(String codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo) {
        this.iProductoRepo.actualizarImpuestoDeProducto(codigoBarras, impuestoIdActual, impuestoIdNuevo);
    }

}
