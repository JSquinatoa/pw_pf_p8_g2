package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Factura;
import uce.edu.web.api.repository.model.dto.ReporteDTO;

public interface IFacturaService {

    public Factura buscarPorId(Integer id);

    public void guardar(Factura factura);

    public List<ReporteDTO> buscarReportes();

}
