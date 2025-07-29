package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Detalle;

public interface IDetalleService {

    public void guardar(Detalle detalle);

    public List<Detalle> buscarDetallesPorIdFactura(Integer fact_id);

}
