package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.model.Cliente;

@ApplicationScoped
@Transactional
public class ClienteRepoImpl implements IClienteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cliente seleccionarPorCedula(String cedula) {
        return this.entityManager.find(Cliente.class, cedula);
    }

    @Override
    public List<Cliente> seleccionarTodos() {
        TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizarPorCedula(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Override
    public void actualizarParcialPorCedula(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Override
    public void eliminarPorCedula(String cedula) {
        this.entityManager.remove(this.seleccionarPorCedula(cedula));
    }

    @Override
    public void insertar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

}
