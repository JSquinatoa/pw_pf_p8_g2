package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Producto;

@Transactional
@ApplicationScoped
public class ProductoImpl implements IProductoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Producto seleccionarPorCodigoBarras(Integer codigoBarras) {
        return entityManager.find(Producto.class, codigoBarras);
    }

    @Override
    public List<Producto> seleccionarTodos() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public void actualizarPorCodigoBarras(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void insertar(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    public void eliminarPorCodigoBarras(Integer codigoBarras) {
        this.entityManager.remove(this.seleccionarPorCodigoBarras(codigoBarras));
    }

    @Override
    public void actualizarParcialPorCodigoBarras(Producto producto) {
        entityManager.merge(producto);
    }

}
