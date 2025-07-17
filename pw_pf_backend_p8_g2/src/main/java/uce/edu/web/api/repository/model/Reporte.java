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
    @Column(name = "rept_id")
    private Integer numDocu;

    @Column(name = "reporte_idcl")
    private Integer idCli;

    @Column(name = "reporte_nombrecl")
    private String nombreCliente;

    @Column(name = "reporte_cantidad")
    private Integer cantidadItems;

    @Column(name = "reporte_impuestos")
    private Double totalImpuestos;

    @Column(name = "reporte_total")
    private Double total;

    @OneToOne(mappedBy = "reporte")
    private List<Cabecera> cabecera;

    @OneToMany(mappedBy = "reporte")
    private List<Detalle> detalle;

    public Integer getNumDocu() {
        return numDocu;
    }

    public void setNumDocu(Integer id) {
        this.numDocu = id;
    }

    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer id_cliente) {
        this.idCli = id_cliente;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
}
