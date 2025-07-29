package uce.edu.web.api.repository.model.dto;

public class ReporteDTO {

    private Integer id;
    private Integer numeroDocumento;
    private String cedulaCliente;
    private String nombreCliente;
    private Integer cantidadItems;
    private Double totalImpuestos;
    private Double total;

    // Constructores
    public ReporteDTO() {
        super();
    }

    public ReporteDTO(Integer id, Integer numeroDocumento, String cedulaCliente, String nombreCliente,
            Integer cantidadItems,
            Double totalImpuestos, Double total) {
        super();
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.cantidadItems = cantidadItems;
        this.totalImpuestos = totalImpuestos;
        this.total = total;
    }

    // GET y SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
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

}
