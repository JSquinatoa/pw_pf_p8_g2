package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Impuesto;

public interface IImpuestoRepo {

    // Impuesto

    public Impuesto seleccionarPorId(Integer id);

    public List<Impuesto> seleccionarTodos();

    public void actualizarPorId(Impuesto impuesto);

    public void actualizarParcialPorId(Impuesto impuesto);

    public void eliminarImpuestoConProductosPorId(Integer id);

    public void insertar(Impuesto impuesto);

    // Impuesto Producto

}
