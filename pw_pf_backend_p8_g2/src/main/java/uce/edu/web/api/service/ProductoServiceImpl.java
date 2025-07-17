package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProductoRepo;
import uce.edu.web.api.repository.model.Producto;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService {
    
    @Inject
    public IProductoRepo productoRepo;


    @Override
    public Producto buscarPorCodigoBarras(Integer codigoBarras) {
        return this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
    }

    @Override
    public List<Producto> buscarTodos() {
        return this.productoRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorCodigoBarras(Producto producto) {
        this.productoRepo.actualizarPorCodigoBarras(producto);
    }

    @Override
    public void actualizarParcialPorCodigoBarras(Producto producto) {
        this.productoRepo.actualizarParcialPorCodigoBarras(producto);
    }

    @Override
    public void borrarPorCodigoBarras(Integer codigoBarras) {
        this.productoRepo.eliminarPorCodigoBarras(codigoBarras);
    }

    @Override
    public void guardar(Producto producto) {
        this.productoRepo.insertar(producto);
    }

}
