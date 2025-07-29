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
public class DetalleRepoImpl implements IDetalleRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Detalle detalle) {
        this.entityManager.persist(detalle);
    }

    @Override
    public List<Detalle> seleccionarDetallesPorIdFactura(Integer fact_id) {
        TypedQuery<Detalle> myQuery = this.entityManager
                .createQuery("SELECT d FROM Detalle d WHERE d.factura.id =:fact_id", Detalle.class);
        myQuery.setParameter("fact_id", fact_id);
        return myQuery.getResultList();
    }

}
