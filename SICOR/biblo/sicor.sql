-- --------------------------------------------------------
-- Host:                         10.10.1.208
-- Versión del servidor:         5.6.40 - MySQL Community Server (GPL)
-- SO del servidor:              Linux
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sicor
DROP DATABASE IF EXISTS `sicor`;
CREATE DATABASE IF NOT EXISTS `sicor` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `sicor`;

-- Volcando estructura para tabla sicor.BANCO
DROP TABLE IF EXISTS `BANCO`;
CREATE TABLE IF NOT EXISTS `BANCO` (
  `idBanco` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) NOT NULL,
  `nombBanco` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `numCuenta` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `formatoCheque` blob,
  PRIMARY KEY (`idBanco`),
  KEY `FK_id_Empresa_Banco` (`idEmpresa`),
  CONSTRAINT `FK_id_Empresa_Banco` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESAS` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.BANCO: ~0 rows (aproximadamente)
DELETE FROM `BANCO`;
/*!40000 ALTER TABLE `BANCO` DISABLE KEYS */;
/*!40000 ALTER TABLE `BANCO` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.CHEQUE
DROP TABLE IF EXISTS `CHEQUE`;
CREATE TABLE IF NOT EXISTS `CHEQUE` (
  `idCheque` int(11) NOT NULL AUTO_INCREMENT,
  `idBanco` int(11) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idCuentaAbono` int(11) DEFAULT NULL,
  `idCuentaCargo` int(11) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  `idPartida` int(11) DEFAULT NULL,
  `serieCheque` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `numCheque` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `lugarEmision` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fechEmision` date DEFAULT NULL,
  `montoDinero` decimal(8,2) DEFAULT NULL,
  `conceptoGeneral` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `anulado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idCheque`),
  KEY `FK_id_BANCO` (`idBanco`),
  KEY `FK_id_EMPRESA1` (`idEmpresa`),
  KEY `FK_id_PROVEEDOR21` (`idProveedor`),
  KEY `FK_id_Partida32` (`idPartida`),
  KEY `FK_id_Cuenta_ABono1` (`idCuentaAbono`),
  KEY `FK_id_cuenta_Cargo_1` (`idCuentaCargo`),
  CONSTRAINT `FK_id_BANCO` FOREIGN KEY (`idBanco`) REFERENCES `BANCO` (`idBanco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Cuenta_ABono1` FOREIGN KEY (`idCuentaAbono`) REFERENCES `CUENTAS` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_EMPRESA1` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESAS` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_PROVEEDOR21` FOREIGN KEY (`idProveedor`) REFERENCES `PROVEEDORES` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Partida32` FOREIGN KEY (`idPartida`) REFERENCES `PARTIDAS` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_cuenta_Cargo_1` FOREIGN KEY (`idCuentaCargo`) REFERENCES `CUENTAS` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.CHEQUE: ~0 rows (aproximadamente)
DELETE FROM `CHEQUE`;
/*!40000 ALTER TABLE `CHEQUE` DISABLE KEYS */;
/*!40000 ALTER TABLE `CHEQUE` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.CONFIGURACIONES
DROP TABLE IF EXISTS `CONFIGURACIONES`;
CREATE TABLE IF NOT EXISTS `CONFIGURACIONES` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `nombConfiguracion` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `formatoCuentas` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idConfiguracion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.CONFIGURACIONES: ~0 rows (aproximadamente)
DELETE FROM `CONFIGURACIONES`;
/*!40000 ALTER TABLE `CONFIGURACIONES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CONFIGURACIONES` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.CUENTAS
DROP TABLE IF EXISTS `CUENTAS`;
CREATE TABLE IF NOT EXISTS `CUENTAS` (
  `idCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `codCuenta` varchar(150) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `nombCuenta` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `cargoDirecto` tinyint(1) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idTipoCuenta` int(11) unsigned zerofill DEFAULT NULL,
  `cargo` decimal(8,2) unsigned DEFAULT NULL,
  `abono` decimal(8,2) unsigned DEFAULT NULL,
  `idCuentaPadre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCuenta`),
  KEY `FK_empresa_asociada` (`idEmpresa`),
  KEY `FK_id_Cuenta_Padre1` (`idCuentaPadre`),
  CONSTRAINT `FK_empresa_asociada` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESAS` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Cuenta_Padre1` FOREIGN KEY (`idCuentaPadre`) REFERENCES `CUENTAS` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.CUENTAS: ~4 rows (aproximadamente)
DELETE FROM `CUENTAS`;
/*!40000 ALTER TABLE `CUENTAS` DISABLE KEYS */;
INSERT INTO `CUENTAS` (`idCuenta`, `codCuenta`, `nombCuenta`, `cargoDirecto`, `idEmpresa`, `idTipoCuenta`, `cargo`, `abono`, `idCuentaPadre`) VALUES
	(1, '1', 'adada', 0, 1, NULL, 0.00, 0.00, NULL),
	(2, '2', 'Pasivo', 0, 1, NULL, 0.00, 0.00, NULL),
	(3, '11', 'Caja', 0, 1, NULL, 0.00, 0.00, 1),
	(4, '1101', 'Caja Chica', 0, 1, NULL, 0.00, 0.00, 3);
/*!40000 ALTER TABLE `CUENTAS` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.DETALLEPARTIDA
DROP TABLE IF EXISTS `DETALLEPARTIDA`;
CREATE TABLE IF NOT EXISTS `DETALLEPARTIDA` (
  `idDetallePartida` int(11) NOT NULL AUTO_INCREMENT,
  `idPartida` int(11) DEFAULT NULL,
  `idCuenta` int(11) DEFAULT NULL,
  `Concepto` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `abono` decimal(8,2) DEFAULT NULL,
  `cargo` decimal(8,2) DEFAULT NULL,
  `correcto` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idDetallePartida`),
  KEY `FK_id_Partida` (`idPartida`),
  KEY `FK_id_cuenta` (`idCuenta`),
  CONSTRAINT `FK_id_Partida` FOREIGN KEY (`idPartida`) REFERENCES `PARTIDAS` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_cuenta` FOREIGN KEY (`idCuenta`) REFERENCES `CUENTAS` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.DETALLEPARTIDA: ~0 rows (aproximadamente)
DELETE FROM `DETALLEPARTIDA`;
/*!40000 ALTER TABLE `DETALLEPARTIDA` DISABLE KEYS */;
/*!40000 ALTER TABLE `DETALLEPARTIDA` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.DIARIO
DROP TABLE IF EXISTS `DIARIO`;
CREATE TABLE IF NOT EXISTS `DIARIO` (
  `idDiario` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `nombDiario` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `año` int(2) DEFAULT NULL,
  `mes` int(2) DEFAULT NULL,
  `cargos` decimal(6,2) DEFAULT NULL,
  `abonos` decimal(6,2) DEFAULT NULL,
  `movimientos` int(2) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idDiario`),
  KEY `FK_Empresa_Asociacion` (`idEmpresa`),
  CONSTRAINT `FK_Empresa_Asociacion` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESAS` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.DIARIO: ~1 rows (aproximadamente)
DELETE FROM `DIARIO`;
/*!40000 ALTER TABLE `DIARIO` DISABLE KEYS */;
INSERT INTO `DIARIO` (`idDiario`, `idEmpresa`, `nombDiario`, `año`, `mes`, `cargos`, `abonos`, `movimientos`, `activo`) VALUES
	(1, 1, 'dasda', 2018, 9, 0.00, 0.00, 0, 1);
/*!40000 ALTER TABLE `DIARIO` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.EMPRESAS
DROP TABLE IF EXISTS `EMPRESAS`;
CREATE TABLE IF NOT EXISTS `EMPRESAS` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `codEmpresa` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `razonSocial` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `repre` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tel` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `conciliacion` tinyint(1) DEFAULT NULL,
  `recuDatos` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.EMPRESAS: ~1 rows (aproximadamente)
DELETE FROM `EMPRESAS`;
/*!40000 ALTER TABLE `EMPRESAS` DISABLE KEYS */;
INSERT INTO `EMPRESAS` (`idEmpresa`, `codEmpresa`, `razonSocial`, `repre`, `tel`, `conciliacion`, `recuDatos`) VALUES
	(1, '01', 'Oscar Pruebas', 'Oscar Garesst', '2526-30-21', NULL, NULL);
/*!40000 ALTER TABLE `EMPRESAS` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.PARTIDAS
DROP TABLE IF EXISTS `PARTIDAS`;
CREATE TABLE IF NOT EXISTS `PARTIDAS` (
  `idPartida` int(11) NOT NULL AUTO_INCREMENT,
  `idDiario` int(11) DEFAULT NULL,
  `idTipoPartida` int(11) DEFAULT NULL,
  `numPartida` int(11) DEFAULT NULL,
  `fechPartida` date DEFAULT NULL,
  `concepto` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `cargos` decimal(8,2) DEFAULT NULL,
  `abonos` decimal(8,2) DEFAULT NULL,
  `correcta` tinyint(1) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `año` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPartida`),
  KEY `FK_id_Diario` (`idDiario`),
  KEY `FK_id_Tipo_Partida` (`idTipoPartida`),
  CONSTRAINT `FK_id_Diario` FOREIGN KEY (`idDiario`) REFERENCES `DIARIO` (`idDiario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Tipo_Partida` FOREIGN KEY (`idTipoPartida`) REFERENCES `TIPOPARTIDA` (`idTipoPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.PARTIDAS: ~0 rows (aproximadamente)
DELETE FROM `PARTIDAS`;
/*!40000 ALTER TABLE `PARTIDAS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PARTIDAS` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.PROVEEDORES
DROP TABLE IF EXISTS `PROVEEDORES`;
CREATE TABLE IF NOT EXISTS `PROVEEDORES` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `nombProveedor` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `regIVA` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tel` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `doc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  KEY `FK_id_Empresa` (`idEmpresa`),
  CONSTRAINT `FK_id_Empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `EMPRESAS` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.PROVEEDORES: ~0 rows (aproximadamente)
DELETE FROM `PROVEEDORES`;
/*!40000 ALTER TABLE `PROVEEDORES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROVEEDORES` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.TIPOCUENTA
DROP TABLE IF EXISTS `TIPOCUENTA`;
CREATE TABLE IF NOT EXISTS `TIPOCUENTA` (
  `idTipoCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `nombTipoCuenta` varchar(3) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.TIPOCUENTA: ~0 rows (aproximadamente)
DELETE FROM `TIPOCUENTA`;
/*!40000 ALTER TABLE `TIPOCUENTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `TIPOCUENTA` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.TIPOPARTIDA
DROP TABLE IF EXISTS `TIPOPARTIDA`;
CREATE TABLE IF NOT EXISTS `TIPOPARTIDA` (
  `idTipoPartida` int(11) NOT NULL AUTO_INCREMENT,
  `nombTipoPartida` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.TIPOPARTIDA: ~0 rows (aproximadamente)
DELETE FROM `TIPOPARTIDA`;
/*!40000 ALTER TABLE `TIPOPARTIDA` DISABLE KEYS */;
/*!40000 ALTER TABLE `TIPOPARTIDA` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.USUARIOS
DROP TABLE IF EXISTS `USUARIOS`;
CREATE TABLE IF NOT EXISTS `USUARIOS` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombUsuario` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `userUsuario` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `pswUsuario` varchar(32) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tipUsuario` int(11) DEFAULT NULL,
  `estaUsuario` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.USUARIOS: ~0 rows (aproximadamente)
DELETE FROM `USUARIOS`;
/*!40000 ALTER TABLE `USUARIOS` DISABLE KEYS */;
INSERT INTO `USUARIOS` (`idUsuario`, `nombUsuario`, `userUsuario`, `pswUsuario`, `tipUsuario`, `estaUsuario`) VALUES
	(1, 'OScar garesst', 'garesst', '23192921', 1, 1);
/*!40000 ALTER TABLE `USUARIOS` ENABLE KEYS */;

-- Volcando estructura para vista sicor.PARTIDASDIARIOS
DROP VIEW IF EXISTS `PARTIDASDIARIOS`;
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `PARTIDASDIARIOS` (
	`idPartida` INT(11) NOT NULL,
	`idDiario` INT(11) NULL,
	`idTipoPartida` INT(11) NULL,
	`numPartida` INT(11) NULL,
	`fechPartida` DATE NULL,
	`concepto` VARCHAR(250) NULL COLLATE 'utf8_spanish2_ci',
	`cargos` DECIMAL(8,2) NULL,
	`abonos` DECIMAL(8,2) NULL,
	`correcta` TINYINT(1) NULL,
	`mes` INT(11) NULL,
	`año` INT(11) NULL
) ENGINE=MyISAM;

-- Volcando estructura para vista sicor.PARTIDASDIARIOS
DROP VIEW IF EXISTS `PARTIDASDIARIOS`;
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `PARTIDASDIARIOS`;
CREATE ALGORITHM=UNDEFINED DEFINER=`garesst`@`10.10.0.19` SQL SECURITY DEFINER VIEW `PARTIDASDIARIOS` AS select `p`.`idPartida` AS `idPartida`,`p`.`idDiario` AS `idDiario`,`p`.`idTipoPartida` AS `idTipoPartida`,`p`.`numPartida` AS `numPartida`,`p`.`fechPartida` AS `fechPartida`,`p`.`concepto` AS `concepto`,`p`.`cargos` AS `cargos`,`p`.`abonos` AS `abonos`,`p`.`correcta` AS `correcta`,`p`.`mes` AS `mes`,`p`.`año` AS `año` from (`DIARIO` `d` join `PARTIDAS` `p`) where (`d`.`idDiario` = `p`.`idDiario`);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
