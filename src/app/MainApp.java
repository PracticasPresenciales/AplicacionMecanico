package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Usuario;
import model.Cliente;
import model.Vehiculo;
import model.Repuesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    // ====== LISTAS EN MEMORIA ======
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private List<Repuesto> repuestos = new ArrayList<>();

    private GridPane grid = new GridPane();

    @Override
    public void start(Stage stage) {

        ComboBox<String> comboEntidad = new ComboBox<>();
        comboEntidad.getItems().addAll(
                "Usuarios",
                "Clientes",
                "Vehículos",
                "Repuestos"
        );
        comboEntidad.setValue("Usuarios");

        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        comboEntidad.setOnAction(e ->
                cargarFormulario(comboEntidad.getValue())
        );

        cargarFormulario("Usuarios");

        VBox root = new VBox(10, comboEntidad, grid);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 500, 420));
        stage.setTitle("Gestión Taller");
        stage.show();
    }

    // ====== CARGA FORMULARIO SEGÚN ENTIDAD ======
    private void cargarFormulario(String entidad) {
        grid.getChildren().clear();

        switch (entidad) {
            case "Usuarios" -> formularioUsuarios();
            case "Clientes" -> formularioClientes();
            case "Vehículos" -> formularioVehiculos();
            case "Repuestos" -> formularioRepuestos();
        }
    }

    // ====== FORMULARIO USUARIOS ======
        private void formularioUsuarios() {

            Label lblNombre = new Label("Nombre");
            Label lblContraseña = new Label("Contraseña");
            Label lblPuesto = new Label("Puesto");
            Label lblFecha = new Label("Fecha de alta");
            Label lblObs = new Label("Observaciones");

            lblNombre.setMinWidth(100);
            lblContraseña.setMinWidth(100);
            lblPuesto.setMinWidth(100);
            lblFecha.setMinWidth(100);
            lblObs.setMinWidth(100);

            TextField txtNombre = new TextField();
            PasswordField txtContraseña = new PasswordField();
            TextField txtPuesto = new TextField();
            DatePicker dpFechaAlta = new DatePicker();
            TextArea txtObservaciones = new TextArea();

            txtObservaciones.setPrefRowCount(4);

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setMinWidth(100);

            btnGuardar.setOnAction(e -> {
                usuarios.add(new Usuario(
                        txtNombre.getText(),
                        txtContraseña.getText(),
                        txtPuesto.getText(),
                        dpFechaAlta.getValue(),
                        txtObservaciones.getText()
                ));
                limpiarFormulario();
            });

            grid.add(lblNombre, 0, 0);
            grid.add(txtNombre, 1, 0);

            grid.add(lblContraseña, 0, 1);
            grid.add(txtContraseña, 1, 1);

            grid.add(lblPuesto, 0, 2);
            grid.add(txtPuesto, 1, 2);

            grid.add(lblFecha, 0, 3);
            grid.add(dpFechaAlta, 1, 3);

            grid.add(lblObs, 0, 4);
            grid.add(txtObservaciones, 1, 4);

            grid.add(btnGuardar, 1, 5);
        }


        // ====== FORMULARIO CLIENTES (BÁSICO) ======
        private void formularioClientes() {

            Label lblNombre = new Label("Nombre");
            Label lblApellidos = new Label("Apellidos");
            Label lblDni = new Label("DNI");
            Label lblFecha = new Label("Fecha última visita");
            Label lblTipo = new Label("Tipo");

            lblNombre.setMinWidth(140);
            lblApellidos.setMinWidth(140);
            lblDni.setMinWidth(140);
            lblFecha.setMinWidth(140);
            lblTipo.setMinWidth(140);

            TextField txtNombre = new TextField();
            TextField txtApellidos = new TextField();
            TextField txtDni = new TextField();
            DatePicker dpUltimaVisita = new DatePicker();
            CheckBox chkTipo = new CheckBox("Tipo");

            // 🔹 MISMO ANCHO QUE USUARIO Y VEHÍCULO
            txtNombre.setPrefWidth(250);
            txtApellidos.setPrefWidth(250);
            txtDni.setPrefWidth(250);
            dpUltimaVisita.setPrefWidth(250);

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setMinWidth(100);

            btnGuardar.setOnAction(e -> {
                Cliente c = new Cliente(
                        0,
                        txtNombre.getText(),
                        txtApellidos.getText(),
                        txtDni.getText(),
                        dpUltimaVisita.getValue(),
                        chkTipo.isSelected()
                );
                clientes.add(c);
                limpiarFormulario();
            });

            grid.add(lblNombre, 0, 0);
            grid.add(txtNombre, 1, 0);

            grid.add(lblApellidos, 0, 1);
            grid.add(txtApellidos, 1, 1);

            grid.add(lblDni, 0, 2);
            grid.add(txtDni, 1, 2);

            grid.add(lblFecha, 0, 3);
            grid.add(dpUltimaVisita, 1, 3);

            grid.add(lblTipo, 0, 4);
            grid.add(chkTipo, 1, 4);

            grid.add(btnGuardar, 1, 5);
        }



    // ====== FORMULARIO VEHÍCULOS ======
    private void formularioVehiculos() {

        Label lblModelo = new Label("Modelo");
        Label lblMatricula = new Label("Matrícula");
        Label lblTelefono = new Label("Teléfono dueño");
        Label lblFecha = new Label("Fecha llegada");
        Label lblAveria = new Label("Avería");

        lblModelo.setMinWidth(140);
        lblMatricula.setMinWidth(140);
        lblTelefono.setMinWidth(140);
        lblFecha.setMinWidth(140);
        lblAveria.setMinWidth(140);

        TextField txtModelo = new TextField();
        TextField txtMatricula = new TextField();
        TextField txtTelefono = new TextField();
        DatePicker dpFechaLlegada = new DatePicker();
        TextArea txtAveria = new TextArea();

        txtAveria.setPrefRowCount(4);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);

        btnGuardar.setOnAction(e -> {
            Vehiculo v = new Vehiculo(
                    txtModelo.getText(),            // modelo
                    txtMatricula.getText(),         // matricula
                    txtTelefono.getText(),          // telefonoDueno
                    dpFechaLlegada.getValue(),      // fechaLlegada
                    txtAveria.getText()             // averia
            );
            vehiculos.add(v);
            limpiarFormulario();
        });

        grid.add(lblModelo, 0, 0);
        grid.add(txtModelo, 1, 0);

        grid.add(lblMatricula, 0, 1);
        grid.add(txtMatricula, 1, 1);

        grid.add(lblTelefono, 0, 2);
        grid.add(txtTelefono, 1, 2);

        grid.add(lblFecha, 0, 3);
        grid.add(dpFechaLlegada, 1, 3);

        grid.add(lblAveria, 0, 4);
        grid.add(txtAveria, 1, 4);

        grid.add(btnGuardar, 1, 5);
    }


    // ====== FORMULARIO REPUESTOS ======
    private void formularioRepuestos() {

        Label lblReferencia = new Label("Referencia");
        Label lblModelo = new Label("Modelo");
        Label lblFecha = new Label("Fecha pedido");
        Label lblPrecio = new Label("Precio");
        Label lblRecibido = new Label("Recibido");
        Label lblGarantia = new Label("Garantía (meses)");

        lblReferencia.setMinWidth(140);
        lblModelo.setMinWidth(140);
        lblFecha.setMinWidth(140);
        lblPrecio.setMinWidth(140);
        lblRecibido.setMinWidth(140);
        lblGarantia.setMinWidth(140);

        TextField txtReferencia = new TextField();
        TextField txtModelo = new TextField();
        DatePicker dpFechaPedido = new DatePicker();
        TextField txtPrecio = new TextField();
        TextField txtGarantia = new TextField();
        CheckBox chkRecibido = new CheckBox("Recibido");

        // 🔹 MISMO ANCHO QUE EL RESTO DE FORMULARIOS
        txtReferencia.setPrefWidth(250);
        txtModelo.setPrefWidth(250);
        dpFechaPedido.setPrefWidth(250);
        txtPrecio.setPrefWidth(250);
        txtGarantia.setPrefWidth(250);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);

        btnGuardar.setOnAction(e -> {
            Repuesto r = new Repuesto(
                    txtReferencia.getText(),               // referencia
                    txtModelo.getText(),                   // modelo
                    dpFechaPedido.getValue(),              // fechaPedido
                    Float.parseFloat(txtPrecio.getText()), // precio
                    chkRecibido.isSelected(),              // recibido
                    Integer.parseInt(txtGarantia.getText())// garantiaMeses
            );
            repuestos.add(r);
            limpiarFormulario();
        });

        grid.add(lblReferencia, 0, 0);
        grid.add(txtReferencia, 1, 0);

        grid.add(lblModelo, 0, 1);
        grid.add(txtModelo, 1, 1);

        grid.add(lblFecha, 0, 2);
        grid.add(dpFechaPedido, 1, 2);

        grid.add(lblPrecio, 0, 3);
        grid.add(txtPrecio, 1, 3);

        grid.add(lblRecibido, 0, 4);
        grid.add(chkRecibido, 1, 4);

        grid.add(lblGarantia, 0, 5);
        grid.add(txtGarantia, 1, 5);

        grid.add(btnGuardar, 1, 6);
    }


    // ====== LIMPIAR FORMULARIO ======
    private void limpiarFormulario() {
        grid.getChildren().forEach(n -> {
            if (n instanceof TextField) ((TextField) n).clear();
            if (n instanceof PasswordField) ((PasswordField) n).clear();
            if (n instanceof DatePicker) ((DatePicker) n).setValue(null);
            if (n instanceof TextArea) ((TextArea) n).clear();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
