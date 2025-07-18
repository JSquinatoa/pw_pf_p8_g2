package uce.edu.web.api.service;

import uce.edu.web.api.repository.model.Cabecera;

public interface ICabeceraService {

    public Cabecera buscarCabeceraPorIdReporte(Integer numDocu);

    public void guardar(Cabecera cabecera);

}
