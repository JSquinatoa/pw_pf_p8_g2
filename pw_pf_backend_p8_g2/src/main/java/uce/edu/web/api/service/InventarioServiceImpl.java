package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IInventarioRepo;
import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Producto;

@ApplicationScoped
public class InventarioServiceImpl implements IInventarioService {

    @Inject
    private IInventarioRepo iInventarioRepo;

    @Override
    public void guardar(String codigoBodega, String codigoBarras, Integer stock) {
        Integer stockActual = this.iInventarioRepo.seleccionarStock(codigoBodega, codigoBarras);
        if (stockActual != null) {
            this.iInventarioRepo.actualizarStock(codigoBodega, codigoBarras, stockActual + stock);
            return;
        }
        this.iInventarioRepo.insertar(codigoBodega, codigoBarras, stock);
    }

    @Override
    public Integer buscarStock(String codigoBodega, String codigoBarras) {
        return this.iInventarioRepo.seleccionarStock(codigoBodega, codigoBarras);
    }

    @Override
    public void actualizarStock(String codigoBodega, String codigoBarras, Integer nuevoStock) {
        this.iInventarioRepo.actualizarStock(codigoBodega, codigoBarras, nuevoStock);
    }

    @Override
    public void borrar(String codigoBodega, String codigoBarras) {
        this.iInventarioRepo.eliminar(codigoBodega, codigoBarras);
    }

    @Override
    public boolean descontarStock(String codigoBodega, String codigoBarras, Integer cantidad) {
        Integer stockActual = this.iInventarioRepo.seleccionarStock(codigoBodega, codigoBarras);
        if (stockActual != null && stockActual >= cantidad) {
            this.iInventarioRepo.actualizarStock(codigoBodega, codigoBarras, stockActual - cantidad);
            return true;
        }
        return false;
    }

    @Override
    public void restaurarStock(String codigoBodega, String codigoBarras, Integer cantidad) {
        Integer stockActual = this.iInventarioRepo.seleccionarStock(codigoBodega, codigoBarras);
        if (stockActual != null) {
            this.iInventarioRepo.actualizarStock(codigoBodega, codigoBarras, stockActual + cantidad);
        }
    }

    @Override
    public List<Producto> buscarProductosPorBodega(String codigoBodega) {
        return this.iInventarioRepo.seleccionarProductosPorBodega(codigoBodega);
    }

    @Override
    public List<Bodega> buscarBodegasPorProducto(String codigoBarras) {
        return this.iInventarioRepo.seleccionarBodegasPorProducto(codigoBarras);
    }

}
