-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.11.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para desarrollo
CREATE DATABASE IF NOT EXISTS `desarrollo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `desarrollo`;

-- Volcando estructura para tabla desarrollo.administradores
CREATE TABLE IF NOT EXISTS `administradores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Los admins';

-- Volcando datos para la tabla desarrollo.administradores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` (`id`, `user`, `password`) VALUES
	(1, 'RaulAdmin', 'Xenoblade05'),
	(2, 'BrissAdmin', 'Xenoblade05'),
	(3, 'KevinAdmin', 'Xenoblade05');
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;

-- Volcando estructura para tabla desarrollo.animales
CREATE TABLE IF NOT EXISTS `animales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `comida` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Los animales del zoologico';

-- Volcando datos para la tabla desarrollo.animales: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `animales` DISABLE KEYS */;
INSERT INTO `animales` (`id`, `nombre`, `comida`) VALUES
	(1, 'Hippo', 'Plantas'),
	(2, 'León', 'Carne'),
	(3, 'Mono', 'Platano'),
	(4, 'Zebra', 'Planta'),
	(5, 'Cabra', 'lechuga');
/*!40000 ALTER TABLE `animales` ENABLE KEYS */;

-- Volcando estructura para tabla desarrollo.cuidadores
CREATE TABLE IF NOT EXISTS `cuidadores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Los que cuidan a los animales';

-- Volcando datos para la tabla desarrollo.cuidadores: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `cuidadores` DISABLE KEYS */;
INSERT INTO `cuidadores` (`id`, `user`, `password`) VALUES
	(1, 'SteveCuid', 'Xenoblade05'),
	(2, 'MarthaCuid', 'Xenoblade05'),
	(3, 'RamonCuid', 'Xenoblade05'),
	(4, 'RoggieCuid', 'Xenoblade05'),
	(5, 'HelpbertoCuid', 'Xenoblade05'),
	(6, 'AngelCuid', 'Xenoblade05'),
	(7, 'DavidCuid', 'Xenoblade05'),
	(8, 'RoberCuid', 'Xenoblade05'),
	(9, 'DavidCuid', 'Xenoblade05');
/*!40000 ALTER TABLE `cuidadores` ENABLE KEYS */;

-- Volcando estructura para tabla desarrollo.visitantes
CREATE TABLE IF NOT EXISTS `visitantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Los que visitan el parque para ver a los animales. Ya están registrados dentro del sistema del parque. ';

-- Volcando datos para la tabla desarrollo.visitantes: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `visitantes` DISABLE KEYS */;
INSERT INTO `visitantes` (`id`, `user`, `password`) VALUES
	(1, 'KarenV', 'Xenoblade05'),
	(2, 'JudeV', 'Xenoblade05'),
	(3, 'MikeV', 'Xenoblade05'),
	(4, 'ClayV', 'Xenoblade05'),
	(5, 'MirandaV', 'Xenoblade05'),
	(6, 'GinaV', 'Xenoblade05'),
	(7, 'ReaganV', 'Xenoblade05'),
	(8, 'ChecoV', 'Xenoblade05');
/*!40000 ALTER TABLE `visitantes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
