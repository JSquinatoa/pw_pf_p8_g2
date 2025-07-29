package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.repository.model.dto.ReporteDTO;

public interface IFacturaRepo {

    public Factura seleccionarPorId(Integer id);

    public void insertar(Factura factura);

    public List<ReporteDTO> SeleccionarReportes();

}
