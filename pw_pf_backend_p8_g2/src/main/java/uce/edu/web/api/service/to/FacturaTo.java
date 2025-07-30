package uce.edu.web.api.service.to;

import java.time.LocalDate;
import java.util.List;

public class FacturaTo {

    private Integer id;
    private String rucEmpresa;
    private Integer numeroDocumento;
    private String establecimiento;
    private String puntoEmision;
    private LocalDate fechaEmision;
    private String cedulaCliente;
    private Integer cantidadItems;
    private Double subTotal;
    private Double totalImpuestos;
    private Double total;
    private String estado;
    private List<DetalleTo> detalleTos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getPuntoEmision() {
        return puntoEmision;
    }

    public void setPuntoEmision(String puntoEmision) {
        this.puntoEmision = puntoEmision;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(Double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleTo> getDetalleTos() {
        return detalleTos;
    }

    public void setDetalleTos(List<DetalleTo> detalleTos) {
        this.detalleTos = detalleTos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

}
