package uce.edu.web.api.repository;

import uce.edu.web.api.repository.model.Cabecera;

public interface ICabeceraRepo {

    public Cabecera seleccionarCabeceraPorIdFactura(Integer idFactura);

    public void insertar(Cabecera cabecera);
    
}
