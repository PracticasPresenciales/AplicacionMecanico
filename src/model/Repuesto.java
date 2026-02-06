package model;

//IMPORTS
import java.time.LocalDate;

public class Repuesto {

    private String referencia;
    private String modelo;
    private LocalDate fechaPedido;
    private float precio;
    private boolean recibido;
    private int garantiaMeses;

    public Repuesto(String referencia, String modelo, LocalDate fechaPedido, float precio, boolean recibido, int garantiaMeses){
        this.referencia = referencia;
        this.modelo = modelo;
        this.fechaPedido = fechaPedido;
        this.precio = precio;
        this.recibido = recibido;
        this .garantiaMeses = garantiaMeses;
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

    public int getGarantiaMeses() {
        return garantiaMeses;
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
    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }
}
