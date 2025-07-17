package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Bodega;

@Transactional
@ApplicationScoped
public class BodegaRepoImpl implements IBodegaRepo {

    @PersistenceContext
    private EntityManager entityManager;

        @Override
    public Bodega seleccionarPorId(Integer id) {
        return entityManager.find(Bodega.class, id);
    }

    @Override
    public Bodega seleccionarPorCodigo(Integer codigo) {
        return entityManager.createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
    }


    @Override
    public List<Bodega> seleccionarTodos() {
        return entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class).getResultList();
    }

    
    @Override
    public void actualizarPorId(Bodega bodega) {
        entityManager.merge(bodega);
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        entityManager.merge(bodega);
    }

    @Override
    public void actualizarParcialPorId(Bodega bodega) {
            entityManager.merge(bodega);
    }

    @Override
    public void actualizarParcialPorCodigo(Bodega bodega) {
            entityManager.merge(bodega);
    }

    @Override
    public void insertar(Bodega bodega) {
        entityManager.persist(bodega);
    }

    @Override
    public void eliminarPorId(Integer id) {
        entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void eliminarPorCodigo(Integer codigo) {
        entityManager.remove(this.seleccionarPorCodigo(codigo));
    }



}
