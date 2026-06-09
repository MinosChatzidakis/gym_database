-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 09, 2026 at 03:38 PM
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
-- Database: `gym_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `available_rewards`
--

CREATE TABLE `available_rewards` (
  `reward_ID` int(11) NOT NULL,
  `description` varchar(800) DEFAULT NULL,
  `pts_Required` int(11) DEFAULT NULL,
  `valid_For` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_ID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_ID`, `name`, `email`, `phone`, `gym_Gym_Code`) VALUES
(1, 'Γεωργια', 'georgia@gmail.com', '6946798803', 7515),
(67, 'Γιώργος Παπαδόπουλος', 'yorgos@gmail.com', '6948252404', 7515);

-- --------------------------------------------------------

--
-- Table structure for table `gym`
--

CREATE TABLE `gym` (
  `gym_Code` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `services` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gym`
--

INSERT INTO `gym` (`gym_Code`, `name`, `address`, `city`, `phone`, `email`, `services`) VALUES
(7515, 'Fitness Club', 'Mikras Asias 120', 'Athens', '2109616119', 'gym@gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_ID` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `payment_Method` varchar(50) NOT NULL,
  `payment_Date` varchar(20) NOT NULL,
  `payment_Status` varchar(50) NOT NULL,
  `reservation_Reservation_Code` int(20) DEFAULT NULL,
  `pts_Transactions_Trans_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pts_transactions`
--

CREATE TABLE `pts_transactions` (
  `trans_ID` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `description` varchar(800) DEFAULT NULL,
  `customer_Customer_ID` int(11) DEFAULT NULL,
  `payment_Payment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_Code` int(20) NOT NULL,
  `date_And_Time` varchar(20) NOT NULL,
  `invoice_Needed` tinyint(1) NOT NULL,
  `reservation_Status` varchar(30) NOT NULL,
  `session_Session_Code` int(11) NOT NULL,
  `customer_Customer_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rewards_distribution`
--

CREATE TABLE `rewards_distribution` (
  `distr_ID` int(11) NOT NULL,
  `available_Rewards_Reward_ID` int(11) DEFAULT NULL,
  `is_Used` tinyint(1) DEFAULT NULL,
  `date_Obtained` varchar(20) DEFAULT NULL,
  `date_Used` varchar(20) DEFAULT NULL,
  `valid_Until` varchar(20) DEFAULT NULL,
  `customer_Customer_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_Name` varchar(20) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `session_Code` int(11) NOT NULL,
  `session_Type` varchar(50) NOT NULL,
  `description` varchar(800) NOT NULL,
  `max_Participants` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `price` int(10) NOT NULL,
  `availability` tinyint(1) NOT NULL,
  `trainer_Trainer_ID` int(11) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL,
  `date_Αnd_Τime` varchar(20) NOT NULL,
  `amount_Of_Participants` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `trainer`
--

CREATE TABLE `trainer` (
  `trainer_ID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `specialty` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `available_rewards`
--
ALTER TABLE `available_rewards`
  ADD PRIMARY KEY (`reward_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_ID`),
  ADD KEY `Customer_GYM_FK` (`gym_Gym_Code`);

--
-- Indexes for table `gym`
--
ALTER TABLE `gym`
  ADD PRIMARY KEY (`gym_Code`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_ID`),
  ADD UNIQUE KEY `Payment__IDX` (`pts_Transactions_Trans_ID`),
  ADD UNIQUE KEY `Payment__IDXv1v1` (`reservation_Reservation_Code`);

--
-- Indexes for table `pts_transactions`
--
ALTER TABLE `pts_transactions`
  ADD PRIMARY KEY (`trans_ID`),
  ADD UNIQUE KEY `pts_transactions__IDX` (`payment_Payment_ID`),
  ADD KEY `pts_transactions_Customer_FK` (`customer_Customer_ID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_Code`),
  ADD KEY `Reservation_Customer_FK` (`customer_Customer_ID`),
  ADD KEY `Reservation_Session_FK` (`session_Session_Code`);

--
-- Indexes for table `rewards_distribution`
--
ALTER TABLE `rewards_distribution`
  ADD PRIMARY KEY (`distr_ID`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_Name`,`gym_Gym_Code`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`session_Code`);

--
-- Indexes for table `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`trainer_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `available_rewards`
--
ALTER TABLE `available_rewards`
  MODIFY `reward_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `gym`
--
ALTER TABLE `gym`
  MODIFY `gym_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7516;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pts_transactions`
--
ALTER TABLE `pts_transactions`
  MODIFY `trans_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_Code` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rewards_distribution`
--
ALTER TABLE `rewards_distribution`
  MODIFY `distr_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `session_Code` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trainer`
--
ALTER TABLE `trainer`
  MODIFY `trainer_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `Customer_GYM_FK` FOREIGN KEY (`gym_Gym_Code`) REFERENCES `gym` (`gym_Code`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `Payment_Reservation_FK` FOREIGN KEY (`reservation_Reservation_Code`) REFERENCES `reservation` (`Reservation_code`),
  ADD CONSTRAINT `Payment_pts_transactions_FK` FOREIGN KEY (`pts_Transactions_Trans_ID`) REFERENCES `pts_transactions` (`trans_id`);

--
-- Constraints for table `pts_transactions`
--
ALTER TABLE `pts_transactions`
  ADD CONSTRAINT `pts_transactions_Customer_FK` FOREIGN KEY (`customer_Customer_ID`) REFERENCES `customer` (`customer_ID`),
  ADD CONSTRAINT `pts_transactions_Payment_FK` FOREIGN KEY (`payment_Payment_ID`) REFERENCES `payment` (`payment_ID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `Reservation_Customer_FK` FOREIGN KEY (`Customer_Customer_ID`) REFERENCES `customer` (`customer_ID`),
  ADD CONSTRAINT `Reservation_Session_FK` FOREIGN KEY (`Session_Session_Code`) REFERENCES `session` (`Session_Code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
