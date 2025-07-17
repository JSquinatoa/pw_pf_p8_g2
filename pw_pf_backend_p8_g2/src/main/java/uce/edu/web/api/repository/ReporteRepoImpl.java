package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Reporte;

@ApplicationScoped
@Transactional
public class ReporteRepoImpl implements IReporteRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Reporte seleccionarReportePorId(Integer numDocu) {
        return this.entityManager.find(Reporte.class, numDocu);
    }

    @Override
    public List<Reporte> seleccionarTodosReportes() {
        return this.entityManager.createQuery("SELECT r FROM Reporte r", Reporte.class).getResultList();
       
    }

    @Override
    public void insertarReporte(Reporte reporte) {
        this.entityManager.persist(reporte);
    }

    @Override
    public void actualizarReportePorId(Reporte reporte) {
        this.entityManager.merge(reporte);
    }

    @Override
    public void actualizarParcialReporte(Reporte reporte) {
        this.entityManager.merge(reporte);
    }

    @Override
    public void borrarPorId(Integer numDocu) {
        this.entityManager.remove(this.seleccionarReportePorId(numDocu));
    }

}
