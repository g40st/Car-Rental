-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 25, 2017 at 11:44 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Dapro2`
--

-- --------------------------------------------------------

--
-- Table structure for table `Ausstattungen`
--

DROP TABLE IF EXISTS `Ausstattungen`;
CREATE TABLE `Ausstattungen` (
  `ID` int(10) UNSIGNED NOT NULL,
  `Bezeichnung` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Ausstattungen`
--

INSERT INTO `Ausstattungen` (`ID`, `Bezeichnung`) VALUES
(1, 'Klimaanlage'),
(2, 'Anh?ngekupplung'),
(3, 'Navigationssystem'),
(4, 'Tempomat');

-- --------------------------------------------------------

--
-- Table structure for table `Auto`
--

DROP TABLE IF EXISTS `Auto`;
CREATE TABLE `Auto` (
  `Kennzeichen` char(10) NOT NULL,
  `KMStand` int(11) DEFAULT NULL,
  `TUVTermin` date DEFAULT NULL,
  `Kaufdatum` date DEFAULT NULL,
  `Modell` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Auto`
--

INSERT INTO `Auto` (`Kennzeichen`, `KMStand`, `TUVTermin`, `Kaufdatum`, `Modell`) VALUES
('RV AB 335', 45000, '2004-05-01', '2002-05-01', 3),
('RV AB 336', 39000, '2004-05-01', '2002-05-01', 3),
('RV AB 337', 41000, '2004-05-01', '2002-05-01', 3),
('RV BQ 591', 65000, '2005-06-01', '2002-05-01', 4),
('RV BQ 592', 66000, '2005-06-01', '2002-05-01', 4),
('RV BQ 593', 64500, '2005-06-01', '2002-05-01', 4),
('RV C 45', 150000, '2005-04-01', '2002-05-01', 6),
('RV MM 999', 16000, '2005-04-01', '2002-05-01', 5),
('RV PF 23', 25000, '2005-04-01', '2002-05-01', 7),
('RV XY 245', 18000, '2005-04-01', '2002-05-01', 1),
('RV XY 246', 19000, '2005-04-01', '2002-05-01', 1),
('RV XY 247', 21000, '2005-04-01', '2002-05-01', 1),
('RV XY 248', 35000, '2005-04-01', '2002-05-01', 2),
('RV XY 249', 29050, '2005-04-01', '2002-05-01', 2);

-- --------------------------------------------------------

--
-- Table structure for table `Autoarten`
--

DROP TABLE IF EXISTS `Autoarten`;
CREATE TABLE `Autoarten` (
  `ID` int(10) UNSIGNED NOT NULL,
  `Art` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Autoarten`
--

INSERT INTO `Autoarten` (`ID`, `Art`) VALUES
(3, 'Cabrio'),
(5, 'Kleinbus'),
(2, 'Kombi'),
(1, 'Limousine'),
(6, 'LKW'),
(7, 'Pickup'),
(4, 'Van');

-- --------------------------------------------------------

--
-- Table structure for table `Automodell`
--

DROP TABLE IF EXISTS `Automodell`;
CREATE TABLE `Automodell` (
  `ID` int(10) UNSIGNED NOT NULL,
  `Bezeichnung` varchar(20) NOT NULL,
  `Hersteller` varchar(20) DEFAULT NULL,
  `Autoart` int(11) DEFAULT NULL,
  `Sitzplaetze` int(11) DEFAULT NULL,
  `kw` int(11) DEFAULT NULL,
  `Treibstoff` char(10) DEFAULT NULL,
  `PreisTag` float(5,2) DEFAULT NULL,
  `PreisKM` float(5,2) DEFAULT NULL,
  `Achsen` int(11) DEFAULT '2',
  `Ladevolumen` int(11) DEFAULT NULL,
  `Zuladung` int(11) DEFAULT NULL,
  `Fuererschein` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Automodell`
--

INSERT INTO `Automodell` (`ID`, `Bezeichnung`, `Hersteller`, `Autoart`, `Sitzplaetze`, `kw`, `Treibstoff`, `PreisTag`, `PreisKM`, `Achsen`, `Ladevolumen`, `Zuladung`, `Fuererschein`) VALUES
(1, 'Golf FSI', 'VW', 1, 5, 80, 'Super', 54.70, 0.04, 2, 350, 400, 'A'),
(2, 'Golf Variant TDI', 'VW', 2, 5, 90, 'Diesel', 62.30, 0.05, 2, 450, 500, 'A'),
(3, 'Golf', 'VW', 1, 5, 60, 'Super', 45.00, 0.03, 2, 350, 400, 'A'),
(4, 'Astra', 'Opel', 1, 5, 70, 'Super', 40.70, 0.04, 2, 330, 380, 'A'),
(5, '528i', 'BMW', 1, 5, 120, 'Super', 83.55, 0.07, 2, 320, 440, 'A'),
(6, 'Taurus', 'Daimler-Chrysler', 6, 3, 340, 'Diesel', 120.30, 0.09, 3, 20000, 4000, 'B'),
(7, 'Sharan', 'VW', 4, 7, 100, 'Super', 85.60, 0.05, 2, 550, 500, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `Fuehrerscheine`
--

DROP TABLE IF EXISTS `Fuehrerscheine`;
CREATE TABLE `Fuehrerscheine` (
  `KundeID` int(11) DEFAULT NULL,
  `Klasse` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Fuehrerscheine`
--

INSERT INTO `Fuehrerscheine` (`KundeID`, `Klasse`) VALUES
(1, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `Kunde`
--

DROP TABLE IF EXISTS `Kunde`;
CREATE TABLE `Kunde` (
  `ID` int(10) UNSIGNED NOT NULL,
  `Vorname` varchar(50) DEFAULT NULL,
  `Nachname` varchar(50) DEFAULT NULL,
  `PLZ` char(5) DEFAULT NULL,
  `Ort` varchar(50) DEFAULT NULL,
  `Strasse` varchar(50) DEFAULT NULL,
  `EMail` varchar(30) DEFAULT NULL,
  `TelNr` char(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Kunde`
--

INSERT INTO `Kunde` (`ID`, `Vorname`, `Nachname`, `PLZ`, `Ort`, `Strasse`, `EMail`, `TelNr`) VALUES
(1, 'Thomas', 'Müller', '84532', 'Hamburg', 'Teststr. 1', 'thomas@web.de', '071651563');

-- --------------------------------------------------------

--
-- Table structure for table `Leihvertrag`
--

DROP TABLE IF EXISTS `Leihvertrag`;
CREATE TABLE `Leihvertrag` (
  `ID` int(10) UNSIGNED NOT NULL,
  `KundeID` int(11) DEFAULT NULL,
  `AutoID` int(11) DEFAULT NULL,
  `Beginn` datetime DEFAULT NULL,
  `Ende` datetime DEFAULT NULL,
  `StartKM` int(11) DEFAULT NULL,
  `EndeKM` int(11) DEFAULT NULL,
  `Rechnungsbetrag` float(6,2) DEFAULT NULL,
  `bezahlt` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ModHatAusst`
--

DROP TABLE IF EXISTS `ModHatAusst`;
CREATE TABLE `ModHatAusst` (
  `MID` int(11) DEFAULT NULL,
  `AID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ModHatAusst`
--

INSERT INTO `ModHatAusst` (`MID`, `AID`) VALUES
(1, 1),
(2, 2),
(5, 1),
(5, 3),
(5, 4),
(7, 1),
(7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Reservierung`
--

DROP TABLE IF EXISTS `Reservierung`;
CREATE TABLE `Reservierung` (
  `ID` int(10) UNSIGNED NOT NULL,
  `KundeID` int(11) DEFAULT NULL,
  `ModellID` int(11) DEFAULT NULL,
  `Beginn` datetime DEFAULT NULL,
  `Ende` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Reservierung`
--

INSERT INTO `Reservierung` (`ID`, `KundeID`, `ModellID`, `Beginn`, `Ende`) VALUES
(1, 1, 2, '2017-12-25 00:00:00', '2017-12-25 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Ausstattungen`
--
ALTER TABLE `Ausstattungen`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Auto`
--
ALTER TABLE `Auto`
  ADD PRIMARY KEY (`Kennzeichen`);

--
-- Indexes for table `Autoarten`
--
ALTER TABLE `Autoarten`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Art` (`Art`);

--
-- Indexes for table `Automodell`
--
ALTER TABLE `Automodell`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Kunde`
--
ALTER TABLE `Kunde`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Leihvertrag`
--
ALTER TABLE `Leihvertrag`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Reservierung`
--
ALTER TABLE `Reservierung`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Ausstattungen`
--
ALTER TABLE `Ausstattungen`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Autoarten`
--
ALTER TABLE `Autoarten`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `Automodell`
--
ALTER TABLE `Automodell`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `Kunde`
--
ALTER TABLE `Kunde`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Leihvertrag`
--
ALTER TABLE `Leihvertrag`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Reservierung`
--
ALTER TABLE `Reservierung`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
