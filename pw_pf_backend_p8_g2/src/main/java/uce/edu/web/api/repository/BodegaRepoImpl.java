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
            TypedQuery<Bodega> query = this.entityManager
                    .createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        TypedQuery<Bodega> query = this.entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class);
        return query.getResultList();
    }

    @Override
    public void insertar(Bodega bodega) {
        this.entityManager.persist(bodega);
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void eliminarPorCodigo(String codigo) {
        Bodega bodega = seleccionarPorCodigo(codigo);
        if (bodega != null) {
            // Elimina eficientemente todos los registros relacionados en Inventario
            this.entityManager.createQuery(
                    "DELETE FROM Inventario i WHERE i.bodega.id = :bodegaId")
                    .setParameter("bodegaId", bodega.getId())
                    .executeUpdate();

            this.entityManager.remove(bodega);
        }
    }

}
