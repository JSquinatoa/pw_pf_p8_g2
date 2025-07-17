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
    public Bodega seleccionarPorCodigo(Integer codigo) {
        return entityManager.find(Bodega.class, codigo);
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        return entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class).getResultList();
    }

    @Override
    public void actualizarPorCodigo(Bodega bodega) {
        entityManager.merge(bodega);
    }

    @Override
    public void insertar(Bodega bodega) {
        entityManager.persist(bodega);
    }

    @Override
    public void eliminarPorCodigo(Integer codigo) {
        Bodega bodega = entityManager.find(Bodega.class, codigo);
        if (bodega != null) {
            entityManager.remove(bodega);
        }
    }

    @Override
    public void actualizarParcialPorCodigo(Integer codigo, Bodega bodega) {
        Bodega existingBodega = entityManager.find(Bodega.class, codigo);
        if (existingBodega != null) {
            if (bodega.getNombre() != null) {
                existingBodega.setNombre(bodega.getNombre());
            }
            if (bodega.getUbicacion() != null) {
                existingBodega.setUbicacion(bodega.getUbicacion());
            }
            entityManager.merge(existingBodega);
        }
    }

}
