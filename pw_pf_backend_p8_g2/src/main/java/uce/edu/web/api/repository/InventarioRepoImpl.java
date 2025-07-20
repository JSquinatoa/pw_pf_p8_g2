package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Bodega;
import uce.edu.web.api.repository.model.Inventario;
import uce.edu.web.api.repository.model.Producto;

@ApplicationScoped
@Transactional
public class InventarioRepoImpl implements IInventarioRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private IBodegaRepo iBodegaRepo;

    @Inject
    private IProductoRepo iProductoRepo;

    @Override
    public void insertar(String codigoBodega, String codigoBarras, Integer stock) {
        Bodega bodega = this.iBodegaRepo.seleccionarPorCodigo(codigoBodega);
        Producto producto = this.iProductoRepo.seleccionarPorCodigoBarras(codigoBarras);

        if (bodega == null || producto == null) {
            throw new IllegalArgumentException("Bodega o Producto no existen");
        }

        // Validar que no exista ya la relación
        Inventario existente = entityManager.createQuery(
                "SELECT i FROM Inventario i WHERE i.bodega.id = :bodegaId AND i.producto.id = :productoId",
                Inventario.class)
                .setParameter("bodegaId", bodega.getId())
                .setParameter("productoId", producto.getId())
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (existente != null) {
            throw new IllegalArgumentException("El producto ya está registrado en esta bodega");
        }

        Inventario inventario = new Inventario();
        inventario.setBodega(bodega);
        inventario.setProducto(producto);
        inventario.setStock(stock);
        entityManager.persist(inventario);
    }

    @Override
    public Integer seleccionarStock(String codigoBodega, String codigoBarras) {

        try {
            TypedQuery<Integer> myQuery = this.entityManager.createQuery(
                    "SELECT i.stock FROM Inventario i " +
                            "JOIN i.bodega b JOIN i.producto p " +
                            "WHERE b.codigo = :codigoBodega AND p.codigoBarras = :codigoBarras",
                    Integer.class);
            myQuery.setParameter("codigoBodega", codigoBodega);
            myQuery.setParameter("codigoBarras", codigoBarras);
            return myQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("No existe registro para esa bodega/producto");
        }

    }

    @Override
    public void actualizarStock(String codigoBodega, String codigoBarras, Integer nuevoStock) {
        Inventario inventario = entityManager.createQuery(
                "SELECT i FROM Inventario i " +
                        "JOIN i.bodega b JOIN i.producto p " +
                        "WHERE b.codigo = :codigoBodega AND p.codigoBarras = :codigoBarras",
                Inventario.class)
                .setParameter("codigoBodega", codigoBodega)
                .setParameter("codigoBarras", codigoBarras)
                .getSingleResult();

        inventario.setStock(nuevoStock);
        entityManager.merge(inventario);
    }

    @Override
    public void eliminar(String codigoBodega, String codigoBarras) {
        Inventario inventario = entityManager.createQuery(
                "SELECT i FROM Inventario i " +
                        "JOIN i.bodega b JOIN i.producto p " +
                        "WHERE b.codigo = :codigoBodega AND p.codigoBarras = :codigoBarras",
                Inventario.class)
                .setParameter("codigoBodega", codigoBodega)
                .setParameter("codigoBarras", codigoBarras)
                .getSingleResult();

        entityManager.remove(inventario);
    }

    @Override
    public List<Producto> seleccionarProductosPorBodega(String codigoBodega) {
        return entityManager.createQuery(
                "SELECT p FROM Producto p " +
                        "JOIN Inventario i ON i.producto.id = p.id " +
                        "JOIN Bodega b ON i.bodega.id = b.id " +
                        "WHERE b.codigo = :codigoBodega",
                Producto.class)
                .setParameter("codigoBodega", codigoBodega)
                .getResultList();
    }

    @Override
    public List<Bodega> seleccionarBodegasPorProducto(String codigoBarras) {
        return entityManager.createQuery(
                "SELECT b FROM Bodega b " +
                        "JOIN Inventario i ON i.bodega.id = b.id " +
                        "JOIN Producto p ON i.producto.id = p.id " +
                        "WHERE p.codigoBarras = :codigoBarras",
                Bodega.class)
                .setParameter("codigoBarras", codigoBarras)
                .getResultList();
    }

}
