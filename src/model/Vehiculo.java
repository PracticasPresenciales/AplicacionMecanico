
import java.time.LocalDate;

public class Vehiculo {

    private String modelo;
    private String matricula;
    private String telefonoDueno;
    private LocalDate fechaLlegada;
    private String averia;

    public Vehiculo(String modelo, String matricula, String telefonoDueno, LocalDate fechaLlegada, String averia) {
      this.modelo =modelo;
      this.matricula =matricula;
      this.telefonoDueno
      this.fechaLlegada =fechaLlegada;
      this.averia =averia;
  }

    //GETTERS
    {
        public String getModelo() {
            return modelo;
    }
        public String getMatricula() {
        return modelo;
    }
        public String getTelefonoDueno() {
        return modelo;
    }
        public String getFechaLlegada() {
        return modelo;
    }
        public String getAveria() {
        return modelo;
    }

    // SETTERS
    public void setModelo(String modelo){
            this.modelo = modelo;
    }

    public void setmatricula(String matricula){
        this.matricula = matricula;
    }

    public void setTelefonoDueno(String telefonoDueno){
        this.telefonoDueno = telefonoDueno;
    }

    public void setFechaLlegada(LocalDate fechaLlegada){
        this.fechaLlegada = fechaLlegada;
    }

    public void setAveria(String averia){
        this.averia = averia;
    }

    }
