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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    // LISTAS EN MEMORIA
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private List<Repuesto> repuestos = new ArrayList<>();

    //INDICES DE LAS LISTAS
    private int indiceUsuario = -1;
    private int indiceCliente = -1;
    private int indiceVehiculo = -1;
    private int indiceRepuesto = -1;


    //ATRIBUTOS USUARIO
    private TextField txtNombreUsuario;
    private PasswordField txtContraseñaUsuario;
    private TextField txtPuestoUsuario;
    private DatePicker dpFechaAltaUsuario;
    private TextArea txtObsUsuario;

    //ATRIBUTOS CLIENTE
    private TextField txtNombreCliente;
    private TextField txtApellidosCliente;
    private TextField txtDniCliente;
    private DatePicker dpUltimaVisitaCliente;
    private CheckBox chkTipoCliente;

    // ATRIBUTOS VEHICULO
    private TextField txtModeloVehiculo;
    private TextField txtMatriculaVehiculo;
    private TextField txtTelefonoVehiculo;
    private DatePicker dpFechaLlegadaVehiculo;
    private TextArea txtAveriaVehiculo;

    //ATRIBUTOS REPUESTO
    private TextField txtReferenciaRepuesto;
    private TextField txtModeloRepuesto;
    private DatePicker dpFechaPedidoRepuesto;
    private TextField txtPrecioRepuesto;
    private TextField txtGarantiaRepuesto;
    private CheckBox chkRecibidoRepuesto;

    // FICHEROS
    private static final String FICHERO_USUARIOS = "usuarios.txt";
    private static final String FICHERO_CLIENTES = "clientes.txt";
    private static final String FICHERO_VEHICULOS = "vehiculos.txt";
    private static final String FICHERO_REPUESTOS = "repuestos.txt";


    private GridPane grid = new GridPane();

    @Override
    public void start(Stage stage) {

        //TODO CARGA DE DATOS
        cargarUsuarios();
        cargarClientes();
        cargarVehiculos();
        cargarRepuestos();

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

    // TODO CARGA FORMULARIO SEGÚN ENTIDAD
    private void cargarFormulario(String entidad) {
        grid.getChildren().clear();

        switch (entidad) {
            case "Usuarios" -> formularioUsuarios();
            case "Clientes" -> formularioClientes();
            case "Vehículos" -> formularioVehiculos();
            case "Repuestos" -> formularioRepuestos();
        }
    }

    // FORMULARIO USUARIOS
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

                if (txtNombre.getText().isBlank()) {
                    mostrarError("Error", "El nombre es obligatorio");
                    return;
                }

                if (txtContraseña.getText().isBlank()) {
                    mostrarError("Error", "La contraseña es obligatoria");
                    return;
                }

                if (dpFechaAlta.getValue() == null) {
                    mostrarError("Error", "Debes seleccionar una fecha");
                    return;
                }

                Usuario u = new Usuario(
                        txtNombre.getText(),
                        txtContraseña.getText(),
                        txtPuesto.getText(),
                        dpFechaAlta.getValue(),
                        txtObservaciones.getText()
                );

                usuarios.add(u);
                guardarUsuarios();
                limpiarFormulario();
            });

        grid.add(lblNombre, 0, 0);
        grid.add(txtNombreUsuario, 1, 0);


            grid.add(lblNombre, 0, 0);
            grid.add(txtNombre, 1, 0);

        grid.add(lblPuesto, 0, 2);
        grid.add(txtPuestoUsuario, 1, 2);

        grid.add(lblFecha, 0, 3);
        grid.add(dpFechaAltaUsuario, 1, 3);

        grid.add(lblObs, 0, 4);
        grid.add(txtObsUsuario, 1, 4);

        grid.add(btnGuardar, 1, 5);

        grid.add(btnAnterior, 0, 6);
        grid.add(btnSiguiente, 1, 6);

        if (!usuarios.isEmpty()) {
            indiceUsuario = 0;
            mostrarUsuario();
        }

    }

    // GUARDAR USUARIOS

    private void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(FICHERO_USUARIOS)) {
            for (Usuario u : usuarios) {
                pw.println(
                        u.getNombre() + ";" +
                                u.getContraseña() + ";" +
                                u.getPuesto() + ";" +
                                u.getFechaAlta() + ";" +
                                u.getObservaciones()
                );
            }
        } catch (Exception ignored) {}
    }



    // FORMULARIO CLIENTES
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

            txtNombre.setPrefWidth(250);
            txtApellidos.setPrefWidth(250);
            txtDni.setPrefWidth(250);
            dpUltimaVisita.setPrefWidth(250);

            Button btnGuardar = new Button("Guardar");
            btnGuardar.setMinWidth(100);

            btnGuardar.setOnAction(e -> {

                if (txtNombre.getText().isBlank()) {
                    mostrarError("Error", "El nombre es obligatorio");
                    return;
                }

                if (txtDni.getText().isBlank()) {
                    mostrarError("Error", "El DNI es obligatorio");
                    return;
                }

                if (dpUltimaVisita.getValue() == null) {
                    mostrarError("Error", "Debes indicar la última visita");
                    return;
                }

                Cliente c = new Cliente(
                        0,
                        txtNombre.getText(),
                        txtApellidos.getText(),
                        txtDni.getText(),
                        dpUltimaVisita.getValue(),
                        chkTipo.isSelected()
                );

                clientes.add(c);
                guardarClientes();
                limpiarFormulario();
            });


            grid.add(lblNombre, 0, 0);
            grid.add(txtNombre, 1, 0);

        txtNombreCliente = new TextField();
        txtApellidosCliente = new TextField();
        txtDniCliente = new TextField();
        dpUltimaVisitaCliente = new DatePicker();
        chkTipoCliente = new CheckBox("Empresa");


        txtNombreCliente.setPrefWidth(250);
        txtApellidosCliente.setPrefWidth(250);
        txtDniCliente.setPrefWidth(250);
        dpUltimaVisitaCliente.setPrefWidth(250);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);
        Button btnAnterior = new Button("Anterior");
        btnGuardar.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnGuardar.setMinWidth(100);


        btnGuardar.setOnAction(e -> {

            if (txtNombreCliente.getText().isBlank()) {
                mostrarError("Error", "El nombre es obligatorio");
                return;
            }

            if (txtDniCliente.getText().isBlank()) {
                mostrarError("Error", "El DNI es obligatorio");
                return;
            }

            if (dpUltimaVisitaCliente.getValue() == null) {
                mostrarError("Error", "Debes indicar la última visita");
                return;
            }

            Cliente c = new Cliente(
                    0,
                    txtNombreCliente.getText(),
                    txtApellidosCliente.getText(),
                    txtDniCliente.getText(),
                    dpUltimaVisitaCliente.getValue(),
                    chkTipoCliente.isSelected()
            );

            clientes.add(c);
            guardarClientes();
            limpiarFormulario();
        });

        btnAnterior.setOnAction(e -> {
            if (indiceCliente > 0) {
                indiceCliente--;
                mostrarCliente();
            }
        });

        btnSiguiente.setOnAction(e -> {
            if (indiceCliente < clientes.size() - 1) {
                indiceCliente++;
                mostrarCliente();
            }
        });

        grid.add(lblNombre, 0, 0);
        grid.add(txtNombreCliente, 1, 0);

        grid.add(lblApellidos, 0, 1);
        grid.add(txtApellidosCliente, 1, 1);

        grid.add(lblDni, 0, 2);
        grid.add(txtDniCliente, 1, 2);

        grid.add(lblFecha, 0, 3);
        grid.add(dpUltimaVisitaCliente, 1, 3);

        grid.add(lblTipo, 0, 4);
        grid.add(chkTipoCliente, 1, 4);

        grid.add(btnGuardar, 1, 5);

        grid.add(btnAnterior, 0, 6);
        grid.add(btnSiguiente, 1, 6);


        if (!clientes.isEmpty()) {
            indiceCliente = 0;
            mostrarCliente();
        }

    }

    // GUARDAR DATOS CLIENTES
    private void guardarClientes() {
        try (PrintWriter pw = new PrintWriter(FICHERO_CLIENTES)) {
            for (Cliente c : clientes) {
                pw.println(
                        c.getId() + ";" +
                                c.getNombre() + ";" +
                                c.getApellidos() + ";" +
                                c.getDni() + ";" +
                                c.getFechaUltimaVisita() + ";" +
                                c.isTipo()
                );
            }
        } catch (Exception ignored) {}
    }


    // FORMULARIO VEHÍCULOS
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

        txtModeloVehiculo = new TextField();
        txtMatriculaVehiculo = new TextField();
        txtTelefonoVehiculo = new TextField();
        dpFechaLlegadaVehiculo = new DatePicker();
        txtAveriaVehiculo = new TextArea();
        txtAveriaVehiculo.setPrefRowCount(4);


        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);
        Button btnAnterior = new Button("Anterior");
        btnGuardar.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnGuardar.setMinWidth(100);


        btnGuardar.setOnAction(e -> {

            if (txtModelo.getText().isBlank()) {
                mostrarError("Error", "El modelo es obligatorio");
                return;
            }

            if (txtMatricula.getText().isBlank()) {
                mostrarError("Error", "La matrícula es obligatoria");
                return;
            }

            if (dpFechaLlegada.getValue() == null) {
                mostrarError("Error", "Debes indicar la fecha de llegada");
                return;
            }

            Vehiculo v = new Vehiculo(
                    txtModelo.getText(),
                    txtMatricula.getText(),
                    txtTelefono.getText(),
                    dpFechaLlegada.getValue(),
                    txtAveria.getText()
            );

            vehiculos.add(v);
            guardarVehiculos();
            limpiarFormulario();
        });


        grid.add(lblModelo, 0, 0);
        grid.add(txtModeloVehiculo, 1, 0);

        grid.add(lblMatricula, 0, 1);
        grid.add(txtMatriculaVehiculo, 1, 1);

        grid.add(lblTelefono, 0, 2);
        grid.add(txtTelefonoVehiculo, 1, 2);

        grid.add(lblFecha, 0, 3);
        grid.add(dpFechaLlegadaVehiculo, 1, 3);

        grid.add(lblAveria, 0, 4);
        grid.add(txtAveriaVehiculo, 1, 4);

        grid.add(btnGuardar, 1, 5);

        grid.add(btnAnterior, 0, 6);
        grid.add(btnSiguiente, 1, 6);


        if (!vehiculos.isEmpty()) {
            indiceVehiculo = 0;
            mostrarVehiculo();
        }

    }


    // GUARDAR VEHICULOS
    private void guardarVehiculos() {
        try (PrintWriter pw = new PrintWriter(FICHERO_VEHICULOS)) {
            for (Vehiculo v : vehiculos) {
                pw.println(
                        v.getModelo() + ";" +
                                v.getMatricula() + ";" +
                                v.getTelefonoDueno() + ";" +
                                v.getFechaLlegada() + ";" +
                                v.getAveria()
                );
            }
        } catch (Exception ignored) {}
    }


    // FORMULARIO REPUESTOS
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

        txtReferenciaRepuesto = new TextField();
        txtModeloRepuesto = new TextField();
        dpFechaPedidoRepuesto = new DatePicker();
        txtPrecioRepuesto = new TextField();
        txtGarantiaRepuesto = new TextField();
        chkRecibidoRepuesto = new CheckBox("Recibido");

        txtReferencia.setPrefWidth(250);
        txtModelo.setPrefWidth(250);
        dpFechaPedido.setPrefWidth(250);
        txtPrecio.setPrefWidth(250);
        txtGarantia.setPrefWidth(250);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);
        Button btnAnterior = new Button("Anterior");
        btnGuardar.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnGuardar.setMinWidth(100);

        btnGuardar.setOnAction(e -> {

            if (txtReferencia.getText().isBlank()) {
                mostrarError("Error", "La referencia es obligatoria");
                return;
            }

            if (dpFechaPedido.getValue() == null) {
                mostrarError("Error", "Debes indicar la fecha del pedido");
                return;
            }

            float precio;
            int garantia;

            try {
                precio = Float.parseFloat(txtPrecio.getText());
                garantia = Integer.parseInt(txtGarantia.getText());
            } catch (Exception ex) {
                mostrarError("Error", "Precio y garantía deben ser números");
                return;
            }

            Repuesto r = new Repuesto(
                    txtReferencia.getText(),
                    txtModelo.getText(),
                    dpFechaPedido.getValue(),
                    precio,
                    chkRecibido.isSelected(),
                    garantia
            );

            repuestos.add(r);
            guardarRepuestos();
            limpiarFormulario();
        }
        );
        btnAnterior.setOnAction(e -> {
            if (indiceRepuesto > 0) {
                indiceRepuesto--;
                mostrarRepuesto();
            }
        });


        grid.add(lblReferencia, 0, 0);
        grid.add(txtReferenciaRepuesto, 1, 0);

        grid.add(lblModelo, 0, 1);
        grid.add(txtModeloRepuesto, 1, 1);

        grid.add(lblFecha, 0, 2);
        grid.add(dpFechaPedidoRepuesto, 1, 2);

        grid.add(lblPrecio, 0, 3);
        grid.add(txtPrecioRepuesto, 1, 3);

        grid.add(lblRecibido, 0, 4);
        grid.add(chkRecibidoRepuesto, 1, 4);

        grid.add(lblGarantia, 0, 5);
        grid.add(txtGarantiaRepuesto, 1, 5);

        grid.add(btnGuardar, 1, 6);

        grid.add(btnAnterior, 0, 7);
        grid.add(btnSiguiente, 1, 7);


        if (!repuestos.isEmpty()) {
            indiceRepuesto = 0;
            mostrarRepuesto();
        }

    }

    // GUARDAR REPUESTOS
    private void guardarRepuestos() {
        try (PrintWriter pw = new PrintWriter(FICHERO_REPUESTOS)) {
            for (Repuesto r : repuestos) {
                pw.println(
                        r.getReferencia() + ";" +
                                r.getModelo() + ";" +
                                r.getFechaPedido() + ";" +
                                r.getPrecio() + ";" +
                                r.isRecibido() + ";" +
                                r.getGarantiaMeses()
                );
            }
        } catch (Exception ignored) {}
    }


    // LIMPIAR FORMULARIO
    private void limpiarFormulario() {
        grid.getChildren().forEach(n -> {
            if (n instanceof TextField) ((TextField) n).clear();
            if (n instanceof PasswordField) ((PasswordField) n).clear();
            if (n instanceof DatePicker) ((DatePicker) n).setValue(null);
            if (n instanceof TextArea) ((TextArea) n).clear();
            if (n instanceof CheckBox) ((CheckBox) n).setSelected(false);
        });
    }


    //MOSTRAR ALERTAS
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    //CARGADO DE DATOS
    private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");

                usuarios.add(new Usuario(
                        p[0],                    // nombre
                        p[1],                    // contraseña
                        p[2],                    // puesto
                        LocalDate.parse(p[3]),   // fechaAlta
                        p[4]                     // observaciones
                ));
            }
        } catch (Exception ignored) {}
    }

    private void cargarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_CLIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");

                clientes.add(new Cliente(
                        Integer.parseInt(p[0]),
                        p[1],
                        p[2],
                        p[3],
                        LocalDate.parse(p[4]),
                        Boolean.parseBoolean(p[5])
                ));
            }
        } catch (Exception ignored) {}
    }

    private void cargarVehiculos() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_VEHICULOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");

                vehiculos.add(new Vehiculo(
                        p[0],
                        p[1],
                        p[2],
                        LocalDate.parse(p[3]),
                        p[4]
                ));
            }
        } catch (Exception ignored) {}
    }

    private void cargarRepuestos() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_REPUESTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");

                repuestos.add(new Repuesto(
                        p[0],
                        p[1],
                        LocalDate.parse(p[2]),
                        Float.parseFloat(p[3]),
                        Boolean.parseBoolean(p[4]),
                        Integer.parseInt(p[5])
                ));
            }
        } catch (Exception ignored) {}
    }



    public static void main(String[] args) {
        launch();
    }
}
