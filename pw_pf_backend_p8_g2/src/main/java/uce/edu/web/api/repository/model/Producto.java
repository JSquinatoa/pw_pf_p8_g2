package uce.edu.web.api.repository.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_codigoBarras")
    private Integer codigoBarras;
    @Column(name = "prod_nombre")
    private String nombre;
    @Column(name = "prod_categoria")
    private String categoria;
    @Column(name = "prod_stock")
    private Integer stock;
    @Column(name = "prod_precio")
    private Double precio;
    
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<ProductoImpuesto> productoImpuestos = new ArrayList<>();

    public Integer getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Integer codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<ProductoImpuesto> getProductoImpuestos() {
        return productoImpuestos;
    }

    public void setProductoImpuestos(List<ProductoImpuesto> productoImpuestos) {
        this.productoImpuestos = productoImpuestos;
    }

    /*public List<Impuesto> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }*/
    

}
