package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Impuesto;
import uce.edu.web.api.repository.model.Producto;

@Transactional
@ApplicationScoped
public class ProductoImpl implements IProductoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private IImpuestoRepo iImpuestoRepo;

    @Override
    public Producto seleccionarPorId(Integer codigoBarras) {
        return entityManager.find(Producto.class, codigoBarras);
    }

    @Override
    public List<Producto> seleccionarTodos() {
        TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizarPorId(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void actualizarParcialPorId(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void eliminarProductoConImpuestosPorId(Integer codigoBarras) {
        Producto producto = seleccionarPorId(codigoBarras);
        if (producto != null) {
            // 1. Rompe la relación con los impuestos (borra filas en productofinal)
            producto.getImpuestos().clear();
            entityManager.flush(); // Opcional: sincroniza cambios con la BD

            // 2. Ahora sí puedes borrar el producto
            entityManager.remove(producto);
        }
    }

    @Override
    public void insertar(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    public List<Impuesto> seleccionarImpuestosPorProducto(Integer codigoBarras) {
        TypedQuery<Impuesto> myQuery = this.entityManager.createQuery(
                "SELECT i FROM Producto p JOIN p.impuestos i WHERE p.codigoBarras = :codigoBarras", Impuesto.class);
        myQuery.setParameter("codigoBarras", codigoBarras);
        return myQuery.getResultList();

    }

    @Override
    public void insertarImpuestoAProducto(Integer codigoBarras, Integer impuestoId) {
        Producto producto = seleccionarPorId(codigoBarras);
        Impuesto impuesto = this.iImpuestoRepo.seleccionarPorId(impuestoId);

        if (producto == null || impuesto == null) {
            throw new IllegalArgumentException("Producto o Impuesto no encontrado");
        }

        producto.getImpuestos().add(impuesto);
    }

    @Override
    public void eliminarImpuestoDeProducto(Integer codigoBarras, Integer impuestoId) {
        Producto producto = seleccionarPorId(codigoBarras);
        Impuesto impuesto = this.iImpuestoRepo.seleccionarPorId(impuestoId);
        producto.getImpuestos().remove(impuesto);
        actualizarPorId(producto);
    }

    @Override
    public void actualizarImpuestoDeProducto(Integer codigoBarras, Integer impuestoIdActual, Integer impuestoIdNuevo) {
        if (impuestoIdActual.equals(impuestoIdNuevo)) {
            return;
        }
        eliminarImpuestoDeProducto(codigoBarras, impuestoIdActual);
        Producto producto = seleccionarPorId(codigoBarras);
        Impuesto impuestoNuevo = this.iImpuestoRepo.seleccionarPorId(impuestoIdNuevo);
        producto.getImpuestos().add(impuestoNuevo);
    }

}
