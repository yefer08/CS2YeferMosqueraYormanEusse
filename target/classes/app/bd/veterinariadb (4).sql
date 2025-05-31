-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-05-2025 a las 00:37:00
-- Versión del servidor: 8.0.41
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinariadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoices`
--

DROP TABLE IF EXISTS `invoices`;
CREATE TABLE IF NOT EXISTS `invoices` (
  `idInvoice` varchar(36) NOT NULL,
  `pet_id` varchar(36) NOT NULL,
  `owner_id` bigint NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `value` float NOT NULL,
  `quantity` int NOT NULL,
  `date` datetime NOT NULL,
  `id_invoice` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  PRIMARY KEY (`idInvoice`),
  KEY `pet_id` (`pet_id`),
  KEY `owner_id` (`owner_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medical_history`
--

DROP TABLE IF EXISTS `medical_history`;
CREATE TABLE IF NOT EXISTS `medical_history` (
  `id` varchar(36) NOT NULL,
  `date` datetime NOT NULL,
  `veterinarian_id` bigint NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `medical_procedure` varchar(255) DEFAULT NULL,
  `medication` varchar(255) DEFAULT NULL,
  `medication_dose` varchar(255) DEFAULT NULL,
  `vaccination_history` varchar(255) DEFAULT NULL,
  `allergies` varchar(255) DEFAULT NULL,
  `procedure_details` varchar(255) DEFAULT NULL,
  `canceled` tinyint(1) DEFAULT NULL,
  `pet_id` varchar(36) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3ap0q6vbooojibvdr8us2y6vf` (`order_id`),
  KEY `veterinarian_id` (`veterinarian_id`),
  KEY `pet_id` (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `medical_history`
--

INSERT INTO `medical_history` (`id`, `date`, `veterinarian_id`, `reason`, `symptoms`, `diagnosis`, `medical_procedure`, `medication`, `medication_dose`, `vaccination_history`, `allergies`, `procedure_details`, `canceled`, `pet_id`, `order_id`) VALUES
('64ad43f2-5baa-433a-a4a3-8aaae6967a24', '2025-05-22 10:30:00', 123, 'Chequeo general', 'Ninguno', 'Mascota en buen estado', 'Revisión física', 'Ninguno', 'N/A', 'Vacunas al día', 'Sin alergias conocidas', 'Examen de rutina sin anomalías', 0, '525783e6-2e4c-4f93-896a-fe8c42600603', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medication_order_items`
--

DROP TABLE IF EXISTS `medication_order_items`;
CREATE TABLE IF NOT EXISTS `medication_order_items` (
  `id` varchar(255) NOT NULL,
  `dose` varchar(255) NOT NULL,
  `instructions` text,
  `medication_name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `order_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjty9xp469u3akr8kxaru7v704` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` varchar(36) NOT NULL,
  `date` datetime NOT NULL,
  `owner_id` bigint NOT NULL,
  `veterinarian_id` bigint NOT NULL,
  `pet_id` varchar(36) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `medical_history_id` varchar(36) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `owner_id` (`owner_id`),
  KEY `veterinarian_id` (`veterinarian_id`),
  KEY `pet_id` (`pet_id`),
  KEY `medical_history_id` (`medical_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`id`, `date`, `owner_id`, `veterinarian_id`, `pet_id`, `completed`, `medical_history_id`, `description`) VALUES
('f1013178-76cb-4ccf-87fe-c6e99a0af9e6', '2025-05-26 19:06:23', 3, 2, '525783e6-2e4c-4f93-896a-fe8c42600603', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pets`
--

DROP TABLE IF EXISTS `pets`;
CREATE TABLE IF NOT EXISTS `pets` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `species` varchar(255) NOT NULL,
  `breed` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `caracteristic` varchar(255) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_id` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pets`
--

INSERT INTO `pets` (`id`, `name`, `species`, `breed`, `age`, `caracteristic`, `weight`, `owner_id`) VALUES
('07770c50-bf93-4c0c-9cb0-906c399fa79b', 'Rex', 'Dog', 'Labrador', 5, 'Friendly', 15.5, 3),
('525783e6-2e4c-4f93-896a-fe8c42600603', 'pepito', 'perro', 'rusp', 5, 'blanco y negro', 10.5, 101528958),
('65401838-66b3-457f-8630-f158f5a51652', 'faraon', 'gato', 'turco', 4, 'amarillo y blanco', 8.5, 6),
('6f84f03f-dd4e-4a06-96e8-605bd1ef1c36', 'mia', 'gato', 'aleman', 8, 'gruñoma', 14.5, 3),
('748c2739-bc6e-4c66-85f0-48819e483dfb', 'Rex', 'Dog', 'Labrador', 5, 'Friendly', 15.5, 3),
('8a14c7e8-dc74-4a59-83db-37d6a76a3a76', 'pepito', 'perro', 'rusp', 5, 'blanco y negro', 10.5, 101528958),
('eec320bf-5277-4043-a595-462c99b36208', 'faraon', 'gato', 'turco', 4, 'amarillo y blanco', 8.5, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=101528959 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `age`, `username`, `password`, `role`) VALUES
(1, 'carlos', 30, 'Admin', '123', 'Admin'),
(2, 'John Doe', 30, 'johndoe', 'secretpassword', 'veterinarian'),
(3, 'yefer', 24, 'yefercordoba', 'motomami', 'owner'),
(4, 'Juan Pérez', 45, 'juanp30', '123456789', 'veterinarian'),
(5, 'camila garcia', 36, 'camilagarcia', '789456123', 'veterinarian'),
(6, 'pepe gomez', 24, 'pepegomez', '456123', 'owner'),
(123, 'Yefer', 25, 'yefer25', '1234segura', 'veterinarian'),
(101528958, 'carlos fernandes', 35, 'carlos01', '123456segura2', 'owner');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoices_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `invoices_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `medical_history`
--
ALTER TABLE `medical_history`
  ADD CONSTRAINT `FK9aifknpmguaomki2ylwl12st4` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `medical_history_ibfk_1` FOREIGN KEY (`veterinarian_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `medical_history_ibfk_2` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `medication_order_items`
--
ALTER TABLE `medication_order_items`
  ADD CONSTRAINT `FKjty9xp469u3akr8kxaru7v704` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Filtros para la tabla `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`veterinarian_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`medical_history_id`) REFERENCES `medical_history` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `pets`
--
ALTER TABLE `pets`
  ADD CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
