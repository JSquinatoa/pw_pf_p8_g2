package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IClienteRepo;
import uce.edu.web.api.repository.IFacturaRepo;
import uce.edu.web.api.repository.model.Cliente;
import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.repository.model.dto.ReporteDTO;

@ApplicationScoped
public class FacturaServiceImpl implements IFacturaService {

    @Inject
    private IFacturaRepo iFacturaRepo;

    @Inject
    private IClienteRepo iClienteRepo;

    @Override
    public Factura buscarPorId(Integer id) {
        return this.iFacturaRepo.seleccionarPorId(id);
    }

    @Override
    public void guardar(Factura factura) {
        Cliente c = this.iClienteRepo.seleccionarPorCedula(factura.getCliente().getCedula());
        factura.setCliente(c);
        this.iFacturaRepo.insertar(factura);
    }

    @Override
    public List<ReporteDTO> buscarReportes() {
        return this.iFacturaRepo.SeleccionarReportes();
    }

}
