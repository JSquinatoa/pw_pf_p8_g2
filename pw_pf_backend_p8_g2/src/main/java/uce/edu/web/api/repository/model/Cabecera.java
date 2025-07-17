package uce.edu.web.api.repository.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cabecera")
public class Cabecera {

    @Id
    @GeneratedValue
    @Column(name = "cabe_id")
    private Integer id;
    @Column(name = "cabe_rucEmpresa")
    private String rucEmpresa;
    @Column(name = "cabe_numDocumeto")
    private String numDocumeto;
    @Column(name = "cabe_establecimiento")
    private String establecimiento;
    @Column(name = "cabe_puntoEmision")
    private String puntoEmision;
    @Column(name = "cabe_fechaEmision")
    private LocalDate fechaEmision;
    @Column(name = "cabe_idCliente")
    private String idCliente;
    @Column(name = "cabe_nombreCliente")
    private String nombreCliente;
    @Column(name = "cabe_direccion")
    private String direccion;
    @Column(name = "cabe_correo")
    private String correo;

    // Relaciones 
    @OneToOne(mappedBy = "cabecera")
    private Factura factura;
    
    // GET y SET
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

    public String getNumDocumeto() {
        return numDocumeto;
    }

    public void setNumDocumeto(String numDocumeto) {
        this.numDocumeto = numDocumeto;
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}