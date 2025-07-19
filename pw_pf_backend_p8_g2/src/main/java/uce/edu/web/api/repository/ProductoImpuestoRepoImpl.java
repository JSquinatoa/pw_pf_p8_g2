package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.ProductoImpuesto;
import uce.edu.web.api.repository.model.ProductoImpuestoId;

@ApplicationScoped
public class ProductoImpuestoRepoImpl implements IProductoImpuestoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoImpuesto> buscarTodos() {
        return entityManager.createQuery(
                "SELECT pi FROM ProductoImpuesto pi JOIN FETCH pi.producto JOIN FETCH pi.impuesto",
                ProductoImpuesto.class).getResultList();
    }

    @Override
    public List<ProductoImpuesto> buscarPorProducto(Integer prod_codigoBarras) {
        return entityManager.createQuery(
                "SELECT pi FROM ProductoImpuesto pi JOIN FETCH pi.producto JOIN FETCH pi.impuesto " +
                        "WHERE pi.id.productoId = :codigoBarras",
                ProductoImpuesto.class)
                .setParameter("codigoBarras", prod_codigoBarras)
                .getResultList();
    }

    @Override
    public List<ProductoImpuesto> buscarPorImpuesto(Integer imp_id) {
        return entityManager.createQuery(
                "SELECT pi FROM ProductoImpuesto pi JOIN FETCH pi.producto JOIN FETCH pi.impuesto " +
                        "WHERE pi.id.impuestoId = :impId",
                ProductoImpuesto.class)
                .setParameter("impId", imp_id)
                .getResultList();
    }

    @Override
    public boolean existeRelacion(Integer prod_codigoBarras, Integer imp_id) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(pi) FROM ProductoImpuesto pi " +
                        "WHERE pi.id.productoId = :codigoBarras AND pi.id.impuestoId = :impId",
                Long.class)
                .setParameter("codigoBarras", prod_codigoBarras)
                .setParameter("impId", imp_id)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public void insertar(ProductoImpuesto productoImpuesto) {
        entityManager.persist(productoImpuesto);
    }

    @Override
    @Transactional
    public void actualizar(ProductoImpuesto productoImpuesto) {
        entityManager.merge(productoImpuesto);
    }

    @Override
    @Transactional
    public void actualizarParcial(ProductoImpuesto productoImpuesto) {
        ProductoImpuestoId idCompuesto = new ProductoImpuestoId(
                productoImpuesto.getProducto().getCodigoBarras(),
                productoImpuesto.getImpuesto().getId());

        ProductoImpuesto existente = entityManager.find(ProductoImpuesto.class, idCompuesto);

        if (existente == null) {
            throw new IllegalArgumentException("La relación producto-impuesto no fue encontrada para actualizar.");
        }

        if (productoImpuesto.getProducto() != null && productoImpuesto.getProducto().getCodigoBarras() != null) {
            existente.setProducto(productoImpuesto.getProducto());
        }
        if (productoImpuesto.getImpuesto() != null && productoImpuesto.getImpuesto().getId() != null) {
            existente.setImpuesto(productoImpuesto.getImpuesto());
        }

        entityManager.merge(existente);
    }

    @Override
    public void eliminar(Integer prod_codigoBarras, Integer imp_id) {
        entityManager.createQuery(
                "DELETE FROM ProductoImpuesto pi " +
                        "WHERE pi.id.productoId = :codigoBarras AND pi.id.impuestoId = :impId")
                .setParameter("codigoBarras", prod_codigoBarras)
                .setParameter("impId", imp_id)
                .executeUpdate();
    }
}