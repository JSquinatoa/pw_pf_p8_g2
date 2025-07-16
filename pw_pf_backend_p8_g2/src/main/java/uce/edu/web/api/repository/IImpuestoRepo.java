package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;

public interface IImpuestoRepo {

    public Impuesto seleccionarPorId(Integer id);

    public List<Impuesto> seleccionarTodos();

    public void insertar(Impuesto impuesto);

    public void actualizarPorId(Impuesto impuesto);

    public void actualizarParcialPorId(Impuesto impuesto);

    public void eliminarPorId(Integer id);
}
