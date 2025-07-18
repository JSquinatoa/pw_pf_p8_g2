package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Reporte;

@ApplicationScoped
@Transactional
public class ReporteRepoImpl implements IReporteRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Reporte seleccionarPorId(Integer numDocu) {
        return this.entityManager.find(Reporte.class, numDocu);
    }

    @Override
    public List<Reporte> seleccionarTodos() {
        TypedQuery<Reporte> myQuery = this.entityManager.createQuery("SELECT r FROM Reporte r", Reporte.class);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Reporte reporte) {
        this.entityManager.persist(reporte);
    }

    @Override
    public void actualizarPorId(Reporte reporte) {
        this.entityManager.merge(reporte);
    }

    @Override
    public void actualizarParcialPorId(Reporte reporte) {
        this.entityManager.merge(reporte);
    }

    @Override
    public void eliminarPorId(Integer numDocu) {
        this.entityManager.remove(this.seleccionarPorId(numDocu));
    }

}
