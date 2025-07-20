package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

@Transactional
@ApplicationScoped
public class ProductoRepoImpl implements IProductoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private IImpuestoRepo iImpuestoRepo;

    @Override
    public Producto seleccionarPorCodigoBarras(String codigoBarras) {
        try {
            TypedQuery<Producto> myQuery = this.entityManager
                    .createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class);
            myQuery.setParameter("codigoBarras", codigoBarras);
            return myQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Producto> seleccionarTodos() {
        TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Producto producto) {
        this.entityManager.persist(producto);
    }

    @Override
    public void actualizarPorCodigoBarras(Producto producto) {
        this.entityManager.merge(producto);
    }

    @Override
    public void eliminarPorCodigoBarras(String codigoBarras) {
        Producto producto = seleccionarPorCodigoBarras(codigoBarras);
        if (producto != null) {
            // Elimina eficientemente todos los registros relacionados en Inventario
            this.entityManager.createQuery(
                    "DELETE FROM Inventario i WHERE i.producto.id = :productoId")
                    .setParameter("productoId", producto.getId())
                    .executeUpdate();

            this.entityManager.remove(producto);
        }
    }

    // relaciones

    @Override
    public List<Impuesto> seleccionarImpuestosPorProducto(String codigoBarras) {
        TypedQuery<Impuesto> myQuery = this.entityManager.createQuery(
                "SELECT i FROM Producto p JOIN p.impuestos i WHERE p.codigoBarras = :codigoBarras", Impuesto.class);
        myQuery.setParameter("codigoBarras", codigoBarras);
        return myQuery.getResultList();

    }

    @Override
    public void insertarImpuestoAProducto(String codigoBarras, Integer impuestoId) {
        Producto producto = seleccionarPorCodigoBarras(codigoBarras);
        Impuesto impuesto = iImpuestoRepo.seleccionarPorId(impuestoId);

        if (producto == null || impuesto == null) {
            throw new IllegalArgumentException("Producto o Impuesto no encontrado");
        }

        if (!producto.getImpuestos().contains(impuesto)) {
            producto.getImpuestos().add(impuesto);
            this.entityManager.merge(producto);
        }
    }

    @Override
    public void eliminarImpuestoDeProducto(String codigoBarras, Integer impuestoId) {
        Producto producto = seleccionarPorCodigoBarras(codigoBarras);
        Impuesto impuesto = iImpuestoRepo.seleccionarPorId(impuestoId);

        if (producto.getImpuestos().contains(impuesto)) {
            producto.getImpuestos().remove(impuesto);
            this.entityManager.merge(producto);
        }
    }

    @Override
    public void actualizarImpuestoDeProducto(String codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo) {
        if (impuestoIdActual.equals(impuestoIdNuevo)) {
            return;
        }
        eliminarImpuestoDeProducto(codigoBarras, impuestoIdActual);
        Producto producto = seleccionarPorCodigoBarras(codigoBarras);
        Impuesto impuestoNuevo = this.iImpuestoRepo.seleccionarPorId(impuestoIdNuevo);
        producto.getImpuestos().add(impuestoNuevo);
    }

}
