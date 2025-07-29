package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.model.dto.ReporteDTO;
import uce.edu.web.api.service.to.ReporteDTOTo;

public class ReporteDTOMapper {

    public static ReporteDTOTo toTo(ReporteDTO reporteDTO) {
        if (reporteDTO == null) {
            return null;
        }
        ReporteDTOTo rDTOTo = new ReporteDTOTo();
        rDTOTo.setId(reporteDTO.getId());
        rDTOTo.setNumeroDocumento(reporteDTO.getNumeroDocumento());
        rDTOTo.setCedulaCliente(reporteDTO.getCedulaCliente());
        rDTOTo.setNombreCliente(reporteDTO.getNombreCliente());
        rDTOTo.setCantidadItems(reporteDTO.getCantidadItems());
        rDTOTo.setTotalImpuestos(reporteDTO.getTotalImpuestos());
        rDTOTo.setTotal(reporteDTO.getTotal());

        return rDTOTo;
    }

}
