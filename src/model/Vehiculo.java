package model;

import java.time.LocalDate;

public class Vehiculo {

    private String modelo;
    private String matricula;
    private String telefonoDueno;
    private LocalDate fechaLlegada;
    private String averia;

    public Vehiculo(String modelo, String matricula, String telefonoDueno, LocalDate fechaLlegada, String averia)
    this.modelo =modelo;
    this.matricula =matricula;
    this.telefonoDueno
    this.fechaLlegada =fechaLlegada;
    this.averia =averia;
}

    //GETTERS
    {
        public String getmodelo() {
            return modelo;
    }
        public String getmatricula() {
        return modelo;
    }
        public String getelefonoDueno() {
        return modelo;
    }
        public String getfechaLlegada() {
        return modelo;
    }
        public String getaveria() {
        return modelo;
    }

    // SETTERS
    public void setmodelo(String modelo){
            this.modelo = modelo;
    }

    public void setmatricula(String matricula){
        this.matricula = matricula;
    }

    public void settelefonoDueno(String telefonoDueno){
        this.telefonoDueno = telefonoDueno;
    }

    public void setFechaLlegada(LocalDate fechaLlegada){
        this.fechaLlegada = fechaLlegada;
    }

    public void setaveria(String averia){
        this.averia = averia;
    }

    }




