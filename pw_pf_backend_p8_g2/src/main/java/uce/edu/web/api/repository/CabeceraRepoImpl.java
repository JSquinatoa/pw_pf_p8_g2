package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Cabecera;

@ApplicationScoped
@Transactional
public class CabeceraRepoImpl implements ICabeceraRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cabecera seleccionarCabeceraPorIdReporte(Integer numDocu) {
        TypedQuery<Cabecera> myQuery = entityManager.createQuery("SELECT c FROM Cabecera c WHERE c.factura.id = :numDocu", Cabecera.class);
        myQuery.setParameter("numDocu", numDocu);
        return myQuery.getSingleResult();
    }

    @Override
    public void insertar(Cabecera cabecera) {
        this.entityManager.persist(cabecera);
    }

}
