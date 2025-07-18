package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Detalle;

@ApplicationScoped
@Transactional
public class DetalleRepoImpl implements IDetalleRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Detalle> seleccionarDetallesPorIdReporte(Integer numDocu) {
        TypedQuery<Detalle> myQuery = this.entityManager.createQuery("SELECT h FROM Detalle h WHERE h.reporte.numDocu =:numDocu", Detalle.class);
        myQuery.setParameter("numDocu", numDocu);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Detalle detalle) {
        this.entityManager.persist(detalle);
    }
    
}
