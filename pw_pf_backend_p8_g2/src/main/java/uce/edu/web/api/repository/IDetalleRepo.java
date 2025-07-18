package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Detalle;

public interface IDetalleRepo {

    List<Detalle> seleccionarDetallesPorIdReporte(Integer numDocu);
    
    public void insertar(Detalle detalle);
}
