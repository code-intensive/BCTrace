-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2021 at 12:15 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bctrace`
--

-- --------------------------------------------------------

--
-- Table structure for table `consume`
--

CREATE TABLE `consume` (
  `id` int(255) NOT NULL,
  `batch` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `mixer` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `sku` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `keg` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `c_date` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `c_time` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `operator` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `bin` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `dateResult` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `timeResult` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `usages` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `machine` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `consumed_keg` varchar(12) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consume_prod`
--

CREATE TABLE `consume_prod` (
  `batch` varchar(10) NOT NULL,
  `mixer` varchar(10) NOT NULL,
  `sku` varchar(25) NOT NULL,
  `keg` varchar(10) NOT NULL,
  `c_date` varchar(15) NOT NULL,
  `c_time` varchar(15) NOT NULL,
  `operator` varchar(20) NOT NULL,
  `bin` varchar(13) NOT NULL,
  `dateResult` varchar(15) NOT NULL,
  `timeResult` varchar(15) NOT NULL,
  `usages` varchar(10) NOT NULL,
  `machine` varchar(10) NOT NULL,
  `consumed_keg` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consume_prod`
--

INSERT INTO `consume_prod` (`batch`, `mixer`, `sku`, `keg`, `c_date`, `c_time`, `operator`, `bin`, `dateResult`, `timeResult`, `usages`, `machine`, `consumed_keg`) VALUES
('210615B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '9:43 AM', 'Adeniyi Micheal', '5', ' 06/17/2021', '9:44 AM', 'Production', 'RCR3', '2232'),
('210615B39', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '9:45 AM', 'Adeniyi Micheal', '9', ' 06/17/2021', '9:45 AM', 'Production', 'RCR11', '1664'),
('210675B32', 'Amixon', 'Royco Beef', '2', ' 06/17/2021', '10:06 AM', 'Adeniyi Micheal', '8', ' 06/17/2021', '10:07 AM', 'Production', 'RCR5', '9978'),
('210615B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '10:36 AM', 'Adeniyi Micheal', '4', ' 06/17/2021', '10:36 AM', 'Production', 'RCK11', '9978'),
('210415B32', 'Amixon', 'Royco Beef', '2', ' 06/17/2021', '10:39 AM', 'Adeniyi Micheal', '5', ' 06/17/2021', '10:40 AM', 'Production', 'RCR10', '3324'),
('210415B32', 'Amixon', 'Royco Beef', '2', ' 06/17/2021', '10:39 AM', 'Adeniyi Micheal', '5', ' 06/17/2021', '10:46 AM', 'Production', 'RCR9', '9955'),
('210615B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '11:44 AM', 'Adeniyi Micheal', '4', ' 06/17/2021', '11:44 AM', 'Production', 'RCR5', '9955'),
('210915B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '11:49 AM', 'Adeniyi Micheal', '8', ' 06/17/2021', '11:50 AM', 'Production', 'RCK12', '8834'),
('210915B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '11:49 AM', 'Adeniyi Micheal', '8', ' 06/17/2021', '11:51 AM', 'Production', 'RCK11', '5524'),
('210915B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '11:49 AM', 'Adeniyi Micheal', '8', ' 06/17/2021', '11:52 AM', 'Production', 'RCK9', '5524'),
('210915B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '11:49 AM', 'Adeniyi Micheal', '8', ' 06/17/2021', '11:52 AM', 'Production', 'RCK11', '9955'),
('210615B39', 'Amixon', 'Royco Beef', '2', ' 06/17/2021', '12:19 PM', 'Adeniyi Micheal', '8', ' 06/17/2021', '12:20 PM', 'Production', 'RCK10', '8634'),
('210715B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '12:19 PM', 'Adeniyi Micheal', '9', ' 06/17/2021', '12:21 PM', 'Production', 'RCK11', '6443'),
('210715B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '12:19 PM', 'Adeniyi Micheal', '9', ' 06/17/2021', '12:22 PM', 'Production', 'RCR6', '3246'),
('210015B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '12:18 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '12:24 PM', 'Production', 'RCK12', '8834'),
('210615B39', 'Amixon', 'Royco Beef', '2', ' 06/17/2021', '12:19 PM', 'Adeniyi Micheal', '8', ' 06/17/2021', '12:24 PM', 'Production', 'RCK25', '2212'),
('210715B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '12:19 PM', 'Adeniyi Micheal', '9', ' 06/17/2021', '12:28 PM', 'Production', 'RCK9', '9253'),
('290615B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '1:27 PM', 'Adeniyi Micheal', '9', ' 06/17/2021', '1:28 PM', 'Production', 'RCK10', '8634'),
('214615B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '1:29 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '1:30 PM', 'Production', 'RCK8', '6634'),
('214615B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '1:29 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '1:31 PM', 'Production', 'RCK16', '8634'),
('214615B32', 'Amixon', 'Royco Beef', '3', ' 06/17/2021', '1:29 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '3:17 PM', 'Production', 'RCK12', '9956'),
('310615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/17/2021', '4:10 PM', 'Adeniyi Micheal', '8', ' 06/17/2021', '4:11 PM', 'Production', 'RCK11', '3324'),
('510615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/17/2021', '4:12 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '4:14 PM', 'Production', 'RCK18', '6442'),
('218875B32', 'Amixon', 'Knorr Chicken', '1', ' 06/17/2021', '4:05 PM', 'Adeniyi Micheal', '8', ' 06/17/2021', '4:16 PM', 'Production', 'RCK10', '6656'),
('410615B32', 'Amixon', 'Knorr Chicken', '2', ' 06/17/2021', '4:18 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '4:19 PM', 'Production', 'RCK10', '8856'),
('550615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:16 AM', 'Adeniyi Micheal', '5', ' 06/18/2021', '9:19 AM', 'Production', 'RCK19', '6656'),
('410615B32', 'Amixon', 'Knorr Chicken', '2', ' 06/17/2021', '4:18 PM', 'Adeniyi Micheal', '7', ' 06/18/2021', '9:24 AM', 'Production', 'RCK10', '7776'),
('210615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:26 AM', 'Adeniyi Micheal', '4', ' 06/18/2021', '9:33 AM', 'Production', 'RCK9', '9675'),
('210615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:26 AM', 'Adeniyi Micheal', '4', ' 06/18/2021', '9:37 AM', 'Production', 'RCK10', '9675');

-- --------------------------------------------------------

--
-- Table structure for table `consume_rwk`
--

CREATE TABLE `consume_rwk` (
  `batch` varchar(15) NOT NULL,
  `mixer` varchar(10) NOT NULL,
  `sku` varchar(25) NOT NULL,
  `keg` varchar(10) NOT NULL,
  `c_date` varchar(15) NOT NULL,
  `c_time` varchar(15) NOT NULL,
  `operator` varchar(25) NOT NULL,
  `bin` varchar(13) NOT NULL,
  `dateResult` varchar(15) NOT NULL,
  `timeResult` varchar(15) NOT NULL,
  `usages` varchar(10) NOT NULL,
  `percentage` varchar(10) NOT NULL,
  `consumed_keg` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consume_rwk`
--

INSERT INTO `consume_rwk` (`batch`, `mixer`, `sku`, `keg`, `c_date`, `c_time`, `operator`, `bin`, `dateResult`, `timeResult`, `usages`, `percentage`, `consumed_keg`) VALUES
('210015B32', 'Amixon', 'Royco Beef', '1', ' 06/17/2021', '12:18 PM', 'Adeniyi Micheal', '7', ' 06/17/2021', '12:22 PM', 'Rework', 'RCK11', '8834');

-- --------------------------------------------------------

--
-- Table structure for table `consumption`
--

CREATE TABLE `consumption` (
  `id` int(11) NOT NULL,
  `batch` varchar(25) NOT NULL,
  `mixer` varchar(25) NOT NULL,
  `sku` varchar(25) NOT NULL,
  `keg` varchar(25) NOT NULL,
  `c_date` varchar(25) NOT NULL,
  `c_time` varchar(25) NOT NULL,
  `operator` varchar(25) NOT NULL,
  `bin` varchar(25) NOT NULL,
  `dateResult` varchar(25) NOT NULL,
  `timeResult` varchar(25) NOT NULL,
  `usages` varchar(25) NOT NULL,
  `machine` varchar(25) NOT NULL,
  `consumed_keg` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumption`
--

INSERT INTO `consumption` (`id`, `batch`, `mixer`, `sku`, `keg`, `c_date`, `c_time`, `operator`, `bin`, `dateResult`, `timeResult`, `usages`, `machine`, `consumed_keg`) VALUES
(1, '550615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:16 AM', 'Adeniyi Micheal', '5', ' 06/18/2021', '9:19 AM', 'Production', 'RCK19', '6656'),
(2, '410615B32', 'Amixon', 'Knorr Chicken', '2', ' 06/17/2021', '4:18 PM', 'Adeniyi Micheal', '7', ' 06/18/2021', '9:24 AM', 'Production', 'RCK10', '7776'),
(3, '210615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:26 AM', 'Adeniyi Micheal', '4', ' 06/18/2021', '9:33 AM', 'Production', 'RCK9', '9675'),
(4, '210615B32', 'Amixon', 'Knorr Chicken', '1', ' 06/18/2021', '9:26 AM', 'Adeniyi Micheal', '4', ' 06/18/2021', '9:37 AM', 'Production', 'RCK10', '9675');

-- --------------------------------------------------------

--
-- Table structure for table `find`
--

CREATE TABLE `find` (
  `id` int(255) NOT NULL,
  `batch` varchar(20) NOT NULL,
  `mixer` varchar(20) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `keg` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(20) NOT NULL,
  `operator` varchar(30) NOT NULL,
  `bin` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `find`
--

INSERT INTO `find` (`id`, `batch`, `mixer`, `sku`, `keg`, `date`, `time`, `operator`, `bin`) VALUES
(1, 'KC4423', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '7:39 PM', 'Adeniyi Micheal', '8'),
(2, 'KC4463', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '7:41 PM', 'Adeniyi Micheal', '8'),
(3, 'KC4493', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '7:58 PM', 'Adeniyi Micheal', '8'),
(4, 'KC4473', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '8:00 PM', 'Adeniyi Micheal', '8'),
(5, 'KC9423', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '8:05 PM', 'Adeniyi Micheal', '9'),
(6, 'KC4623', 'Amixon', 'Knorr Chicken', '1', '0000-00-00', '8:12 PM', 'Adeniyi Micheal', '8'),
(7, 'KC1124', 'Amixon', 'Knorr Chicken', '1', '2021-06-14', '8:28 PM', 'Adeniyi Micheal', '8'),
(8, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-15', '4:03 PM', 'Adeniyi Micheal', '1'),
(9, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '9:43 AM', 'Adeniyi Micheal', '5'),
(10, '210615B39', 'Amixon', 'Royco Beef', '1', '2021-06-17', '9:45 AM', 'Adeniyi Micheal', '9'),
(11, '210675B32', 'Amixon', 'Royco Beef', '2', '2021-06-17', '10:06 AM', 'Adeniyi Micheal', '8'),
(12, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '10:36 AM', 'Adeniyi Micheal', '4'),
(13, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '10:37 AM', 'Adeniyi Micheal', '5'),
(14, '210515B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '10:38 AM', 'Adeniyi Micheal', '4'),
(15, '210415B32', 'Amixon', 'Royco Beef', '2', '2021-06-17', '10:39 AM', 'Adeniyi Micheal', '5'),
(16, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '11:44 AM', 'Adeniyi Micheal', '4'),
(17, '210915B32', 'Amixon', 'Royco Beef', '3', '2021-06-17', '11:49 AM', 'Adeniyi Micheal', '8'),
(18, '210015B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '12:18 PM', 'Adeniyi Micheal', '7'),
(19, '210715B32', 'Amixon', 'Royco Beef', '3', '2021-06-17', '12:19 PM', 'Adeniyi Micheal', '9'),
(20, '210615B39', 'Amixon', 'Royco Beef', '2', '2021-06-17', '12:19 PM', 'Adeniyi Micheal', '8'),
(21, '290615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '1:27 PM', 'Adeniyi Micheal', '9'),
(22, '214615B32', 'Amixon', 'Royco Beef', '3', '2021-06-17', '1:29 PM', 'Adeniyi Micheal', '7'),
(23, '210615B32', 'Amixon', 'Royco Beef', '1', '2021-06-17', '1:37 PM', 'Adeniyi Micheal', '4'),
(24, '210615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '3:12 PM', 'Adeniyi Micheal', '4'),
(25, '210615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '4:05 PM', 'Adeniyi Micheal', '3'),
(26, '218875B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '4:05 PM', 'Adeniyi Micheal', '8'),
(27, '210615B39', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '4:06 PM', 'Adeniyi Micheal', '7'),
(28, '310615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '4:10 PM', 'Adeniyi Micheal', '8'),
(29, '510615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-17', '4:12 PM', 'Adeniyi Micheal', '7'),
(30, '410615B32', 'Amixon', 'Knorr Chicken', '2', '2021-06-17', '4:18 PM', 'Adeniyi Micheal', '7'),
(31, '550615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-18', '9:16 AM', 'Adeniyi Micheal', '5'),
(32, '550615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-18', '9:16 AM', 'Adeniyi Micheal', '5'),
(33, '210615B32', 'Amixon', 'Knorr Chicken', '1', '2021-06-18', '9:26 AM', 'Adeniyi Micheal', '4');

-- --------------------------------------------------------

--
-- Table structure for table `generate`
--

CREATE TABLE `generate` (
  `id` int(255) NOT NULL,
  `batch` varchar(15) NOT NULL,
  `mixer` varchar(10) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `keg` varchar(10) NOT NULL,
  `date` varchar(50) NOT NULL,
  `time` varchar(10) NOT NULL,
  `operator` varchar(20) NOT NULL,
  `bin` varchar(5) NOT NULL,
  `keg_1` varchar(10) DEFAULT NULL,
  `keg_2` varchar(10) DEFAULT NULL,
  `keg_3` varchar(10) DEFAULT NULL,
  `keg_4` varchar(10) DEFAULT NULL,
  `keg_5` varchar(10) DEFAULT NULL,
  `keg_6` varchar(10) DEFAULT NULL,
  `keg_7` varchar(10) DEFAULT NULL,
  `keg_8` varchar(10) DEFAULT NULL,
  `keg_9` varchar(10) DEFAULT NULL,
  `keg_10` varchar(10) DEFAULT NULL,
  `keg_11` varchar(10) DEFAULT NULL,
  `keg_12` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `kegs`
--

CREATE TABLE `kegs` (
  `id` int(11) NOT NULL,
  `batch` varchar(25) NOT NULL,
  `keg_1` varchar(25) NOT NULL,
  `keg_2` varchar(25) NOT NULL,
  `keg_3` varchar(25) NOT NULL,
  `keg_4` varchar(25) NOT NULL,
  `keg_5` varchar(25) NOT NULL,
  `keg_6` varchar(25) NOT NULL,
  `keg_7` varchar(25) NOT NULL,
  `keg_8` varchar(25) NOT NULL,
  `keg_9` varchar(25) NOT NULL,
  `keg_10` varchar(25) NOT NULL,
  `keg_11` varchar(25) NOT NULL,
  `keg_12` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kegs`
--

INSERT INTO `kegs` (`id`, `batch`, `keg_1`, `keg_2`, `keg_3`, `keg_4`, `keg_5`, `keg_6`, `keg_7`, `keg_8`, `keg_9`, `keg_10`, `keg_11`, `keg_12`) VALUES
(1, '510615B32', '6442', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty'),
(2, '510615B32', '6442', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty'),
(3, '410615B32', '7776', '8856', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty'),
(4, '550615B32', '6656', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty'),
(5, '210615B32', '9675', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty', 'Empty');

-- --------------------------------------------------------

--
-- Table structure for table `minor`
--

CREATE TABLE `minor` (
  `id` int(255) NOT NULL,
  `minorNo` varchar(20) NOT NULL,
  `mixer` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `minor`
--

INSERT INTO `minor` (`id`, `minorNo`, `mixer`) VALUES
(2, 'KC4423', 'Amixon'),
(3, '210615B32', 'Amixon'),
(4, 'B4424', 'Lodige'),
(5, '210615B32', 'Amixon');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(20) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `operator_name` varchar(115) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `username`, `password`, `operator_name`) VALUES
(1, 'ikeonly', 'Ikeonly@only1', ''),
(2, 'Ikeonly', 'godhand', ''),
(3, 'adeniyi', 'micheal', 'Adeniyi Micheal'),
(4, 'arregf', '2222', 'adsdf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consume`
--
ALTER TABLE `consume`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `consumption`
--
ALTER TABLE `consumption`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `find`
--
ALTER TABLE `find`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `generate`
--
ALTER TABLE `generate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kegs`
--
ALTER TABLE `kegs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `minor`
--
ALTER TABLE `minor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consume`
--
ALTER TABLE `consume`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `consumption`
--
ALTER TABLE `consumption`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `find`
--
ALTER TABLE `find`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `generate`
--
ALTER TABLE `generate`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `kegs`
--
ALTER TABLE `kegs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `minor`
--
ALTER TABLE `minor`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
