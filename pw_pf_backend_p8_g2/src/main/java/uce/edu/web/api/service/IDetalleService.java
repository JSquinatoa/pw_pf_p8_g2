package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Detalle;

public interface IDetalleService {

    public List<Detalle> buscarDetallesPorIdReporte(Integer numDocu);

    public void guardar(Detalle detalle);

}
