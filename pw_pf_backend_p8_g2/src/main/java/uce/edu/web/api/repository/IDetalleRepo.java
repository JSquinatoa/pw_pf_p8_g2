package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Detalle;

public interface IDetalleRepo {

    public void insertar(Detalle detalle);

    public List<Detalle> seleccionarDetallesPorIdFactura(Integer fact_id);

}
