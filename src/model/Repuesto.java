package model;

//IMPORTS
import java.time.LocalDate;

public class Repuesto {

    private String referencia;
    private String modelo;
    private LocalDate fechaPedido;
    private float precio;
    private boolean recibido;

    public Repuesto(String referencia, String modelo, LocalDate fechaPedido, float precio, boolean recibido){
        this.referencia = referencia;
        this.modelo = modelo;
        this.fechaPedido = fechaPedido;
        this.precio = precio;
        this.recibido = recibido;
    }

    //GETTERS
    public String getReferencia() {
        return referencia;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public float getPrecio() {
        return precio;
    }

    public boolean isRecibido() {
        return recibido;
    }

    //SETTERS

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }
}
