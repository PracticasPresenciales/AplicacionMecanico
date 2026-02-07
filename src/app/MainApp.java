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

import java.io.*;
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

    // LOgin
    private Usuario usuarioEnSesion = null;

    @Override
    public void start(Stage stage) {

        //TODO CARGA DE DATOS
        cargarUsuarios();
        cargarClientes();
        cargarVehiculos();
        cargarRepuestos();

        // Admin/1234
        if (!existeUsuario("Admin")) {
            usuarios.add(new Usuario("Admin", "1234", "", LocalDate.now(), ""));
            guardarUsuarios();
        }

        // Mostrar login primero
        mostrarLogin(stage);
    }

    // LOGIN
    private void mostrarLogin(Stage stage) {

        Label titulo = new Label("Login");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtUsuario = new TextField();
        PasswordField txtPassword = new PasswordField();

        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setDefaultButton(true);

        btnEntrar.setOnAction(e -> {
            String user = (txtUsuario.getText() == null) ? "" : txtUsuario.getText().trim();
            String pass = (txtPassword.getText() == null) ? "" : txtPassword.getText();

            Usuario u = loginCorrecto(user, pass);
            if (u != null) {
                usuarioEnSesion = u;
                mostrarUIOriginal(stage);   // aquí entra en tu UI real (la del segundo MainApp)
            } else {
                lblError.setText("Usuario o contraseña incorrectos");
            }
        });

        txtPassword.setOnAction(e -> btnEntrar.fire());

        GridPane gridLogin = new GridPane();
        gridLogin.setPadding(new Insets(12));
        gridLogin.setHgap(10);
        gridLogin.setVgap(10);

        gridLogin.add(titulo, 0, 0, 2, 1);
        gridLogin.add(new Label("User"), 0, 1);
        gridLogin.add(txtUsuario, 1, 1);
        gridLogin.add(new Label("Password"), 0, 2);
        gridLogin.add(txtPassword, 1, 2);
        gridLogin.add(btnEntrar, 1, 3);
        gridLogin.add(lblError, 1, 4);

        stage.setScene(new Scene(gridLogin, 360, 220));
        stage.setTitle("Login - Gestión Taller");
        stage.setResizable(false);
        stage.show();
    }

    private Usuario loginCorrecto(String user, String pass) {
        for (Usuario u : usuarios) {
            if (u.getNombre() != null && u.getContraseña() != null
                    && u.getNombre().equalsIgnoreCase(user)
                    && u.getContraseña().equals(pass)) {
                return u;
            }
        }
        return null;
    }

    private boolean existeUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombre() != null && u.getNombre().equalsIgnoreCase(nombre)) return true;
        }
        return false;
    }


    // UI
    private void mostrarUIOriginal(Stage stage) {

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
        stage.setResizable(true);
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

        txtNombreUsuario = new TextField();
        txtContraseñaUsuario = new PasswordField();
        txtPuestoUsuario = new TextField();
        dpFechaAltaUsuario = new DatePicker();
        txtObsUsuario = new TextArea();
        txtObsUsuario.setPrefRowCount(4);

        //BOTONES FORMULARIO
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);
        Button btnAnterior = new Button("Anterior");
        btnAnterior.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setMinWidth(100);

        btnGuardar.setOnAction(e -> {

            if (txtNombreUsuario.getText().isBlank()) {
                mostrarError("Error", "El nombre es obligatorio");
                return;
            }

            if (txtContraseñaUsuario.getText().isBlank()) {
                mostrarError("Error", "La contraseña es obligatoria");
                return;
            }

            if (dpFechaAltaUsuario.getValue() == null) {
                mostrarError("Error", "Debes seleccionar una fecha");
                return;
            }

            Usuario u = new Usuario(
                    txtNombreUsuario.getText(),
                    txtContraseñaUsuario.getText(),
                    txtPuestoUsuario.getText(),
                    dpFechaAltaUsuario.getValue(),
                    txtObsUsuario.getText()
            );

            usuarios.add(u);
            guardarUsuarios();
            limpiarFormulario();
        });

        btnAnterior.setOnAction(e -> {
            if (indiceUsuario > 0) {
                indiceUsuario--;
                mostrarUsuario();
            }
        });

        btnSiguiente.setOnAction(e -> {
            if (indiceUsuario < usuarios.size() - 1) {
                indiceUsuario++;
                mostrarUsuario();
            }
        });

        grid.add(lblNombre, 0, 0);
        grid.add(txtNombreUsuario, 1, 0);

        grid.add(lblContraseña, 0, 1);
        grid.add(txtContraseñaUsuario, 1, 1);

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
        btnAnterior.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setMinWidth(100);

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
        btnAnterior.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setMinWidth(100);

        btnGuardar.setOnAction(e -> {

            if (txtModeloVehiculo.getText().isBlank()) {
                mostrarError("Error", "El modelo es obligatorio");
                return;
            }

            if (txtMatriculaVehiculo.getText().isBlank()) {
                mostrarError("Error", "La matrícula es obligatoria");
                return;
            }

            if (dpFechaLlegadaVehiculo.getValue() == null) {
                mostrarError("Error", "Debes indicar la fecha de llegada");
                return;
            }

            Vehiculo v = new Vehiculo(
                    txtModeloVehiculo.getText(),
                    txtMatriculaVehiculo.getText(),
                    txtTelefonoVehiculo.getText(),
                    dpFechaLlegadaVehiculo.getValue(),
                    txtAveriaVehiculo.getText()
            );

            vehiculos.add(v);
            guardarVehiculos();
            limpiarFormulario();
        });

        btnAnterior.setOnAction(e -> {
            if (indiceVehiculo > 0) {
                indiceVehiculo--;
                mostrarVehiculo();
            }
        });

        btnSiguiente.setOnAction(e -> {
            if (indiceVehiculo < vehiculos.size() - 1) {
                indiceVehiculo++;
                mostrarVehiculo();
            }
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

        txtReferenciaRepuesto.setPrefWidth(250);
        txtModeloRepuesto.setPrefWidth(250);
        dpFechaPedidoRepuesto.setPrefWidth(250);
        txtPrecioRepuesto.setPrefWidth(250);
        txtGarantiaRepuesto.setPrefWidth(250);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setMinWidth(100);
        Button btnAnterior = new Button("Anterior");
        btnAnterior.setMinWidth(100);
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setMinWidth(100);

        btnGuardar.setOnAction(e -> {

                    if (txtReferenciaRepuesto.getText().isBlank()) {
                        mostrarError("Error", "La referencia es obligatoria");
                        return;
                    }

                    if (dpFechaPedidoRepuesto.getValue() == null) {
                        mostrarError("Error", "Debes indicar la fecha del pedido");
                        return;
                    }

                    float precio;
                    int garantia;

                    try {
                        precio = Float.parseFloat(txtPrecioRepuesto.getText());
                        garantia = Integer.parseInt(txtGarantiaRepuesto.getText());
                    } catch (Exception ex) {
                        mostrarError("Error", "Precio y garantía deben ser números");
                        return;
                    }

                    Repuesto r = new Repuesto(
                            txtReferenciaRepuesto.getText(),
                            txtModeloRepuesto.getText(),
                            dpFechaPedidoRepuesto.getValue(),
                            precio,
                            chkRecibidoRepuesto.isSelected(),
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

        btnSiguiente.setOnAction(e -> {
            if (indiceRepuesto < repuestos.size() - 1) {
                indiceRepuesto++;
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


    //TODO MOSTRAR ALERTAS
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //MUESTRA DE DATOS
    private void mostrarUsuario() {

        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()) return;

        Usuario u = usuarios.get(indiceUsuario);

        txtNombreUsuario.setText(u.getNombre());
        txtContraseñaUsuario.setText(u.getContraseña());
        txtPuestoUsuario.setText(u.getPuesto());
        dpFechaAltaUsuario.setValue(u.getFechaAlta());
        txtObsUsuario.setText(u.getObservaciones());
    }

    private void mostrarCliente() {

        if (indiceCliente < 0 || indiceCliente >= clientes.size()) return;

        Cliente c = clientes.get(indiceCliente);

        txtNombreCliente.setText(c.getNombre());
        txtApellidosCliente.setText(c.getApellidos());
        txtDniCliente.setText(c.getDni());
        dpUltimaVisitaCliente.setValue(c.getFechaUltimaVisita());
        chkTipoCliente.setSelected(c.isTipo());
    }

    private void mostrarVehiculo() {

        if (indiceVehiculo < 0 || indiceVehiculo >= vehiculos.size()) return;

        Vehiculo v = vehiculos.get(indiceVehiculo);

        txtModeloVehiculo.setText(v.getModelo());
        txtMatriculaVehiculo.setText(v.getMatricula());
        txtTelefonoVehiculo.setText(v.getTelefonoDueno());
        dpFechaLlegadaVehiculo.setValue(v.getFechaLlegada());
        txtAveriaVehiculo.setText(v.getAveria());
    }

    private void mostrarRepuesto() {

        if (indiceRepuesto < 0 || indiceRepuesto >= repuestos.size()) return;

        Repuesto r = repuestos.get(indiceRepuesto);

        txtReferenciaRepuesto.setText(r.getReferencia());
        txtModeloRepuesto.setText(r.getModelo());
        dpFechaPedidoRepuesto.setValue(r.getFechaPedido());
        txtPrecioRepuesto.setText(String.valueOf(r.getPrecio()));
        txtGarantiaRepuesto.setText(String.valueOf(r.getGarantiaMeses()));
        chkRecibidoRepuesto.setSelected(r.isRecibido());
    }

    //CARGADO DE DATOS
    private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);

                if (p.length < 2) continue;

                String nombre = p[0];
                String pass = p[1];
                String puesto = (p.length > 2) ? p[2] : "";

                LocalDate fecha = LocalDate.now();
                if (p.length > 3 && p[3] != null && !p[3].isBlank() && !"null".equalsIgnoreCase(p[3])) {
                    try { fecha = LocalDate.parse(p[3]); } catch (Exception ignored) {}
                }

                String obs = (p.length > 4) ? p[4] : "";

                if (nombre != null && !nombre.isBlank() && pass != null && !pass.isBlank()) {
                    usuarios.add(new Usuario(nombre, pass, puesto, fecha, obs));
                }
            }
        } catch (Exception ignored) {}
    }

    private void cargarClientes() {
        clientes.clear();

        File f = new File(FICHERO_CLIENTES);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_CLIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);
                if (p.length < 6) continue;

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
        vehiculos.clear();

        File f = new File(FICHERO_VEHICULOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_VEHICULOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);
                if (p.length < 5) continue;

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
        repuestos.clear();

        File f = new File(FICHERO_REPUESTOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_REPUESTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);
                if (p.length < 6) continue;

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
