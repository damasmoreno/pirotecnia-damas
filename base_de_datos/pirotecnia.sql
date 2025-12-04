-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2025 a las 19:21:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pirotecnia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `ID_Articulo` int(11) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `precio` double NOT NULL,
  `fecha_alta` varchar(255) DEFAULT NULL,
  `imagen_nombre` varchar(255) DEFAULT NULL,
  `ID_Seccion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`ID_Articulo`, `Descripcion`, `precio`, `fecha_alta`, `imagen_nombre`, `ID_Seccion`) VALUES
(1, '100 chinos', 3.18, '2019-04-15', 'chinos', 1),
(2, '50 águilas', 2, '2015-02-01', 'aguilas', 1),
(3, '25 super falleros', 4, '2024-11-21', 'super_falleros', 1),
(4, 'Super XXL', 10, '2010-01-10', 'super_xxl', 1),
(5, 'Traca valenciana 10 metros', 19, '2025-12-03', 'traca_10', 4),
(6, 'Bengalas AllStar', 1.05, '2022-07-30', 'bengalas_allstar', 3),
(7, 'Batería Macao', 21, '2025-09-05', 'bateria_macao', 9),
(8, 'Parchís (4)', 6.95, '2025-10-15', 'parchis', 6),
(9, 'Batería Qatar', 37.95, '2023-11-31', 'qatar', 9),
(10, 'Batería Confeti', 29.95, '2012-01-21', 'confeti', 9),
(11, 'Bengalas colorKerzen', 2.75, '2010-04-18', 'colorkerzen', 3),
(12, 'Bengalas Sunset Tricolor', 6.95, '2015-06-21', 'sunset_tricolor', 3),
(13, 'Fuente Áurea', 15.95, '2014-07-01', 'aurea', 8),
(14, 'Fuente Giralda', 23.5, '2016-12-05', 'giralda', 8),
(15, 'Fuente La Roca', 7.95, '2017-08-14', 'laroca', 8),
(16, 'Fuente Capri', 4.95, '2018-09-23', 'capri', 7),
(17, 'Fuente Trevi', 3.75, '2019-03-09', 'trevi', 7),
(18, 'Fuente Piccola', 2.75, '2021-11-25', 'piccola', 6),
(19, 'Fuente Big Flash', 8.95, '2022-05-19', 'big_flash', 6),
(20, 'Traca 20 Chinos', 0.95, '2022-09-24', 'traca_chinos', 5),
(21, 'Traca 160 Águilas', 19.95, '2010-01-17', 'traca_aguilas', 5),
(22, 'Silbido y cracker', 2.75, '2013-09-07', 'silbido', 2),
(23, 'Cohetes Rocket', 10.95, '2016-12-11', 'rocket', 2),
(24, 'Cohetes Winder', 39.95, '2018-11-13', 'winder', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `nacionalidad`, `nif`, `fecha_nacimiento`, `correo_electronico`, `password`) VALUES
(1, 'Laura Gómez', 'Española', '47583926M', '1991-04-15', 'laura.gomez@iberonet.es', 'Lg!2025xR#9'),
(2, 'Carlos Hernández', 'Mexicana', '89012345L', '1989-05-12', 'carlos.hernandez@megacable.mx', 'Ch#8vP2!t4s'),
(3, 'María Rossi', 'Italiana', '12345678R', '1975-08-23', 'maria.rossi@italnet.it', 'Mr7!Lq2Zp6@'),
(4, 'John Smith', 'Estadounidense', '23456789S', '1985-02-10', 'john.smith@usconnect.com', 'Js_4F9p!w2Q'),
(5, 'Sofía Martínez', 'Argentina', '34567890T', '1992-03-04', 'sofia.martinez@argmail.com.ar', 'Sm2025!kR3%'),
(6, 'Pierre Dupont', 'Francesa', '45678901U', '1988-12-19', 'pierre.dupont@frmail.fr', 'Pd!6tX2v9#b'),
(7, 'Lucía Fernández', 'Española', '56789012V', '1995-07-30', 'lucia.fernandez@iberonet.es', 'Lf!5Qw8z2Pa'),
(8, 'Andreas Müller', 'Alemana', '67890123W', '1982-09-14', 'andreas.muller@webmail.de', 'Am_3Yv7!sT1'),
(9, 'Ana Souza', 'Brasileña', '78901234X', '1993-06-21', 'ana.souza@brconnect.com.br', 'As!9kD4p2Gh'),
(10, 'Kenji Tanaka', 'Japonesa', '89012345Y', '1987-11-05', 'kenji.tanaka@jpnet.jp', 'Kt#2rV8!mZ6'),
(11, 'Emma Johnson', 'Británica', '90123456Z', '1991-01-28', 'emma.johnson@ukconnect.co.uk', 'Ej!7pN3x4Wq'),
(12, 'Diego Pérez', 'Chilena', '12345678A', '1996-10-09', 'diego.perez@clmail.cl', 'Dp!1sK8v6Zy');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pedido`
--

CREATE TABLE `detalle_pedido` (
  `id_venta` bigint(20) NOT NULL,
  `ID_Articulo` int(100) NOT NULL,
  `Unidades` int(100) NOT NULL,
  `precio` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `ID_Pedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_pedido`
--

INSERT INTO `detalle_pedido` (`id_venta`, `ID_Articulo`, `Unidades`, `precio`, `total`, `ID_Pedido`) VALUES
(37, 1, 6, 3.18, 19, 1),
(38, 3, 2, 4, 8, 1),
(39, 7, 1, 21, 21, 1),
(40, 2, 8, 2, 16, 2),
(41, 4, 1, 10, 10, 2),
(42, 6, 5, 1, 5, 2),
(43, 5, 2, 19, 38, 3),
(44, 3, 4, 4, 16, 3),
(45, 1, 3, 3.18, 10, 3),
(46, 4, 3, 10, 30, 4),
(47, 7, 1, 21, 21, 4),
(48, 2, 5, 2, 10, 4),
(49, 5, 1, 19, 19, 5),
(50, 6, 10, 1, 10, 5),
(51, 3, 2, 4, 8, 5),
(52, 1, 4, 3.18, 13, 6),
(53, 7, 2, 21, 42, 6),
(54, 4, 1, 10, 10, 6),
(55, 2, 9, 4, 24, 1),
(56, 4, 3, 4, 20, 2),
(57, 7, 4, 4, 28, 3),
(58, 1, 8, 4, 20, 4),
(59, 3, 8, 4, 32, 5),
(60, 3, 7, 4, 12, 6),
(61, 4, 10, 4, 24, 7),
(62, 4, 3, 4, 8, 8),
(63, 7, 6, 4, 36, 9),
(64, 6, 5, 4, 20, 10),
(65, 1, 9, 4, 12, 11),
(66, 3, 2, 4, 16, 12),
(67, 3, 9, 4, 8, 13),
(68, 5, 3, 4, 40, 14),
(69, 3, 9, 4, 40, 15),
(70, 2, 5, 4, 20, 16),
(71, 6, 7, 4, 32, 17),
(72, 6, 7, 4, 36, 18),
(73, 2, 10, 4, 28, 19),
(74, 4, 9, 4, 28, 20),
(75, 5, 3, 4, 36, 21),
(76, 7, 8, 4, 12, 22),
(77, 4, 9, 4, 40, 23),
(78, 7, 3, 4, 40, 24),
(79, 3, 2, 4, 12, 25),
(80, 3, 6, 4, 24, 26),
(81, 6, 4, 4, 16, 27),
(82, 3, 9, 4, 16, 28),
(83, 6, 9, 4, 16, 29),
(84, 4, 8, 4, 20, 30),
(85, 6, 6, 4, 16, 31),
(86, 1, 2, 4, 24, 32),
(87, 7, 6, 4, 24, 33),
(88, 1, 8, 4, 24, 34),
(89, 3, 10, 4, 8, 35),
(90, 2, 4, 4, 20, 36),
(91, 1, 3, 4, 28, 37),
(92, 4, 6, 4, 40, 38),
(93, 4, 9, 4, 24, 39),
(94, 2, 4, 4, 40, 40),
(95, 5, 8, 4, 20, 41),
(96, 6, 7, 4, 40, 42),
(97, 1, 4, 4, 8, 43),
(98, 5, 2, 4, 12, 44),
(99, 6, 3, 4, 32, 45),
(100, 7, 4, 4, 40, 46),
(101, 5, 7, 4, 36, 47),
(102, 3, 7, 4, 8, 48),
(103, 2, 10, 4, 16, 49),
(104, 4, 10, 4, 32, 50),
(105, 2, 5, 4, 32, 51),
(106, 2, 4, 4, 24, 52),
(107, 5, 8, 4, 28, 53),
(108, 2, 8, 4, 16, 54),
(109, 3, 10, 4, 28, 55),
(110, 3, 8, 4, 28, 56),
(111, 1, 5, 4, 28, 57),
(112, 6, 3, 4, 24, 58),
(113, 7, 4, 4, 28, 59),
(114, 2, 6, 4, 20, 60),
(115, 6, 7, 4, 32, 61),
(116, 5, 4, 4, 12, 62),
(117, 7, 6, 4, 32, 63),
(118, 7, 9, 4, 12, 64),
(119, 2, 9, 4, 12, 65),
(120, 5, 8, 4, 20, 66),
(121, 6, 2, 4, 8, 67),
(122, 6, 7, 4, 40, 68),
(123, 1, 2, 4, 24, 69),
(124, 1, 3, 4, 20, 70),
(125, 3, 10, 4, 28, 71),
(126, 1, 6, 4, 20, 72),
(127, 4, 6, 4, 32, 73),
(128, 4, 9, 4, 36, 74),
(129, 4, 4, 4, 28, 75),
(130, 4, 5, 4, 32, 76),
(131, 2, 2, 4, 28, 77),
(132, 6, 10, 4, 32, 78),
(182, 13, 1, 15.95, 15.95, 101),
(183, 14, 1, 23.5, 23.5, 101),
(184, 13, 1, 15.95, 15.95, 102),
(185, 7, 1, 21, 21, 103),
(186, 7, 1, 21, 21, 104),
(187, 7, 1, 21, 21, 105),
(188, 3, 1, 4, 4, 109),
(189, 1, 1, 3.18, 3.18, 115),
(190, 1, 1, 3.18, 3.18, 116),
(191, 2, 1, 2, 2, 116),
(192, 4, 1, 10, 10, 116),
(193, 3, 1, 4, 4, 116),
(194, 20, 1, 0.95, 0.95, 117),
(195, 21, 1, 19.95, 19.95, 117),
(196, 1, 1, 3.18, 3.18, 118),
(197, 2, 2, 2, 4, 118),
(198, 9, 1, 37.95, 37.95, 119),
(199, 22, 1, 2.75, 2.75, 119),
(200, 24, 1, 39.95, 39.95, 119),
(201, 1, 3, 3.18, 9.540000000000001, 120),
(202, 4, 1, 10, 10, 120),
(203, 17, 1, 3.75, 3.75, 120),
(204, 16, 3, 4.95, 14.850000000000001, 120),
(205, 24, 1, 39.95, 39.95, 120);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `ID_Pedido` int(11) NOT NULL,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Fecha_pedido` datetime NOT NULL,
  `total` double DEFAULT NULL,
  `Entregado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`ID_Pedido`, `ID_Cliente`, `Fecha_pedido`, `total`, `Entregado`) VALUES
(1, 1, '2025-09-15 00:00:00', 72, 1),
(2, 3, '2025-09-28 00:00:00', 51, 0),
(3, 5, '2025-10-10 00:00:00', 92, 0),
(4, 7, '2025-10-25 00:00:00', 81, 1),
(5, 9, '2025-11-02 00:00:00', 69, 0),
(6, 12, '2025-11-05 00:00:00', 77, 1),
(7, 1, '2025-09-10 00:00:00', 24, 1),
(8, 1, '2025-09-25 00:00:00', 8, 0),
(9, 1, '2025-10-05 00:00:00', 36, 1),
(10, 1, '2025-10-22 00:00:00', 20, 0),
(11, 1, '2025-11-11 00:00:00', 12, 1),
(12, 1, '2025-12-02 00:00:00', 16, 0),
(13, 2, '2025-09-12 00:00:00', 8, 1),
(14, 2, '2025-09-28 00:00:00', 40, 1),
(15, 2, '2025-10-06 00:00:00', 40, 0),
(16, 2, '2025-10-23 00:00:00', 20, 1),
(17, 2, '2025-11-14 00:00:00', 32, 0),
(18, 2, '2025-12-05 00:00:00', 36, 1),
(19, 3, '2025-09-15 00:00:00', 28, 0),
(20, 3, '2025-09-30 00:00:00', 28, 1),
(21, 3, '2025-10-12 00:00:00', 36, 0),
(22, 3, '2025-10-27 00:00:00', 12, 1),
(23, 3, '2025-11-16 00:00:00', 40, 0),
(24, 3, '2025-12-08 00:00:00', 40, 1),
(25, 4, '2025-09-18 00:00:00', 12, 1),
(26, 4, '2025-10-01 00:00:00', 24, 0),
(27, 4, '2025-10-13 00:00:00', 16, 1),
(28, 4, '2025-10-29 00:00:00', 16, 0),
(29, 4, '2025-11-18 00:00:00', 16, 1),
(30, 4, '2025-12-09 00:00:00', 20, 0),
(31, 5, '2025-09-20 00:00:00', 16, 1),
(32, 5, '2025-10-03 00:00:00', 24, 0),
(33, 5, '2025-10-15 00:00:00', 24, 1),
(34, 5, '2025-10-31 00:00:00', 24, 1),
(35, 5, '2025-11-19 00:00:00', 8, 0),
(36, 5, '2025-12-10 00:00:00', 20, 1),
(37, 6, '2025-09-22 00:00:00', 28, 0),
(38, 6, '2025-10-04 00:00:00', 40, 1),
(39, 6, '2025-10-17 00:00:00', 24, 1),
(40, 6, '2025-11-01 00:00:00', 40, 0),
(41, 6, '2025-11-20 00:00:00', 20, 1),
(42, 6, '2025-12-12 00:00:00', 40, 0),
(43, 7, '2025-09-23 00:00:00', 8, 1),
(44, 7, '2025-10-05 00:00:00', 12, 1),
(45, 7, '2025-10-19 00:00:00', 32, 0),
(46, 7, '2025-11-03 00:00:00', 40, 1),
(47, 7, '2025-11-21 00:00:00', 36, 1),
(48, 7, '2025-12-13 00:00:00', 8, 0),
(49, 8, '2025-09-25 00:00:00', 16, 0),
(50, 8, '2025-10-07 00:00:00', 32, 1),
(51, 8, '2025-10-21 00:00:00', 32, 1),
(52, 8, '2025-11-05 00:00:00', 24, 0),
(53, 8, '2025-11-22 00:00:00', 28, 1),
(54, 8, '2025-12-14 00:00:00', 16, 1),
(55, 9, '2025-09-26 00:00:00', 28, 1),
(56, 9, '2025-10-09 00:00:00', 28, 0),
(57, 9, '2025-10-22 00:00:00', 28, 1),
(58, 9, '2025-11-06 00:00:00', 24, 1),
(59, 9, '2025-11-23 00:00:00', 28, 0),
(60, 9, '2025-12-15 00:00:00', 20, 1),
(61, 10, '2025-09-28 00:00:00', 32, 0),
(62, 10, '2025-10-11 00:00:00', 12, 1),
(63, 10, '2025-10-25 00:00:00', 32, 1),
(64, 10, '2025-11-08 00:00:00', 12, 0),
(65, 10, '2025-11-25 00:00:00', 12, 1),
(66, 10, '2025-12-17 00:00:00', 20, 0),
(67, 11, '2025-09-30 00:00:00', 8, 1),
(68, 11, '2025-10-13 00:00:00', 40, 0),
(69, 11, '2025-10-28 00:00:00', 24, 1),
(70, 11, '2025-11-09 00:00:00', 20, 0),
(71, 11, '2025-11-26 00:00:00', 28, 1),
(72, 11, '2025-12-18 00:00:00', 20, 1),
(73, 12, '2025-10-02 00:00:00', 32, 0),
(74, 12, '2025-10-15 00:00:00', 36, 1),
(75, 12, '2025-10-30 00:00:00', 28, 0),
(76, 12, '2025-11-11 00:00:00', 32, 1),
(77, 12, '2025-11-27 00:00:00', 28, 0),
(78, 12, '2025-12-20 00:00:00', 32, 1),
(101, NULL, '2025-11-15 18:49:01', NULL, 0),
(102, NULL, '2025-11-15 18:50:52', NULL, 0),
(103, NULL, '2025-11-15 18:52:24', NULL, 0),
(104, NULL, '2025-11-15 19:09:00', NULL, 0),
(105, 1, '2025-11-18 18:45:46', 21, 0),
(106, 1, '2025-11-18 18:55:33', 21, 0),
(107, 1, '2025-11-18 19:00:06', 1.05, 0),
(108, 1, '2025-11-18 19:07:09', 23.5, 0),
(109, 1, '2025-11-18 19:11:47', 4, 0),
(110, 1, '2025-11-18 19:12:55', 19.18, 0),
(111, 1, '2025-11-18 19:15:17', 19.18, 0),
(112, 1, '2025-11-18 19:15:48', 19.18, 0),
(113, 1, '2025-11-18 19:20:08', 21.849999999999998, 0),
(114, 1, '2025-11-18 19:30:43', 3.18, 0),
(115, 1, '2025-11-18 19:36:21', 3.18, 0),
(116, 1, '2025-11-18 19:36:54', 19.18, 0),
(117, 1, '2025-11-18 19:41:24', 20.9, 0),
(118, 4, '2025-11-18 19:50:56', 7.18, 0),
(119, 7, '2025-11-20 11:50:27', 80.65, 0),
(120, 1, '2025-11-21 22:35:01', 78.09, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `ID_Seccion` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen_nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`ID_Seccion`, `nombre`, `descripcion`, `imagen_nombre`) VALUES
(1, 'Petardos', 'Gran variedad de petardos y masclets', 'petardos'),
(2, 'Voladores', 'Voladores de todos los tamaños', 'voladores'),
(3, 'Bengalas', 'Bengalas de colores y efectos', 'bengalas'),
(4, 'Traca Valenciana', 'Tracas tradicionales valencianas', 'traca_valenciana'),
(5, 'Traca China', 'Tracas de origen chino', 'traca_china'),
(6, 'Fuente Pequeña', 'Fuentes de hasta 30 cm', 'fuente_pequena'),
(7, 'Fuente Mediana', 'Fuentes de 30 cm a 1 metro', 'fuente_mediana'),
(8, 'Fuente Grande', 'Fuentes de más de 1 metro', 'fuente_grande'),
(9, 'Baterías', 'Baterías de fuegos artificiales', 'baterias');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`ID_Articulo`),
  ADD KEY `fk_articulo_seccion` (`ID_Seccion`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `Nif` (`nif`);

--
-- Indices de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `ID_Articulo` (`ID_Articulo`),
  ADD KEY `ID_Pedido` (`ID_Pedido`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`ID_Pedido`),
  ADD KEY `Id_Cliente` (`ID_Cliente`) USING BTREE,
  ADD KEY `Total` (`total`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`ID_Seccion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `ID_Articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  MODIFY `id_venta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=206;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `ID_Pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT de la tabla `seccion`
--
ALTER TABLE `seccion`
  MODIFY `ID_Seccion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_seccion` FOREIGN KEY (`ID_Seccion`) REFERENCES `seccion` (`ID_Seccion`);

--
-- Filtros para la tabla `detalle_pedido`
--
ALTER TABLE `detalle_pedido`
  ADD CONSTRAINT `ID_ARTICULO_FK` FOREIGN KEY (`ID_Articulo`) REFERENCES `articulo` (`ID_Articulo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_PEDIDO_FK` FOREIGN KEY (`ID_Pedido`) REFERENCES `pedido` (`ID_Pedido`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `ID_CLIENTES_FK` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
