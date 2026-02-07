CREATE TABLE  usuarios (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR (20) UNIQUE,
	contraseña VARCHAR (20) NOT NULL,
	puesto VARCHAR (40),
	fecha_alta DATE NOT NULL,
	observaciones VARCHAR (500)
)

CREATE TABLE vehiculos (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	modelo VARCHAR (10),
	matricula VARCHAR (20) UNIQUE NOT NULL,
	telefono_dueño VARCHAR (30),
	fecha_llegada DATE NOT NULL,
	averia VARCHAR (500),
	id_cliente INT UNSIGNED,
	FOREIGN KEY (id_cliente) REFERENCES  clientes (id)
)

CREATE TABLE clientes (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR (20),
	apellidos VARCHAR (20),
	dni VARCHAR (9) UNIQUE NOT NULL,
	fecha_ultima_visita DATE NOT NULL,
	tipo BOOLEAN DEFAULT FALSE
)

CREATE TABLE repuestos (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	recibida BOOLEAN DEFAULT FALSE,
	precio FLOAT DEFAULT 0,
	fecha_pedido DATE NOT NULL,
	modelo VARCHAR (50),
	referencia VARCHAR (50) UNIQUE NOT NULL, 
	garantia_meses INT NOT NULL
)

CREATE TABLE usuario_repuestos (
	id_usuario INT UNSIGNED,
	FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
	id_repuesto INT UNSIGNED,
	FOREIGN KEY (id_repuesto) REFERENCES repuestos (id)
)

CREATE TABLE usuario_vehiculo (
	id_usuario INT UNSIGNED,
	FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
	id_vehiculo INT UNSIGNED,
	FOREIGN KEY (id_vehiculo) REFERENCES vehiculos (id)
)