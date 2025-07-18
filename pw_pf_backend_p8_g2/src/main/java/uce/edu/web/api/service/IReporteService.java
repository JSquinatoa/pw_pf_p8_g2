package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.model.Reporte;

public interface IReporteService {

    public Reporte buscarPorId(Integer numDocu);

    public List<Reporte> buscarTodos();

    public void guardar(Reporte reporte);

    public void actualizarPorId(Reporte reporte);

    public void actualizarParcialPorId(Reporte reporte);

    public void borrarPorId(Integer numDocu);

}
