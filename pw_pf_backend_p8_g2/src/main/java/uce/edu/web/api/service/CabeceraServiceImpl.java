package uce.edu.web.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.ICabeceraRepo;
import uce.edu.web.api.repository.model.Cabecera;

@ApplicationScoped
public class CabeceraServiceImpl implements ICabeceraService {

    @Inject
    private ICabeceraRepo iCabeceraRepo;

    @Override
    public Cabecera buscarCabeceraPorIdReporte(Integer numDocu) {
        return this.iCabeceraRepo.seleccionarCabeceraPorIdReporte(numDocu);
    }

    @Override
    public void guardar(Cabecera cabecera) {
        this.iCabeceraRepo.insertar(cabecera);
    }

}
