/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: taller
-- ------------------------------------------------------
-- Server version	11.4.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `apellidos` varchar(20) DEFAULT NULL,
  `dni` varchar(9) NOT NULL,
  `fecha_ultima_visita` date NOT NULL,
  `tipo` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES
(1,'Mateo','García López','12345678A','2023-05-12',1),
(2,'Valeria','Martínez Ruiz','87654321B','2024-01-20',0),
(3,'Lucas','Sánchez Pérez','11223344C','2023-11-15',1),
(4,'Lucía','Gómez Fernández','55667788D','2024-02-10',0),
(5,'Enzo','Díaz Moreno','99001122E','2023-08-05',1),
(6,'Sofía','Álvarez Muñoz','33445566F','2023-12-25',0),
(7,'Hugo','Romero Alonso','77889900G','2024-03-01',1),
(8,'Martina','Gutiérrez Navarro','22334455H','2023-09-18',0),
(9,'Leo','Torres Doménech','66778899I','2024-01-14',1),
(10,'Sara','Vázquez Ramos','10203040J','2023-07-22',0),
(11,'Daniel','Gil Gil','50607080K','2023-10-30',1),
(12,'Carla','Serrano Blanco','90807060L','2024-02-28',0),
(13,'Alejandro','Molina Morales','13572468M','2023-06-14',1),
(14,'Alba','Suárez Ortega','24681357N','2023-12-01',0),
(15,'Pablo','Delgado Castro','98712345O','2024-01-05',1),
(16,'Julia','Ortiz Rubio','12398745P','2023-11-20',0),
(17,'Álvaro','Marín Sanz','45612378Q','2023-08-30',1),
(18,'Noa','Medina Ferrer','78945612R','2024-02-15',0),
(19,'Adrián','Cortes Castillo','32165498S','2023-05-25',1),
(20,'Lola','Santos Cortés','65498732T','2023-10-12',0),
(21,'Diego','Lozano Garrido','15926348U','2024-03-05',1),
(22,'Vega','Guerrero Cano','75315948V','2023-07-10',0),
(23,'Mario','Prieto Méndez','95135748W','2023-12-12',1),
(24,'Mia','Cruz Calvo','85296314X','2024-01-25',0),
(25,'Marcos','Gallego Vidal','74185296Y','2023-09-05',1),
(26,'Emma','León Herrera','36925814Z','2024-02-20',0),
(27,'Bruno','Márquez Peña','14725836A','2023-06-20',1),
(28,'Clara','Flores Cabrera','25836914B','2023-11-08',0),
(29,'Izan','Campos Esteban','96385274C','2024-01-18',1),
(30,'Aitana','Vega Bravo','85274196D','2023-08-14',0),
(31,'Thiago','Fuentes Soler','74196385E','2023-12-30',1),
(32,'Elena','Reyes Pardo','12345098F','2024-02-05',0),
(33,'Gael','Pascual Mora','98765012G','2023-10-22',1),
(34,'Chloe','Vila Rojo','45678023H','2023-07-15',0),
(35,'Ian','Jiménez Luque','32109845I','2024-03-10',1),
(36,'Ainhoa','Hidalgo Cruz','65432107J','2023-09-30',0),
(37,'Isaac','Paredes Nieto','15948726K','2024-01-02',1),
(38,'Lara','Sáez Expósito','75395146L','2023-11-12',0),
(39,'Biel','Pastor Luna','36914725M','2023-06-05',1),
(40,'Zoe','Vicente Abad','14736925N','2024-02-22',0);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repuestos`
--

DROP TABLE IF EXISTS `repuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `repuestos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `recibida` tinyint(1) DEFAULT 0,
  `precio` float DEFAULT 0,
  `fecha_pedido` date NOT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `referencia` varchar(50) NOT NULL,
  `garantia_meses` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `referencia` (`referencia`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repuestos`
--

LOCK TABLES `repuestos` WRITE;
/*!40000 ALTER TABLE `repuestos` DISABLE KEYS */;
INSERT INTO `repuestos` VALUES
(1,1,45.5,'2024-01-10','Filtro Aceite Premium','REF-001-ABC',12),
(2,0,120,'2024-02-15','Pastillas Freno Delanteras','REF-002-XYZ',24),
(3,1,89.99,'2024-01-20','Batería 70Ah HighPower','REF-003-LMN',36),
(4,0,350.4,'2024-03-01','Alternador Bosch 12V','REF-004-JKL',24),
(5,1,15.2,'2024-01-05','Bujía Iridium','REF-005-IOP',6),
(6,1,210,'2024-02-10','Kit Distribución','REF-006-TYU',48),
(7,0,65,'2024-03-05','Amortiguador Trasero','REF-007-VBN',12),
(8,1,32.15,'2024-01-25','Filtro Aire Deportivo','REF-008-QWE',12),
(9,0,540,'2024-02-28','Compresor Aire Acond.','REF-009-ASD',24),
(10,1,12.5,'2024-02-02','Lámpara H7 LED','REF-010-ZXC',12),
(11,1,95,'2024-01-15','Disco Freno Ventilado','REF-011-GHJ',24),
(12,0,180.5,'2024-03-10','Bomba de Agua','REF-012-KLS',12),
(13,1,44,'2024-01-12','Termostato Motor','REF-013-MNB',12),
(14,0,25.99,'2024-03-12','Escobilla Limpiaparabrisas','REF-014-POI',6),
(15,1,110,'2024-02-05','Embrague Kit Completo','REF-015-UYT',36),
(16,1,55.3,'2024-01-30','Radiador Calefacción','REF-016-REW',24),
(17,0,78.4,'2024-03-14','Cojinete Rueda Delantera','REF-017-FDG',12),
(18,1,215,'2024-02-18','Inyector Diesel Eco','REF-018-HJK',24),
(19,1,40,'2024-01-22','Manguito Turbo','REF-019-CVB',12),
(20,0,155,'2024-03-15','Caudalímetro Aire','REF-020-XSD',12),
(21,1,8.5,'2024-02-10','Filtro de Habitáculo','REF-021-WER',6),
(22,0,310,'2024-03-16','Turbo Intercooler','REF-022-DFG',24),
(23,1,42.1,'2024-02-20','Sonda Lambda','REF-023-CVX',24),
(24,1,130,'2024-01-18','Motor de Arranque','REF-024-BNM',24),
(25,0,60,'2024-03-18','Polea Cigüeñal','REF-025-TYP',12),
(26,1,22,'2024-02-22','Silentblock Brazo','REF-026-GHU',12),
(27,1,190,'2024-01-28','Centralita Confort','REF-027-IOK',48),
(28,0,48.2,'2024-03-20','Cable de Freno Mano','REF-028-UJH',12),
(29,1,14.9,'2024-02-25','Tapón Depósito Aceite','REF-029-YTR',6),
(30,0,275,'2024-03-22','Catalizador Homologado','REF-030-WES',24),
(31,1,52,'2024-01-05','Válvula EGR','REF-031-AQW',24),
(32,1,33.5,'2024-02-26','Sensor de Aparcamiento','REF-032-SDE',12),
(33,0,85,'2024-03-24','Muelle Suspensión','REF-033-FRT',24),
(34,1,115.6,'2024-02-01','Cremallera Dirección','REF-034-GHY',36),
(35,1,28,'2024-02-28','Junta de Culata','REF-035-VFR',12),
(36,0,210.4,'2024-03-26','Faro Principal Xenón','REF-036-BGT',24),
(37,1,18,'2024-01-15','Correa Auxiliar Poly-V','REF-037-NHY',12),
(38,0,99,'2024-03-27','Bomba de Combustible','REF-038-MJU',24),
(39,1,64.2,'2024-02-14','Disco Embrague','REF-039-KIU',24),
(40,1,37.8,'2024-02-10','Sensor ABS Rueda','REF-040-LOI',12);
/*!40000 ALTER TABLE `repuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_repuestos`
--

DROP TABLE IF EXISTS `usuario_repuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_repuestos` (
  `id_usuario` int(10) unsigned DEFAULT NULL,
  `id_repuesto` int(10) unsigned DEFAULT NULL,
  KEY `id_usuario` (`id_usuario`),
  KEY `id_repuesto` (`id_repuesto`),
  CONSTRAINT `usuario_repuestos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `usuario_repuestos_ibfk_2` FOREIGN KEY (`id_repuesto`) REFERENCES `repuestos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_repuestos`
--

LOCK TABLES `usuario_repuestos` WRITE;
/*!40000 ALTER TABLE `usuario_repuestos` DISABLE KEYS */;
INSERT INTO `usuario_repuestos` VALUES
(1,12),
(1,5),
(1,28),
(1,3),
(1,40),
(2,15),
(2,22),
(3,30),
(3,1),
(3,18),
(3,39),
(4,7),
(4,11),
(4,25),
(5,33),
(5,2),
(5,19),
(5,37),
(5,8),
(6,4),
(7,21),
(7,10),
(7,35),
(7,29),
(8,6),
(8,14),
(8,27),
(9,38),
(9,9),
(9,20),
(9,13),
(9,31),
(10,16),
(10,23),
(11,24),
(11,32),
(11,17),
(12,26),
(12,34),
(12,36),
(12,5),
(12,12),
(13,11),
(14,28),
(14,1),
(14,22),
(14,30),
(15,7),
(15,40),
(15,3),
(16,19),
(16,15),
(16,8),
(16,33),
(16,2),
(17,37),
(17,21),
(18,10),
(18,35),
(19,6),
(19,27),
(19,14),
(19,18),
(20,39),
(20,4);
/*!40000 ALTER TABLE `usuario_repuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_vehiculo`
--

DROP TABLE IF EXISTS `usuario_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_vehiculo` (
  `id_usuario` int(10) unsigned DEFAULT NULL,
  `id_vehiculo` int(10) unsigned DEFAULT NULL,
  KEY `id_usuario` (`id_usuario`),
  KEY `id_vehiculo` (`id_vehiculo`),
  CONSTRAINT `usuario_vehiculo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `usuario_vehiculo_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_vehiculo`
--

LOCK TABLES `usuario_vehiculo` WRITE;
/*!40000 ALTER TABLE `usuario_vehiculo` DISABLE KEYS */;
INSERT INTO `usuario_vehiculo` VALUES
(7,12),
(7,45),
(7,3),
(7,28),
(1,15),
(1,50),
(1,8),
(14,33),
(14,2),
(14,19),
(14,41),
(14,10),
(3,25),
(3,7),
(19,30),
(19,1),
(19,48),
(6,22),
(12,11),
(12,34),
(12,5),
(12,49),
(12,27),
(12,16),
(20,39),
(20,4),
(2,21),
(2,9),
(2,44),
(15,37),
(15,20),
(15,13),
(5,24),
(5,42),
(5,6),
(5,31),
(18,17),
(18,35),
(9,26),
(9,43),
(9,38),
(9,23),
(11,29),
(4,32),
(4,46),
(4,14),
(4,36),
(17,47),
(17,18),
(17,40),
(8,22),
(8,3),
(13,1),
(13,50),
(13,25),
(10,8),
(10,15),
(16,12),
(16,45),
(16,28);
/*!40000 ALTER TABLE `usuario_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `contraseña` varchar(20) NOT NULL,
  `puesto` varchar(40) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES
(1,'Juan Pérez','Px92mQz1','Mecánico Senior','2024-01-15','Especialista en motores diesel'),
(2,'María García','vK7s2P9w','Mecánico Junior','2024-01-16','En periodo de formación'),
(3,'Carlos Ruiz','4fT8nLp0','Especialista','2024-01-18','Experto en diagnosis electrónica'),
(4,'Ana Belén','zX1vB5nM','Mecánico Senior','2024-01-20','Responsable de seguridad'),
(5,'Luis Miguel','9oP3kL1j','Mecánico Junior','2024-01-22','Ayudante de taller'),
(6,'Elena Cano','qW2eR4tY','Especialista','2024-01-25','Sistemas de frenado'),
(7,'Diego Mesa','aS4dF6gH','Mecánico Senior','2024-02-01','Especialista en transmisiones'),
(8,'Sofía Lara','zX7cV9bN','Mecánico Junior','2024-02-05','Revisiones de mantenimiento'),
(9,'Pedro Sanz','pL0kJ8hG','Especialista','2024-02-10','Vehículos híbridos'),
(10,'Lucía Rivas','mN1bV3cX','Mecánico Senior','2024-02-12','Mecánica de precisión'),
(11,'Javier Soler','qA1zW2sX','Mecánico Junior','2024-02-15','Apoyo en mecánica general'),
(12,'Marta Vidal','eD3rF4tG','Especialista','2024-02-18','Inyección y turbos'),
(13,'Roberto Gil','yH6uJ7kI','Mecánico Senior','2024-02-20','Especialista en culatas'),
(14,'Isabel Luna','oP9lK8mJ','Mecánico Junior','2024-02-22','Cambios de aceite y filtros'),
(15,'Hugo Ferre','nB5vC4xZ','Especialista','2024-02-25','Aire acondicionado'),
(16,'Sara Tello','mK2jH3gF','Mecánico Senior','2024-02-28','Suspensión y dirección'),
(17,'Raúl Blanco','pO0iU9yT','Mecánico Junior','2024-03-01','Reparación de neumáticos'),
(18,'Paula Ortiz','lK8jH7gF','Especialista','2024-03-03','Sistemas eléctricos'),
(19,'Marcos Rey','dS4fG5hJ','Mecánico Senior','2024-03-05','Revisión pre-ITV'),
(20,'Julia Mora','aZ1sX2cD','Mecánico Junior','2024-03-07','Mantenimiento preventivo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `modelo` varchar(10) DEFAULT NULL,
  `matricula` varchar(20) NOT NULL,
  `telefono_dueño` varchar(30) DEFAULT NULL,
  `fecha_llegada` date NOT NULL,
  `averia` varchar(500) DEFAULT NULL,
  `id_cliente` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricula` (`matricula`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `vehiculos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES
(1,'Renault C.','7412-KLP','622333444','2024-01-05','Aceite',14),
(2,'Fiat 500','9632-BTR','666777888','2024-01-07','Luces',2),
(3,'Seat Ibiza','1598-MCZ','600111222','2024-01-10','Revisión',29),
(4,'Audi A3','3574-PDX','633444555','2024-01-12','Motor',4),
(5,'BMW S1','8520-QRT','644555666','2024-01-15','Ruedas',33),
(6,'Opel Corsa','7531-VBN','655666777','2024-01-18','Batería',14),
(7,'Golf GTI','9510-MSX','677888999','2024-01-20','Embrague',8),
(8,'Peugeot208','4562-LZP','688999000','2024-01-22','Correa',40),
(9,'Citroen C3','1230-WQR','699000111','2024-01-25','Frenos',22),
(10,'Toyota Y.','7895-KMN','600123123','2024-01-28','Pastillas',4),
(11,'Ford Focus','6541-JLB','611234234','2024-02-01','ITV',14),
(12,'Nissan Q.','3218-PHG','622345345','2024-02-03','Filtro',31),
(13,'Kia Rio','1479-VCS','633456456','2024-02-05','Alineación',7),
(14,'Hyundai i2','2580-BTK','644567567','2024-02-08','Bomba agua',19),
(15,'Mazda 3','3694-MLP','655678678','2024-02-10','Turbo',2),
(16,'Mini Coop.','7410-XCV','666789789','2024-02-12','Faro',35),
(17,'Dacia S.','8523-BNM','677890890','2024-02-15','Escape',8),
(18,'Skoda F.','9631-TYU','688901901','2024-02-18','Limpias',27),
(19,'Honda Civ.','1590-GHK','699012012','2024-02-20','Sensores',11),
(20,'Suzuki S.','3572-QWE','600999888','2024-02-22','Espejo',5),
(21,'Tesla M3','8526-ASD','611888777','2024-02-25','Software',24),
(22,'Ford Fies.','7539-ZXC','622777666','2024-02-27','Pinchazo',1),
(23,'Renault M.','9514-RTY','633666555','2024-03-01','Mantenim.',38),
(24,'Seat Leon','4560-FGH','644555444','2024-03-03','Agua',12),
(25,'Audi Q3','1238-JKL','655444333','2024-03-05','Caja',30),
(26,'BMW X1','7892-VBN','666333222','2024-03-08','Bujías',17),
(27,'Opel Astr.','6540-MQW','677222111','2024-03-10','Ventanilla',23),
(28,'Fiat Pan.','3215-ERP','688111000','2024-03-12','Inyectores',9),
(29,'Golf TDI','1472-SDF','699222333','2024-03-15','Discos',3),
(30,'Dacia Dus.','2584-GHJ','600333444','2024-03-18','Radiador',16),
(31,'Kia Ceed','3690-TYU','611444555','2024-03-20','Puerta',25),
(32,'Mazda CX5','7415-BNM','622555666','2024-03-22','Cerradura',32),
(33,'Skoda Oct.','8529-ASD','633666777','2024-03-25','Niveles',6),
(34,'Toyota Cor','9630-ZXC','644777888','2024-03-28','Híbrido',21),
(35,'Nissan Mic','1594-RTY','655888999','2024-03-30','General',37),
(36,'Jeep Ren.','3570-FGH','666999000','2024-04-01','Frenos',13),
(37,'Lexus UX','8522-JKL','677000111','2024-04-03','Aceite',26),
(38,'Volvo XC4','7534-VBN','688222111','2024-04-05','Revisión',10),
(39,'Smart FW','9512-MQW','699333222','2024-04-07','Batería',34),
(40,'Cupra For.','4569-ERP','611222333','2024-04-09','Luz',20),
(41,'Ford Focus','1235-SDF','622333444','2024-04-11','Rueda',18),
(42,'Audi A3','7891-GHJ','633444555','2024-04-13','Espejo',39),
(43,'BMW S1','6542-TYU','644555666','2024-04-15','Antena',15),
(44,'Golf GTI','3210-BNM','677888999','2024-04-17','Filtros',28),
(45,'Seat Ibiza','1475-ASD','600111222','2024-04-19','Limpia',36),
(46,'Renault C.','2581-ZXC','622333444','2024-04-21','Pintura',8),
(47,'Fiat 500','3692-RTY','666777888','2024-04-23','Mantenim.',2),
(48,'Audi A3','7419-FGH','633444555','2024-04-25','Sensor',4),
(49,'Peugeot208','8521-JKL','688999000','2024-04-27','Parachoques',14),
(50,'Golf GTI','9635-VBN','677888999','2024-04-29','Caja',8);
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'taller'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-02-07 13:09:16
