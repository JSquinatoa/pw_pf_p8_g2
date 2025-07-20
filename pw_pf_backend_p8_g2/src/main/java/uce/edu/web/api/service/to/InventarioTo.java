package uce.edu.web.api.service.to;

public class InventarioTo {

    private Integer stock;
    private String codigoBodega;
    private String codigoBarras;

    // GET y SET
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCodigoBodega() {
        return codigoBodega;
    }

    public void setCodigoBodega(String codigoBodega) {
        this.codigoBodega = codigoBodega;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoProducto) {
        this.codigoBarras = codigoProducto;
    }

}
