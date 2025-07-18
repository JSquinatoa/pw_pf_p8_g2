package uce.edu.web.api.repository.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rept_numDocu")
    private Integer numDocu;
    @Column(name = "reporte_idCli")
    private Integer idCli;
    @Column(name = "reporte_nombreCliente")
    private String nombreCliente;
    @Column(name = "reporte_cantidadItems")
    private Integer cantidadItems;
    @Column(name = "reporte_totalImpuestos")
    private Double totalImpuestos;
    @Column(name = "reporte_total")
    private Double total;

    // Relaciones
    @OneToOne(mappedBy = "reporte")
    private List<Cabecera> cabecera;

    @OneToMany(mappedBy = "reporte")
    private List<Detalle> detalle;

    // GET y SET

    public Integer getNumDocu() {
        return numDocu;
    }

    public void setNumDocu(Integer numDocu) {
        this.numDocu = numDocu;
    }

    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer idCli) {
        this.idCli = idCli;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
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

    public List<Cabecera> getCabecera() {
        return cabecera;
    }

    public void setCabecera(List<Cabecera> cabecera) {
        this.cabecera = cabecera;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

}
