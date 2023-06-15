package Controlador;

public class Venta {
    private String idVenta;
    private String idMayorista;
    private String idEmpleado;
    private float total;
    //private int cantidad;
    private String fecha;

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdMayorista() {
        return idMayorista;
    }

    public void setIdMayorista(String idMayorista) {
        this.idMayorista = idMayorista;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
