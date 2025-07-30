package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Inventario;
import uce.edu.web.api.repository.model.Producto;

public interface IInventarioRepo {

    // Inventario
    void insertar(String codigoBodega, String codigoBarras, Integer stock);

    Integer seleccionarStock(String codigoBodega, String codigoBarras);

    void actualizarStock(String codigoBodega, String codigoBarras, Integer nuevoStock);

    void eliminar(String codigoBodega, String codigoBarras);

    // Relaciones
    List<Producto> seleccionarProductosPorBodega(String codigoBodega);
    
    List<Inventario> seleccionarInventariosPorBodega(String codigoBodega); 

    List<Bodega> seleccionarBodegasPorProducto(String codigoBarras);

}