package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;

public interface IImpuestoService {

    public Impuesto buscarPorId(Integer id);

    public List<Impuesto> buscarTodos();

    public void actualizarPorId(Impuesto impuesto);

    public void actualizarParcialPorId(Impuesto impuesto);

    public void borrarImpuestoConProductosPorId(Integer id);

    public void guardar(Impuesto impuesto);
}
