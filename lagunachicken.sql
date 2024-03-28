-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: localhost    Database: lagunachicken
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chofer`
--

DROP TABLE IF EXISTS `chofer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chofer` (
  `id_chofer` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  `fecha_nacimiento` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `direccion` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  `nss` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `vencimiento_licencia` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `tipo_sangre` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `foto` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_chofer`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chofer`
--

LOCK TABLES `chofer` WRITE;
/*!40000 ALTER TABLE `chofer` DISABLE KEYS */;
INSERT INTO `chofer` VALUES (4,'julio','5 de mayo','torreon','123456','6 de mayo','AB+',''),(5,'juan perez','01/04/1990','torreon','1234567','01/08/2026','AB-',''),(6,'Mario Gomez','1970-02-11T06:00:00.000Z','torreon','123456789','2024-02-22T06:00:00.000Z','AB+',''),(7,'Jose Perez','2024-02-15T06:00:00.000Z','torreon','123455','2024-02-28T06:00:00.000Z','AB-',''),(8,'alejandro','2024-02-14T06:00:00.000Z','torreon','123456','2024-02-14T06:00:00.000Z','O+',''),(9,'raul','2024-02-15T06:00:00.000Z','torreon','95416516','2024-02-21T06:00:00.000Z','AB-',''),(10,'pablo','2024-02-14T06:00:00.000Z','torreon','673657467','2024-02-21T06:00:00.000Z','O+',''),(11,'luis','2024-02-14T06:00:00.000Z','torreon','674658578','2024-02-21T06:00:00.000Z','O+',''),(12,'carlos','2024-02-07T06:00:00.000Z','torreon','3546734673567','2024-02-27T06:00:00.000Z','AB+',''),(13,'osvaldo','2024-02-14T06:00:00.000Z','torreon','564673657','2024-02-28T06:00:00.000Z','O-',''),(14,'prueba','2024-03-13T06:00:00.000Z','gomez','123456','2024-03-13T06:00:00.000Z','AB-','');
/*!40000 ALTER TABLE `chofer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacion`
--

DROP TABLE IF EXISTS `estacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacion` (
  `id_estacion` int NOT NULL AUTO_INCREMENT,
  `estacion` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_estacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacion`
--

LOCK TABLES `estacion` WRITE;
/*!40000 ALTER TABLE `estacion` DISABLE KEYS */;
INSERT INTO `estacion` VALUES (1,'BB Rebollo'),(2,'SHELL Magisteri'),(3,'BB Bravo'),(4,'SHELL Division'),(5,'BB Toro Viejo');
/*!40000 ALTER TABLE `estacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `producto` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Diesel Automotriz Sin Marca'),(2,'Shell Super');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Grease Monkey');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (5,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `id_servicio` int NOT NULL AUTO_INCREMENT,
  `servicio` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Cambio de llantas'),(2,'Cambio de aceite'),(3,'Servicio preventivo'),(4,'Balanceo');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursal` (
  `id_sucursal` int NOT NULL AUTO_INCREMENT,
  `sucursal` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'Torreón'),(2,'Gomez Palacio'),(3,'Durango');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_username` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `password` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'admin','admin@lagunachicken.com','$2a$10$8ecq0Vor9Ihy/AIrTSfr4.AR2YhB7yhRJOypUhjxjom9UpMiAHvT2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id_user_role` int NOT NULL AUTO_INCREMENT,
  `user_id_username` int NOT NULL,
  `role_id_role` int NOT NULL,
  PRIMARY KEY (`id_user_role`,`user_id_username`,`role_id_role`),
  KEY `fk_user_has_Role_Role1_idx` (`role_id_role`),
  KEY `fk_user_has_Role_user_idx` (`user_id_username`),
  CONSTRAINT `fk_user_has_Role_Role1` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `fk_user_has_Role_user` FOREIGN KEY (`user_id_username`) REFERENCES `user` (`id_username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (5,7,5);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `id_vehiculo` int NOT NULL AUTO_INCREMENT,
  `sucursal_id_sucursal` int NOT NULL,
  `num_economico` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `kilometraje` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `kilometraje_aviso` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `kilometraje_periodo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `placas` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `estado_placas` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `modelo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `capacidad` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `marca` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `tipo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `descripcion` varchar(500) COLLATE utf16_bin DEFAULT NULL,
  `numero_serie` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `numero_motor` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `numero_poliza` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `aseguradora` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `vencimiento_poliza` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `chofer_id_chofer` int NOT NULL,
  PRIMARY KEY (`id_vehiculo`),
  KEY `fk_vehiculo_sucursal1_idx` (`sucursal_id_sucursal`),
  KEY `fk_vehiculo_chofer1_idx` (`chofer_id_chofer`),
  CONSTRAINT `fk_vehiculo_chofer1` FOREIGN KEY (`chofer_id_chofer`) REFERENCES `chofer` (`id_chofer`),
  CONSTRAINT `fk_vehiculo_sucursal1` FOREIGN KEY (`sucursal_id_sucursal`) REFERENCES `sucursal` (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (5,2,'123456','1000',NULL,NULL,'123456','Durango','modelo','capacidad','marca','tipo','descripcion','123456','123456','','aseguradora','18/01/2024',5),(6,2,'123456789','100',NULL,NULL,'123456','Durango','modelo','capacidad','marca','tipo','descripcion','123456','123456','9481564','aseguradora','2024-02-29T15:40:10.984Z',6),(7,1,'45736573','0',NULL,NULL,'123456','Durango','modelo','capacidad','marca','tipo','descripcion','123456','123456','9481564','aseguradora','2024-02-29T17:29:57.744Z',4),(8,1,'45376472','0',NULL,NULL,'123456','Durango','modelo','capacidad','marca','tipo','descripcion','123456','123456','9481564','aseguradora','2024-02-29T17:30:22.132Z',8),(9,1,'874549875','0',NULL,NULL,'123456','Durango','modelo','capacidad','marca','tipo','descripcion','123456','','9481564','aseguradora','2024-02-29T17:30:51.912Z',9),(10,1,'GP1','200150','201','10000','G12JIU','DURANGO','2012','3.5','HINO','SEMI LONG','color blanco','123456789','123456789','283742863GG','AXXA','2024-03-13T17:07:03.758Z',6);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo_consumo`
--

DROP TABLE IF EXISTS `vehiculo_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo_consumo` (
  `id_vehiculo_consumo` int NOT NULL AUTO_INCREMENT,
  `vehiculo_id_vehiculo` int NOT NULL,
  `estacion_id_estacion` int NOT NULL,
  `producto_id_producto` int NOT NULL,
  `odometro` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `recorrido` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `rendimiento` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `cantidad` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `precio` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `monto` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `hora_consumo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `fecha_consumo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo_consumo`),
  KEY `fk_vehiculo_consumo_vehiculo1_idx` (`vehiculo_id_vehiculo`),
  KEY `fk_vehiculo_consumo_estacion1_idx` (`estacion_id_estacion`),
  KEY `fk_vehiculo_consumo_producto1_idx` (`producto_id_producto`),
  CONSTRAINT `fk_vehiculo_consumo_estacion1` FOREIGN KEY (`estacion_id_estacion`) REFERENCES `estacion` (`id_estacion`),
  CONSTRAINT `fk_vehiculo_consumo_producto1` FOREIGN KEY (`producto_id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `fk_vehiculo_consumo_vehiculo1` FOREIGN KEY (`vehiculo_id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo_consumo`
--

LOCK TABLES `vehiculo_consumo` WRITE;
/*!40000 ALTER TABLE `vehiculo_consumo` DISABLE KEYS */;
INSERT INTO `vehiculo_consumo` VALUES (1,9,1,1,'183602','440','3.99','50','25','1250',NULL,'2024-03-05T06:00:00.000Z'),(2,9,1,2,'183602','440','3.99','50','25','1250','11:00','2024-03-06T06:00:00.000Z'),(3,10,3,2,'184603','440','3.99','50','25',NULL,'08:00','2024-12-03T06:00:00.000Z'),(4,10,2,2,'183602','440','3.99','50','25',NULL,'09:00','2024-03-26T06:00:00.000Z'),(5,10,2,2,'199999','440','3.99','50','25',NULL,'09:00','2024-03-27T06:00:00.000Z'),(6,10,2,2,'200150','440','4.01','50','25','1250.0','09:00','2024-03-27T06:00:00.000Z');
/*!40000 ALTER TABLE `vehiculo_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo_servicio`
--

DROP TABLE IF EXISTS `vehiculo_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo_servicio` (
  `id_vehiculo_servicio` int NOT NULL AUTO_INCREMENT,
  `vehiculo_id_vehiculo` int NOT NULL,
  `servicio_id_servicio` int NOT NULL,
  `proveedor_id_proveedor` int NOT NULL,
  `kilometraje` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `folio_factura` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `costo` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `fecha_servicio` varchar(45) COLLATE utf16_bin DEFAULT NULL,
  `descripcion` varchar(500) COLLATE utf16_bin DEFAULT NULL,
  `file` varchar(150) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo_servicio`,`vehiculo_id_vehiculo`,`servicio_id_servicio`),
  KEY `fk_vehiculo_has_servicio_servicio1_idx` (`servicio_id_servicio`),
  KEY `fk_vehiculo_has_servicio_vehiculo1_idx` (`vehiculo_id_vehiculo`),
  KEY `fk_vehiculo_servicio_proveedor1_idx` (`proveedor_id_proveedor`),
  CONSTRAINT `fk_vehiculo_has_servicio_servicio1` FOREIGN KEY (`servicio_id_servicio`) REFERENCES `servicio` (`id_servicio`),
  CONSTRAINT `fk_vehiculo_has_servicio_vehiculo1` FOREIGN KEY (`vehiculo_id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`),
  CONSTRAINT `fk_vehiculo_servicio_proveedor1` FOREIGN KEY (`proveedor_id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo_servicio`
--

LOCK TABLES `vehiculo_servicio` WRITE;
/*!40000 ALTER TABLE `vehiculo_servicio` DISABLE KEYS */;
INSERT INTO `vehiculo_servicio` VALUES (3,5,2,1,'1000','123456','4500','2024-02-22T06:00:00.000Z',NULL,NULL),(4,5,2,1,'1000','55887541','4500','2024-02-22T06:00:00.000Z','descuento de 150',NULL),(5,5,4,1,'1000','55887541','4500','2024-02-24T06:00:00.000Z','descripcion',NULL),(6,6,1,1,'1000','55887541','4500','2024-02-21T06:00:00.000Z','',NULL),(7,7,2,1,'1000','55887541','4500','2024-03-12T06:00:00.000Z','descuento de 150',NULL),(8,6,4,1,'2500','55887541','4500','2024-03-05T06:00:00.000Z','descripcion',NULL),(9,8,4,1,'2500','55887541','4500','2024-03-20T06:00:00.000Z','descripcion',NULL),(12,8,2,1,'1000','55887541','4500','2024-03-09T06:00:00.000Z','descripcion',NULL),(13,8,2,1,'100','55887541','4500','2024-03-06T06:00:00.000Z','',NULL),(14,6,4,1,'8963','558875418','3205','2024-03-13T06:00:00.000Z','',NULL),(15,5,2,1,'85623','81896521','7521','2024-03-11T06:00:00.000Z','',NULL),(16,6,2,1,'8963','55887541','3205','2024-03-13T06:00:00.000Z','',NULL),(17,6,4,1,'1569','81896521','5500','2024-03-11T06:00:00.000Z','',NULL),(18,8,2,1,'8963','81896521','3205','2024-03-12T06:00:00.000Z','',NULL),(19,10,1,1,'20000','87698790','3500','2024-03-12T06:00:00.000Z','',NULL),(20,10,2,1,'8963','81896521','3205','2024-03-14T06:00:00.000Z','',NULL),(21,10,2,1,'8963','55887541','3205','2024-03-14T06:00:00.000Z','',NULL),(22,10,4,1,'8963','87698790','3205','2024-03-13T06:00:00.000Z','',NULL),(23,10,4,1,'8963','87698790','3205','2024-03-13T06:00:00.000Z','',NULL),(24,10,2,1,'1000','55887541','5500','2024-03-14T06:00:00.000Z','',''),(25,9,2,1,'85623','87698790','5896','2024-03-14T06:00:00.000Z','','1r1SV9mFe3ANjTdSF91MsNHL.pdf'),(26,7,4,1,'8963','87698790','3500','2024-03-13T06:00:00.000Z','','Lb4S7QmnnWRD9S3susfnMhlT.pdf');
/*!40000 ALTER TABLE `vehiculo_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'lagunachicken'
--
/*!50003 DROP PROCEDURE IF EXISTS `register_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `register_user`(
	IN `_username` VARCHAR(150),
    IN `_email` VARCHAR(150),
    IN `_password` VARCHAR(150),
    IN `_role_id_role` INT
)
BEGIN
	-- rollback transaction and bubble up errors if something bad happens
	DECLARE exit handler FOR SQLEXCEPTION, SQLWARNING
  	BEGIN
    	ROLLBACK;
    	RESIGNAL;
  	END;
	
    START TRANSACTION;
    BEGIN
    
    DECLARE next_user_id INT;
    
    INSERT INTO user(username, email, password) VALUES(_username, _email, _password);
    -- Obtener el último valor insertado en la columna store_id
    SELECT LAST_INSERT_ID() INTO next_user_id;
    
    INSERT INTO user_role(user_id_username, role_id_role) VALUES(next_user_id, _role_id_role);
    
    -- if no errors happened yet, commit transaction
	END;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-28 10:59:04
