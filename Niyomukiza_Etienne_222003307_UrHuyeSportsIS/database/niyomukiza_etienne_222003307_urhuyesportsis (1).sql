-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 09:20 AM
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
-- Database: `niyomukiza_etienne_222003307_urhuyesportsis`
--

-- --------------------------------------------------------

--
-- Table structure for table `athletes`
--

CREATE TABLE `athletes` (
  `Athlete_ID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Nationality` varchar(100) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Residence` varchar(255) DEFAULT NULL,
  `Team_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `athletes`
--

INSERT INTO `athletes` (`Athlete_ID`, `Name`, `Age`, `Gender`, `Nationality`, `Email`, `Phone`, `Residence`, `Team_ID`) VALUES
(2, 'amill', 75, 'Female', 'rwanda', 'amill@gmail.com', '74858484', 'huye', 1),
(3, 'Diaby', 25, 'Male', 'rwanda', 'diaby@gmail.com', '74858484', 'huye', 1);

-- --------------------------------------------------------

--
-- Table structure for table `coaches`
--

CREATE TABLE `coaches` (
  `Coach_ID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Specialization` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `coaches`
--

INSERT INTO `coaches` (`Coach_ID`, `Name`, `Specialization`, `Email`, `Phone`, `Address`) VALUES
(1, 'Msudi', 'Football', 'masudi@gmail.com', '87987989879', 'huye'),
(3, 'gael', 'Football', 'gael@gmail.com', '90965869', 'kigali'),
(4, 'Mahadi NGAYABATERANYA', 'Football', 'mahadi@gmail.com', '0789909988', 'Kigali'),
(5, 'Noelline KAMPIRE', 'Tennis', 'noelline@gmail.com', '078990934', 'Muhanga'),
(6, 'Masudi', 'Tennis', 'masudi@gmail.com', '078990911', 'Kikweba');

-- --------------------------------------------------------

--
-- Table structure for table `competitions`
--

CREATE TABLE `competitions` (
  `Competition_ID` int(11) NOT NULL,
  `Competition_title` varchar(255) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Venue` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `competitions`
--

INSERT INTO `competitions` (`Competition_ID`, `Competition_title`, `Date`, `Venue`) VALUES
(1, 'dfggg', '2002-09-09', 'huye');

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `Match_ID` int(11) NOT NULL,
  `Competition_ID` int(11) DEFAULT NULL,
  `Team1_ID` int(11) DEFAULT NULL,
  `Team2_ID` int(11) DEFAULT NULL,
  `Match_Date` date DEFAULT NULL,
  `Venue` varchar(255) DEFAULT NULL,
  `Referee_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `officials`
--

CREATE TABLE `officials` (
  `Referee_ID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Certification` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `officials`
--

INSERT INTO `officials` (`Referee_ID`, `Name`, `Email`, `Phone`, `Certification`) VALUES
(2, 'asa', 'asa@gmail.com', '9878787878', 'lA'),
(3, '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `Team_ID` int(11) NOT NULL,
  `Team_Name` varchar(255) DEFAULT NULL,
  `Coach_ID` int(11) DEFAULT NULL,
  `Training_Facility_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`Team_ID`, `Team_Name`, `Coach_ID`, `Training_Facility_ID`) VALUES
(1, 'HWFC', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `trainingfacilities`
--

CREATE TABLE `trainingfacilities` (
  `Facility_ID` int(11) NOT NULL,
  `Facility_Type` varchar(100) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Available_Equipment` varchar(255) DEFAULT NULL,
  `Available_Time` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trainingfacilities`
--

INSERT INTO `trainingfacilities` (`Facility_ID`, `Facility_Type`, `Location`, `Available_Equipment`, `Available_Time`) VALUES
(1, 'runing', 'huye PG', 'balls', '22:08:09'),
(3, 'dragging', 'huye', 'boots', '22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_ID` int(11) NOT NULL,
  `First_Name` varchar(255) DEFAULT NULL,
  `Last_Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_ID`, `First_Name`, `Last_Name`, `Email`, `Password`) VALUES
(1, 'kim', 'jaine', 'jaine@gmail.com', '12345678');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `athletes`
--
ALTER TABLE `athletes`
  ADD PRIMARY KEY (`Athlete_ID`),
  ADD KEY `Team_ID` (`Team_ID`);

--
-- Indexes for table `coaches`
--
ALTER TABLE `coaches`
  ADD PRIMARY KEY (`Coach_ID`);

--
-- Indexes for table `competitions`
--
ALTER TABLE `competitions`
  ADD PRIMARY KEY (`Competition_ID`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`Match_ID`),
  ADD KEY `Competition_ID` (`Competition_ID`),
  ADD KEY `Team1_ID` (`Team1_ID`),
  ADD KEY `Team2_ID` (`Team2_ID`),
  ADD KEY `Referee_ID` (`Referee_ID`);

--
-- Indexes for table `officials`
--
ALTER TABLE `officials`
  ADD PRIMARY KEY (`Referee_ID`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`Team_ID`),
  ADD KEY `Coach_ID` (`Coach_ID`),
  ADD KEY `Training_Facility_ID` (`Training_Facility_ID`);

--
-- Indexes for table `trainingfacilities`
--
ALTER TABLE `trainingfacilities`
  ADD PRIMARY KEY (`Facility_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `athletes`
--
ALTER TABLE `athletes`
  MODIFY `Athlete_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `coaches`
--
ALTER TABLE `coaches`
  MODIFY `Coach_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `competitions`
--
ALTER TABLE `competitions`
  MODIFY `Competition_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `Match_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `officials`
--
ALTER TABLE `officials`
  MODIFY `Referee_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `Team_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `trainingfacilities`
--
ALTER TABLE `trainingfacilities`
  MODIFY `Facility_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `athletes`
--
ALTER TABLE `athletes`
  ADD CONSTRAINT `athletes_ibfk_1` FOREIGN KEY (`Team_ID`) REFERENCES `teams` (`Team_ID`);

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`Competition_ID`) REFERENCES `competitions` (`Competition_ID`),
  ADD CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`Team1_ID`) REFERENCES `teams` (`Team_ID`),
  ADD CONSTRAINT `matches_ibfk_3` FOREIGN KEY (`Team2_ID`) REFERENCES `teams` (`Team_ID`),
  ADD CONSTRAINT `matches_ibfk_4` FOREIGN KEY (`Referee_ID`) REFERENCES `officials` (`Referee_ID`);

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `teams_ibfk_1` FOREIGN KEY (`Coach_ID`) REFERENCES `coaches` (`Coach_ID`),
  ADD CONSTRAINT `teams_ibfk_2` FOREIGN KEY (`Training_Facility_ID`) REFERENCES `trainingfacilities` (`Facility_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
