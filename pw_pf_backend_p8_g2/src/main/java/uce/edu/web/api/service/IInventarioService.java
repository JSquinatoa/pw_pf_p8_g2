package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Producto;
import uce.edu.web.api.service.to.InventarioTo;

public interface IInventarioService {

    // Inventario
    void guardar(String codigoBodega, String codigoBarras, Integer stock);

    Integer buscarStock(String codigoBodega, String codigoBarras);

    void actualizarStock(String codigoBodega, String codigoBarras, Integer nuevoStock);

    void borrar(String codigoBodega, String codigoBarras);

    // Relaciones
    List<Producto> buscarProductosPorBodega(String codigoBodega);

    List<InventarioTo> buscarProductosInventarioBodega(String codigoBodega); 
    

    List<Bodega> buscarBodegasPorProducto(String codigoBarras);

}
