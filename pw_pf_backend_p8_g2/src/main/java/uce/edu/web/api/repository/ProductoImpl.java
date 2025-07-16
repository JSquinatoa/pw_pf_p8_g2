package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Producto;

@Transactional
@ApplicationScoped
public class ProductoImpl implements IProductoRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Producto seleccionarPorId(Integer id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public Producto seleccionarPorCodigoBarras(String codigoBarras) {
        try {
            return entityManager.createQuery(
                    "SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class)
                    .setParameter("codigoBarras", codigoBarras)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Producto> seleccionarTodos() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public void actualizarPorId(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void insertar(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void actualizarParcialPorId(Producto producto) {
        entityManager.merge(producto);
    }

}
