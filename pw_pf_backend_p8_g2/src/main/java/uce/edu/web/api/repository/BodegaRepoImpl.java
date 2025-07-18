package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
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
    public Bodega seleccionarPorId(Integer id) {
        return this.entityManager.find(Bodega.class, id);
    }

    @Override
    public Bodega seleccionarPorCodigo(Integer codigo) {
        TypedQuery<Bodega> myQuery = this.entityManager.createQuery("SELECT b FROM Bodega b WHERE b.codigo = :codigo", Bodega.class);
        myQuery.setParameter("codigo", codigo);
        return myQuery.getSingleResult();
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        TypedQuery<Bodega> myQuery = this.entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizarPorId(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void actualizarParcialPorId(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void actualizarParcialPorCodigo(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void insertar(Bodega bodega) {
        this.entityManager.persist(bodega);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void eliminarPorCodigo(Integer codigo) {
        this.entityManager.remove(this.seleccionarPorCodigo(codigo));
    }

}
