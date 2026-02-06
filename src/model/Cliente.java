package app;

import java.time.LocalDate;

public class Cliente {

    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaUltimaVisita;
    private boolean tipo;

    public Cliente(int id, String nombre, String apellidos, String dni, LocalDate fechaUltimaVisita, boolean tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaUltimaVisita = fechaUltimaVisita;
        this.tipo = tipo;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public boolean isTipo() {
        return tipo;
    }


    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaUltimaVisita(LocalDate fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}

