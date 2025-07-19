package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Impuesto;

@Transactional
@ApplicationScoped
public class ImpuestoRepoImpl implements IImpuestoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Impuesto seleccionarPorId(Integer id) {
        return entityManager.find(Impuesto.class, id);
    }

    @Override
    public List<Impuesto> seleccionarTodos() {
        return entityManager.createQuery("SELECT i FROM Impuesto i", Impuesto.class)
                .getResultList();
    }

    @Override
    public void insertar(Impuesto impuesto) {
        entityManager.persist(impuesto);
    }

    @Override
    public void actualizarPorId(Impuesto impuesto) {
        entityManager.merge(impuesto);
    }

    @Override
    public void eliminarImpuestoConProductosPorId(Integer id) {
        Impuesto impuesto = seleccionarPorId(id);
        if (impuesto != null) {
            // 1. Rompe la relación con los productos (borra filas en productofinal)
            impuesto.getProductos().clear();
            entityManager.flush(); // Opcional: sincroniza cambios

            // 2. Ahora sí puedes borrar el impuesto
            entityManager.remove(impuesto);
        }
    }

    @Override
    public void actualizarParcialPorId(Impuesto impuesto) {
        entityManager.merge(impuesto);
    }

}
