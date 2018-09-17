-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.31-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
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

-- Volcando estructura para tabla sicor.banco
DROP TABLE IF EXISTS `banco`;
CREATE TABLE IF NOT EXISTS `banco` (
  `idBanco` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) NOT NULL,
  `nombBanco` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `numCuenta` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `formatoCheque` blob,
  PRIMARY KEY (`idBanco`),
  KEY `FK_id_Empresa_Banco` (`idEmpresa`),
  CONSTRAINT `FK_id_Empresa_Banco` FOREIGN KEY (`idEmpresa`) REFERENCES `empresas` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.banco: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.cheque
DROP TABLE IF EXISTS `cheque`;
CREATE TABLE IF NOT EXISTS `cheque` (
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
  CONSTRAINT `FK_id_BANCO` FOREIGN KEY (`idBanco`) REFERENCES `banco` (`idBanco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Cuenta_ABono1` FOREIGN KEY (`idCuentaAbono`) REFERENCES `cuentas` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_EMPRESA1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresas` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_PROVEEDOR21` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Partida32` FOREIGN KEY (`idPartida`) REFERENCES `partidas` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_cuenta_Cargo_1` FOREIGN KEY (`idCuentaCargo`) REFERENCES `cuentas` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.cheque: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cheque` DISABLE KEYS */;
/*!40000 ALTER TABLE `cheque` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.configuraciones
DROP TABLE IF EXISTS `configuraciones`;
CREATE TABLE IF NOT EXISTS `configuraciones` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `nombConfiguracion` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `formatoCuentas` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idConfiguracion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.configuraciones: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `configuraciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuraciones` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.cuentas
DROP TABLE IF EXISTS `cuentas`;
CREATE TABLE IF NOT EXISTS `cuentas` (
  `idCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `codCuenta` varchar(150) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `nombCuenta` int(11) DEFAULT NULL,
  `cargoDirecto` tinyint(1) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idTipoCuenta` int(11) unsigned zerofill DEFAULT NULL,
  `cargo` decimal(8,2) unsigned DEFAULT NULL,
  `abono` decimal(8,2) unsigned DEFAULT NULL,
  `idCuentaPadre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCuenta`),
  KEY `FK_empresa_asociada` (`idEmpresa`),
  KEY `FK_id_Cuenta_Padre1` (`idCuentaPadre`),
  CONSTRAINT `FK_empresa_asociada` FOREIGN KEY (`idEmpresa`) REFERENCES `empresas` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Cuenta_Padre1` FOREIGN KEY (`idCuentaPadre`) REFERENCES `cuentas` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.cuentas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.detallepartida
DROP TABLE IF EXISTS `detallepartida`;
CREATE TABLE IF NOT EXISTS `detallepartida` (
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
  CONSTRAINT `FK_id_Partida` FOREIGN KEY (`idPartida`) REFERENCES `partidas` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_cuenta` FOREIGN KEY (`idCuenta`) REFERENCES `cuentas` (`idCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.detallepartida: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detallepartida` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepartida` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.diario
DROP TABLE IF EXISTS `diario`;
CREATE TABLE IF NOT EXISTS `diario` (
  `idDiario` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `año` int(2) DEFAULT NULL,
  `mes` int(2) DEFAULT NULL,
  `cargos` decimal(6,2) DEFAULT NULL,
  `abonos` decimal(6,2) DEFAULT NULL,
  `movimientos` int(2) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idDiario`),
  KEY `FK_Empresa_Asociacion` (`idEmpresa`),
  CONSTRAINT `FK_Empresa_Asociacion` FOREIGN KEY (`idEmpresa`) REFERENCES `empresas` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.diario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `diario` DISABLE KEYS */;
/*!40000 ALTER TABLE `diario` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.empresas
DROP TABLE IF EXISTS `empresas`;
CREATE TABLE IF NOT EXISTS `empresas` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `codEmpresa` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `razonSocial` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `repre` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tel` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `conciliacion` tinyint(1) DEFAULT NULL,
  `recuDatos` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.empresas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.partidas
DROP TABLE IF EXISTS `partidas`;
CREATE TABLE IF NOT EXISTS `partidas` (
  `idPartida` int(11) NOT NULL AUTO_INCREMENT,
  `idDiario` int(11) DEFAULT NULL,
  `idTipoPartida` int(11) DEFAULT NULL,
  `numPartida` int(11) DEFAULT NULL,
  `fechPartida` date DEFAULT NULL,
  `concepto` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `cargos` decimal(8,2) DEFAULT NULL,
  `abonos` decimal(8,2) DEFAULT NULL,
  `correcta` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPartida`),
  KEY `FK_id_Diario` (`idDiario`),
  KEY `FK_id_Tipo_Partida` (`idTipoPartida`),
  CONSTRAINT `FK_id_Diario` FOREIGN KEY (`idDiario`) REFERENCES `diario` (`idDiario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_id_Tipo_Partida` FOREIGN KEY (`idTipoPartida`) REFERENCES `tipopartida` (`idTipoPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.partidas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.proveedores
DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE IF NOT EXISTS `proveedores` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `nombProveedor` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `regIVA` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tel` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `doc` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  KEY `FK_id_Empresa` (`idEmpresa`),
  CONSTRAINT `FK_id_Empresa` FOREIGN KEY (`idEmpresa`) REFERENCES `empresas` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.proveedores: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.tipocuenta
DROP TABLE IF EXISTS `tipocuenta`;
CREATE TABLE IF NOT EXISTS `tipocuenta` (
  `idTipoCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `nombTipoCuenta` varchar(3) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.tipocuenta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tipocuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipocuenta` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.tipopartida
DROP TABLE IF EXISTS `tipopartida`;
CREATE TABLE IF NOT EXISTS `tipopartida` (
  `idTipoPartida` int(11) NOT NULL AUTO_INCREMENT,
  `nombTipoPartida` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.tipopartida: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tipopartida` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipopartida` ENABLE KEYS */;

-- Volcando estructura para tabla sicor.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombUsuario` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `userUsuario` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `pswUsuario` varchar(32) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tipUsuario` int(11) DEFAULT NULL,
  `estaUsuario` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Volcando datos para la tabla sicor.usuarios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
REPLACE INTO `usuarios` (`idUsuario`, `nombUsuario`, `userUsuario`, `pswUsuario`, `tipUsuario`, `estaUsuario`) VALUES
	(1, 'OScar garesst', 'garesst', '23192921', 1, 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
