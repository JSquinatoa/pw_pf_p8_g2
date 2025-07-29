package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.repository.model.dto.ReporteDTO;

@ApplicationScoped
@Transactional
public class FacturaRepoImpl implements IFacturaRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Factura seleccionarPorId(Integer id) {
        return this.entityManager.find(Factura.class, id);
    }

    @Override
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);
    }

    @Override
    public List<ReporteDTO> SeleccionarReportes() {
        TypedQuery<ReporteDTO> myQuery = this.entityManager.createQuery(
                "SELECT new uce.edu.web.api.repository.model.dto.ReporteDTO(" +
                        "f.id, f.numeroDocumento, f.cliente.cedula, f.cliente.nombre, f.cantidadItems, f.totalImpuestos, f.total) " +
                        "FROM Factura f",
                ReporteDTO.class);
        return myQuery.getResultList();
    }

}
