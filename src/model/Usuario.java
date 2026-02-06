package model;

// IMPORTS
import java.time.LocalDate;


public class Usuario {

    private String nombre;
    private String contraseña;
    private String puesto;
    private LocalDate fechaAlta;
    private  String observaciones;

    public Usuario(String nombre, String contraseña, String puesto, LocalDate fechaAlta, String observaciones) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.puesto = puesto;
        this.fechaAlta = fechaAlta;
        this.observaciones = observaciones;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }

   public String getContraseña() {
        return contraseña;
   }

   public String getPuesto() {
        return puesto;
   }

   public LocalDate getFechaAlta() {
        return fechaAlta;
   }

   public String getObservaciones() {
        return observaciones;
   }


   //SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.puesto = contraseña;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
