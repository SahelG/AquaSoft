package Controlador;
import java.math.BigDecimal;

public class Mayorista {
    private String idMayorista;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String calle;
    private String colonia;
    private float precio;

    public String getIdMayorista() {
        return idMayorista;
    }

    public void setIdMayorista(String idMayorista) {
        this.idMayorista = idMayorista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoP) {
        this.apellidoPaterno = apellidoP;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoM) {
        this.apellidoMaterno = apellidoM;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
