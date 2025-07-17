package uce.edu.web.api.repository.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer nombreCliente;

    @OneToMany(mappedBy = "reporte")
    @Column(name = "reporte_cabecera")
    private List<Cabecera> cabecera;

    @Column(name = "reporte_cantidad")
    private Integer cantidadItems;

    @OneToMany(mappedBy = "reporte")
    @Column(name = "reporte_detalle")
    private List<Detalle> detalle;

    @Column(name = "reporte_impuestos")
    private Double totalImpuestos;

    @Column(name = "reporte_total")
    private Double total;

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

    public Integer getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(Integer nombreCliente) {
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

    
    

    

}

class Detalle {

}

class Cabecera {

}
