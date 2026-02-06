package app;

// ============== JAVAFX =============
import javafx.application.Application;     //Clase para toda app JavaFX
import javafx.beans.property.SimpleBooleanProperty; //Boolean observablke
import javafx.collections.*;        //Listas observables
import javafx.collections.transformation.FilteredList;  //Listas filtradas
import javafx.geometry.Insets;  //Margenes y padding
import javafx.scene.Scene;  //Escena principal
import javafx.scene.control.*;      //Botones trablas y demas
import javafx.scene.control.cell.CheckBoxTableCell;     //Celdas con check
import javafx.scene.control.cell.PropertyValueFactory;  //Atributos a columnas
import javafx.scene.layout.*;   //Layouts
import javafx.stage.Stage;      //Ventanas
import javafx.animation.PauseTransition;    //pausa para el splash
import javafx.scene.image.Image;        //Imagenes
import javafx.scene.image.ImageView;    //Vista de imagen
import javafx.scene.text.Font;      //Fuentes
import javafx.util.Duration;        //Duracion para animaciones


//PROYECTO

import model.Cliente;
import model.Repuesto;
import model.Usuario;
import model.Vehiculo;


public class MainApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplicación JavaFX");
        primaryStage.show();
    }
}
