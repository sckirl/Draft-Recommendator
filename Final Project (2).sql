-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 17, 2024 at 10:43 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `FinalProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `MsAbilities`
--

CREATE TABLE `MsAbilities` (
  `abilitiesID` varchar(10) NOT NULL,
  `passive` varchar(255) NOT NULL,
  `ability1` varchar(255) NOT NULL,
  `ability2` varchar(255) NOT NULL,
  `ability3` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MsAbilities`
--

INSERT INTO `MsAbilities` (`abilitiesID`, `passive`, `ability1`, `ability2`, `ability3`) VALUES
('Ass001', 'Buff Heal', 'Damage Blink', 'Aoe Slow', 'Blink Buff\r'),
('Ass002', 'Buff', 'Blink Aoe', 'Aoe Slow', 'Burst Blink\r'),
('Ass004', 'buff', 'summon slow', 'cc speedup', 'morph speedup\r'),
('Ass03', 'buff damage', 'buff speedup', 'aoe', 'burst blink\r'),
('FR001', 'Shield', 'Damage Buff', 'Buff Cc', 'Mobility Damage\r'),
('FR002', 'Buff', 'aoe Slow', 'blink slow', 'Summon Morph\r'),
('FR003', 'Buff', 'CC Aoe', 'CC Shield', 'Summon CC\r'),
('FR004', 'Buff Heal', 'cc damage', 'blink debuff', 'speed up buff\r'),
('FR005', 'Buff', 'Aoe Blink', 'Blink buff', 'CC burst\r'),
('FR006', 'Buff', 'aoe Slow', 'CC AOE', 'CC AOE\r'),
('FR007', 'Debuff Heal', 'aoe', 'blink aoe', 'blink aoe\r'),
('FR008', 'burst', 'blink cc', 'burst blink', 'morph shield\r'),
('FR009', 'Buff', 'AoE', 'CC', 'Burst Mobility\r'),
('FR010', 'burst', 'damage', 'heal aoe', ' burst cc\r'),
('FR011', 'buff', 'Burst ', 'shield cc', 'blink cc\r'),
('FR012', 'damage', 'aoe slow', 'blink cc', 'burst aoe\r'),
('FR013', 'buff', 'speedup buff', 'cc damage', 'Burst Blink\r'),
('FR014', 'buff', 'cc aoe', 'blink aoe', 'burst buff\r'),
('ME001', 'shield cc', 'damage slow', 'R_CC Blink', 'CC burst\r'),
('ME002', 'Buff SpeedUp', 'aoe Slow', 'aoe Slow', 'Heal Buff\r'),
('ME003', 'Death_Immune', 'aoe', 'cc debuff', ' burst cc\r'),
('ME004', 'buff', 'aoe speedup', 'cc aoe', 'burst speedup\r'),
('ME005', 'buff shield', 'shield buff', 'aoe buff', 'burst mobility\r'),
('ME006', 'cc damage', 'aoe slow', 'aoe', 'burst buff\r'),
('ME007', 'damage slow', 'cc damage', 'aoe', 'burst\r'),
('ME008', 'buff', 'aoe', 'blink buff', 'buff slow\r'),
('ME009', 'buff damage', 'damage buff', 'blink cc', 'burst cc\r'),
('ME010', 'buff', 'aoe slow', 'cc damage', 'burst blink\r'),
('MM001', 'Buff', 'Buff AOE', 'CC AOE', 'CC_Immune R_CC\r'),
('MM002', 'burst', 'aoe', 'blink cc', 'aoe slow\r'),
('MM003', 'buff', 'aoe debuff', 'slow aoe', 'buff blink\r'),
('MM004', 'buff', 'aoe slow', 'blink damage', 'buff burst\r'),
('MM005', 'buff blink', 'aoe cc', 'r_cc aoe', 'burst\r'),
('MM006', 'buff', 'morph buff', 'blink buff', 'burst\r'),
('MM007', 'buff', 'burst buff', 'aoe slow', 'burst buff\r'),
('MM008', 'buff', 'blink buff', 'cc damage', 'burst slow\r'),
('MM009', 'buff', 'burst', 'mobility buff', 'burst slow\r'),
('MM010', 'buff', 'aoe buff', 'blink summon', 'burst shield\r'),
('MM011', 'buff', 'damage slow', 'blink cc', 'burst cc\r'),
('Sup001', 'SpeedUp', 'Heal AOE', 'Slow Cc', 'Attach Shield\r'),
('Sup002', 'burst ', 'aoe slow', 'heal Speedup', 'cc aoe\r'),
('Sup003', 'buff', 'heal buff', 'slow aoe', 'Heal Buff\r'),
('Sup004', 'buff', 'buff cc', 'aoe burst ', 'death_immune buff\r'),
('Sup005', 'buff', 'damage heal', 'cc damage', 'heal damage'),
('TK001', 'Buff', 'aoe Slow', 'Charge CC', 'CC AOE\r'),
('TK002', 'Buff', 'aoe Slow', 'CC AOE', 'Mobility AOE\r'),
('TK003', 'buff debuff', 'aoe', 'mobility cc', 'cc aoe\r'),
('TK004', 'buff', 'speedUp aoe', 'damage cc', 'burst cc\r'),
('TK005', 'buff debuff', 'speedup cc', 'damage slow', 'speed up aoe\r'),
('TK006', 'shield ', 'cc aoe', 'aoe slow', 'morph cc\r'),
('TK007', 'heal damage', 'mobility cc', 'slow buff', 'cc aoe\r'),
('TK008', 'buff', 'cc damage', 'slow', 'burst cc\r'),
('TK009', 'buff', 'cc aoe', 'heal buff', 'cc\r'),
('TK010', 'buff', 'aoe slow', 'blink damage', 'cc buff\r'),
('﻿abilities', 'passive', 'ability1', 'ability2', 'ability3\r');

-- --------------------------------------------------------

--
-- Table structure for table `MsCounter`
--

CREATE TABLE `MsCounter` (
  `counterID` varchar(10) NOT NULL,
  `counterAbility` varchar(50) NOT NULL,
  `abilityToCounter` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MsCounter`
--

INSERT INTO `MsCounter` (`counterID`, `counterAbility`, `abilityToCounter`) VALUES
('CO001', 'R_CC', 'CC'),
('CO002', 'BURST', 'SHIELD'),
('CO003', 'SLOW', 'SPEEDUP'),
('CO004', 'CC', 'BLINK'),
('CO005', 'BLINK', 'AOE'),
('CO006', 'DEBUFF', 'BUFF'),
('CO007', 'DEATH_IMMUNE', 'DAMAGE'),
('CO008', 'MOBILITY', 'SLOW'),
('CO009', 'BURST', 'HEAL'),
('CO010', 'DAMAGE', 'SHIELD');

-- --------------------------------------------------------

--
-- Table structure for table `MsHeros`
--

CREATE TABLE `MsHeros` (
  `herosID` varchar(11) NOT NULL,
  `heroName` varchar(100) NOT NULL,
  `roleID` varchar(11) NOT NULL,
  `abilitiesID` varchar(10) NOT NULL,
  `hp` bigint(255) NOT NULL DEFAULT 100,
  `def` bigint(255) NOT NULL,
  `atk` bigint(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MsHeros`
--

INSERT INTO `MsHeros` (`herosID`, `heroName`, `roleID`, `abilitiesID`, `hp`, `def`, `atk`) VALUES
('Ass001', 'Gusion', 'Assasin', 'Ass001', 2580, 10, 179),
('Ass002', 'Lancelot', 'Assasin', 'Ass002', 2675, 15, 179),
('Ass003', 'Karina', 'Assasin', 'Ass03', 2625, 10, 170),
('Ass004', 'Selena', 'Assasin', 'Ass004', 2490, 10, 168),
('FR001', 'Aldous', 'Fighter', 'FR001', 2700, 20, 108),
('FR002', 'Leomord', 'Fighter', 'FR002', 2900, 20, 160),
('FR003', 'Minsitthar', 'Fighter', 'FR003', 2700, 25, 170),
('FR004', 'Zilong', 'Fighter', 'FR004', 2680, 18, 170),
('FR005', 'Chou', 'Fighter', 'FR005', 2670, 19, 157),
('FR006', 'Ruby', 'Fighter', 'FR006', 2620, 20, 160),
('FR007', 'Sun', 'Fighter', 'FR007', 2700, 22, 158),
('FR008', 'Freya', 'Fighter', 'FR008', 2750, 22, 170),
('FR009', 'X.Borg', 'Fighter', 'FR009', 2800, 25, 168),
('FR010', 'Bane', 'Fighter', 'FR010', 2750, 20, 160),
('FR011', 'Jawhead', 'Fighter', 'FR011', 2650, 20, 159),
('FR012', 'Badang', 'Fighter', 'FR012', 2750, 24, 176),
('FR013', 'Masha', 'Fighter', 'FR013', 3200, 22, 180),
('FR014', 'Martis', 'Fighter', 'FR014', 2680, 18, 170),
('ME001', 'Kagura', 'Mage', 'ME001', 2550, 15, 134),
('ME002', 'Lylia', 'Mage', 'ME002', 2500, 10, 120),
('ME003', 'Nana', 'Mage', 'ME003', 2600, 10, 120),
('ME005', 'Esmeralda', 'Mage', 'ME005', 2800, 25, 148),
('ME006', 'Xavier', 'Mage', 'ME006', 2600, 12, 140),
('ME007', 'Pharsa', 'Mage', 'ME007', 2580, 10, 135),
('ME008', 'Harith', 'Mage', 'ME008', 2550, 15, 158),
('ME009', 'Guinevere', 'Mage', 'ME009', 2580, 15, 160),
('ME010', 'Odette', 'Mage', 'ME010', 2450, 10, 130),
('MG004', 'Cecilion', 'Mage', 'ME004', 2760, 10, 139),
('MM001', 'Miya', 'Marksman', 'MM001', 2550, 18, 142),
('MM002', 'Clint', 'Marksman', 'MM002', 2550, 15, 155),
('MM003', 'Irithel', 'Marksman', 'MM003', 2550, 18, 155),
('MM004', 'Karrie', 'Marksman', 'MM004', 2400, 15, 160),
('MM005', 'Wanwan', 'Marksman', 'MM005', 2550, 17, 148),
('MM006', 'Beatrix', 'Marksman', 'MM006', 2600, 18, 171),
('MM007', 'Layla', 'Marksman', 'MM007', 2580, 15, 160),
('MM008', 'Moskov', 'Marksman', 'MM008', 2550, 18, 170),
('MM009', 'Granger', 'Marksman', 'MM009', 2550, 16, 170),
('MM010', 'Claude', 'Marksman', 'MM010', 2600, 15, 160),
('MM011', 'Bruno', 'Marksman', 'MM011', 2450, 15, 164),
('Sup001', 'Angela', 'Support', 'Sup001', 2500, 10, 108),
('Sup002', 'Rafaela', 'Support', 'Sup002', 2500, 12, 124),
('Sup003', 'Estes', 'Support', 'Sup003', 2650, 14, 126),
('Sup004', 'Faramis', 'Support', 'Sup004', 2400, 15, 130),
('Sup005', 'Floryn', 'Support', 'Sup005', 2650, 12, 107),
('TK001', 'Tigreal', 'Tank', 'TK001', 3000, 30, 135),
('TK002', 'Grock', 'Tank', 'TK002', 3150, 35, 155),
('TK003', 'Atlas', 'Tank', 'TK003', 2900, 30, 138),
('TK004', 'Hilda', 'Tank', 'TK004', 2800, 20, 172),
('TK005', 'Baxia', 'Tank', 'TK005', 3150, 25, 145),
('TK006', 'Johnson', 'Tank', 'TK006', 3100, 35, 131),
('TK007', 'Khufra', 'Tank', 'TK007', 2900, 30, 137),
('TK008', 'Franco', 'Tank', 'TK008', 2900, 30, 138),
('TK009', 'Minotaur', 'Tank', 'TK009', 2950, 30, 139),
('TK010', 'Kaja', 'Tank', 'TK010', 2600, 22, 141);

-- --------------------------------------------------------

--
-- Table structure for table `MsRole`
--

CREATE TABLE `MsRole` (
  `roleID` varchar(10) NOT NULL,
  `roleTitle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MsRole`
--

INSERT INTO `MsRole` (`roleID`, `roleTitle`) VALUES
('RO001', 'Marksman'),
('RO002', 'Mage'),
('RO003', 'Tank'),
('RO004', 'Fighter'),
('RO005', 'Assasin'),
('RO006', 'Support');

-- --------------------------------------------------------

--
-- Table structure for table `MustHave`
--

CREATE TABLE `MustHave` (
  `mustHave` varchar(10) NOT NULL,
  `CrowdControl` tinyint(1) NOT NULL,
  `Mobility` tinyint(1) NOT NULL,
  `AoE` tinyint(1) NOT NULL,
  `Blink` tinyint(1) NOT NULL,
  `Damage` tinyint(1) NOT NULL,
  `Heal` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MustHave`
--

INSERT INTO `MustHave` (`mustHave`, `CrowdControl`, `Mobility`, `AoE`, `Blink`, `Damage`, `Heal`) VALUES
('0', 0, 0, 0, 0, 0, 0),
('1001', 0, 0, 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `MsAbilities`
--
ALTER TABLE `MsAbilities`
  ADD PRIMARY KEY (`abilitiesID`);

--
-- Indexes for table `MsCounter`
--
ALTER TABLE `MsCounter`
  ADD PRIMARY KEY (`counterID`);

--
-- Indexes for table `MsHeros`
--
ALTER TABLE `MsHeros`
  ADD PRIMARY KEY (`herosID`),
  ADD UNIQUE KEY `abilitiesID` (`abilitiesID`);

--
-- Indexes for table `MsRole`
--
ALTER TABLE `MsRole`
  ADD PRIMARY KEY (`roleID`);

--
-- Indexes for table `MustHave`
--
ALTER TABLE `MustHave`
  ADD PRIMARY KEY (`mustHave`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `MsHeros`
--
ALTER TABLE `MsHeros`
  ADD CONSTRAINT `msheros_ibfk_1` FOREIGN KEY (`abilitiesID`) REFERENCES `MsAbilities` (`abilitiesID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
