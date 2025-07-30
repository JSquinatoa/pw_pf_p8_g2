package uce.edu.web.api.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IInventarioRepo;
import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Inventario;
import uce.edu.web.api.repository.model.Producto;
import uce.edu.web.api.service.mapper.InventarioMapper;
import uce.edu.web.api.service.to.InventarioTo;

@ApplicationScoped
public class InventarioServiceImpl implements IInventarioService {

    @Inject
    private IInventarioRepo iInventarioRepo;

    @Override
    public void guardar(String codigoBodega, String codigoBarras, Integer stock) {
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
    public List<Producto> buscarProductosPorBodega(String codigoBodega) {
        return this.iInventarioRepo.seleccionarProductosPorBodega(codigoBodega);
    }

    @Override
    public List<InventarioTo> buscarProductosInventarioBodega(String codigoBodega) {
        List<Inventario> inventarios = this.iInventarioRepo.seleccionarInventariosPorBodega(codigoBodega);
        if (inventarios == null || inventarios.isEmpty()) {
            return Collections.emptyList();
        }
        return inventarios.stream()
                          .map(InventarioMapper::toTo)
                          .collect(Collectors.toList());
    }

    @Override
    public List<Bodega> buscarBodegasPorProducto(String codigoBarras) {
        return this.iInventarioRepo.seleccionarBodegasPorProducto(codigoBarras);
    }

}
