-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2021 at 04:51 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_nafafood`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_checkout`
--

CREATE TABLE `tb_checkout` (
  `id_checkout` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `tgl_checkout` varchar(30) NOT NULL,
  `total_harga` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_makanan`
--

CREATE TABLE `tb_makanan` (
  `id_makanan` int(11) NOT NULL,
  `nama_makanan` varchar(30) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `gambar` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_makanan`
--

INSERT INTO `tb_makanan` (`id_makanan`, `nama_makanan`, `owner`, `harga`, `gambar`) VALUES
(1, 'Soto Ayam', 'Pak Sulaiman', '13000', 'soto.jpg'),
(2, 'Pempek', 'pak Joko', '15000', 'pempek.jpg'),
(3, 'Cakwe', 'bang Andi', '10000', 'cakwe.jpg'),
(4, 'Mie Rebus Ayam', 'bu Ani', '10000', 'mierebus.png');

-- --------------------------------------------------------

--
-- Table structure for table `tb_nafapay`
--

CREATE TABLE `tb_nafapay` (
  `id_nafapay` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `saldo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_nafapay`
--

INSERT INTO `tb_nafapay` (`id_nafapay`, `id_user`, `saldo`) VALUES
(1, 1, '177598'),
(2, 5, '0');

-- --------------------------------------------------------

--
-- Table structure for table `tb_orderlist`
--

CREATE TABLE `tb_orderlist` (
  `id_order` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_makanan` int(11) NOT NULL,
  `tgl_order` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `hak_akses` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `nama`, `email`, `password`, `hak_akses`) VALUES
(1, 'Naufal Alfadhil', 'naufal@nafaarts.com', '123123', 'Administrator'),
(5, 'icha silvia', 'ichasilvia@gmail.com', '123123', 'Pelanggan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_checkout`
--
ALTER TABLE `tb_checkout`
  ADD PRIMARY KEY (`id_checkout`);

--
-- Indexes for table `tb_makanan`
--
ALTER TABLE `tb_makanan`
  ADD PRIMARY KEY (`id_makanan`);

--
-- Indexes for table `tb_nafapay`
--
ALTER TABLE `tb_nafapay`
  ADD PRIMARY KEY (`id_nafapay`);

--
-- Indexes for table `tb_orderlist`
--
ALTER TABLE `tb_orderlist`
  ADD PRIMARY KEY (`id_order`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_checkout`
--
ALTER TABLE `tb_checkout`
  MODIFY `id_checkout` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_makanan`
--
ALTER TABLE `tb_makanan`
  MODIFY `id_makanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_nafapay`
--
ALTER TABLE `tb_nafapay`
  MODIFY `id_nafapay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_orderlist`
--
ALTER TABLE `tb_orderlist`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
