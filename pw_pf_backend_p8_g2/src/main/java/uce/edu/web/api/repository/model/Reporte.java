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
    @Column(name = "rept_idCli")
    private Integer idCli;
    @Column(name = "rept_nombreCliente")
    private String nombreCliente;
    @Column(name = "rept_cantidadItems")
    private Integer cantidadItems;
    @Column(name = "rept_totalImpuestos")
    private Double totalImpuestos;
    @Column(name = "rept_total")
    private Double total;

    // Relaciones
    @OneToOne(mappedBy = "reporte")
    private Cabecera cabecera;

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

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

}
