package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Impuesto;

@Transactional
@ApplicationScoped
public class ImpuestoImpl implements IImpuestoRepo {
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
    public void eliminarPorId(Integer id) {
        entityManager.remove(seleccionarPorId(id));
    }

    @Override
    public void actualizarParcialPorId(Impuesto impuesto) {
        entityManager.merge(impuesto);
    }

}
