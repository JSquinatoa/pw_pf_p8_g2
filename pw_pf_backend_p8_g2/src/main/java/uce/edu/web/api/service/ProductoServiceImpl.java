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
    public Producto buscarPorId(Integer id) {
        return this.productoRepo.seleccionarPorId(id);
    }

    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return this.productoRepo.seleccionarPorCodigoBarras(codigoBarras);
    }

    @Override
    public List<Producto> buscarTodos() {
        return this.productoRepo.seleccionarTodos();
    }

    @Override
    public void actualizarPorId(Producto producto) {
        this.productoRepo.actualizarPorId(producto);
    }

    @Override
    public void actualizarParcialPorId(Producto producto) {
        this.productoRepo.actualizarParcialPorId(producto);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.productoRepo.eliminarPorId(id);
    }

    @Override
    public void guardar(Producto producto) {
        this.productoRepo.insertar(producto);
    }

}
