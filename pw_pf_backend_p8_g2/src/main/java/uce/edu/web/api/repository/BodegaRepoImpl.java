package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Bodega;

@Transactional
@ApplicationScoped
public class BodegaRepoImpl implements IBodegaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Bodega seleccionarPorCodigo(String codigo) {
        try {
            TypedQuery<Bodega> myQuery = this.entityManager
                    .createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class);
            myQuery.setParameter("codigo", codigo);
            return myQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        TypedQuery<Bodega> myQuery = this.entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Bodega bodega) {
        this.entityManager.persist(bodega);
    }

    @Override
    public void actualizar(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void eliminarPorCodigo(String codigo) {
        Bodega bodega = seleccionarPorCodigo(codigo);
        this.entityManager.createQuery("DELETE FROM Inventario i WHERE i.bodega.id = :id")
                .setParameter("id", bodega.getId())
                .executeUpdate();
        this.entityManager.remove(bodega);
    }

}
