package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.model.Reporte;

public interface IReporteRepo {

    public Reporte seleccionarPorId(Integer numDocu);

    public List<Reporte> seleccionarTodos();

    public void insertar(Reporte reporte);

    public void actualizarPorId(Reporte reporte);

    public void actualizarParcialPorId(Reporte reporte);

    public void eliminarPorId(Integer numDocu);
}
