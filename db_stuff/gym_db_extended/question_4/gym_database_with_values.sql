-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 09, 2026 at 05:05 PM
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

--
-- Dumping data for table `available_rewards`
--

INSERT INTO `available_rewards` (`reward_ID`, `description`, `pts_Required`, `valid_For`) VALUES
(1, 'Δωρεάν Πρωτεϊνικό Shaker', 50, 30),
(2, 'Μία Δωρεάν Προπόνηση CrossFit', 80, 15),
(3, 'Πετσέτα Μικροϊνών με το Logo του Γυμναστηρίου', 100, 45),
(4, 'Ένα Δωρεάν Μάθημα Personal Training', 150, 30),
(5, 'Μηνιαία Έκπτωση 20% στη Συνδρομή', 250, 60),
(6, 'Λιπομέτρηση και Πλάνο Διατροφής', 300, 30),
(7, 'Αθλητικό T-shirt του Γυμναστηρίου', 350, 90),
(8, 'Δωρεάν 1 Μήνας Συνδρομής', 600, 90),
(9, '1 Σετ Ιμάντες Suspension (Τύπου TRX)', 1000, 120),
(10, 'Δωρεάν Ετήσια Συνδρομή', 5000, 365);

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
(68, 'Νίκος Γεωργίου', 'nikos.g@email.com', '6971234567', 7516),
(69, 'Άννα Παππά', 'anna.pappa@email.com', '6989876543', 7516),
(70, 'Κώστας Νικολάου', 'k.nikolaou@email.com', '6945556677', 7516),
(71, 'Ελένη Μήτρου', 'eleni.m@email.com', '6945556677', 7517),
(72, 'Ελένη Μήτρου', 'eleni.m@email.com', '6932223344', 7520),
(73, 'Δημήτρης Αναστασίου', 'dim.anast@email.com', '6977778899', 7522),
(74, 'Κατερίνα Λύκου', 'kat.lykou@email.com', '6981112233', 7521),
(75, 'Γιάννης Στεργίου', 'giannis.st@email.com', '6944445566', 7517),
(76, 'Σοφία Καλλέργη', 'sofia.kal@email.com', '6939998877', 7522),
(77, 'Πέτρος Οικονόμου', 'petros.oik@email.com', '6976665544', 7524),
(78, 'Μαρία Αλεξίου', 'maria.alex@email.com', '6983334455', 7523);

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
(7515, 'Fitness Club', 'Mikras Asias 120', 'Athens', '2109616119', 'gym@gmail.com', 'Cardio, Weights, Classes'),
(7516, 'Titan Fitness', 'Λεωφ. Βουλιαγμένης 15', 'Γλυφάδα', '2101234567', 'info@titanfitness.gr', 'Weights, Cardio, TRX'),
(7517, 'Olympus Gym', 'Τσιμισκή 40', 'Θεσσαλονίκη', '2310987654', 'contact@olympus.gr', 'CrossFit, Pool, Sauna'),
(7518, 'Spartan Box', 'Ηρώων Πολυτεχνείου 10', 'Πειραιάς', '2104567890', 'hello@spartanbox.gr', 'CrossFit, TRX, Weights'),
(7519, 'Athina Health Club', 'Ερμού 50', 'Αθήνα', '2101122334', 'athens@healthclub.gr', 'Pilates, Yoga, Spa'),
(7520, 'Iron Core', 'Κηφισίας 120', 'Μαρούσι', '2109988776', 'info@ironcore.gr', 'Bodybuilding, PT'),
(7521, 'Flex Studio', 'Κουντουριώτου 5', 'Πάτρα', '2610112233', 'flex@studio.gr', 'Aerobics, Zumba, Yoga'),
(7522, 'Aqua Fit', 'Ποσειδώνος 80', 'Παλαιό Φάληρο', '2105544332', 'aqua@fit.gr', 'Pool, Aqua Aerobics'),
(7523, 'Powerhouse', 'Βενιζέλου 15', 'Λάρισα', '2410667788', 'power@house.gr', 'Powerlifting, Weights'),
(7524, 'Zen Wellness', 'Παπάγου 22', 'Χαλάνδρι', '2108877665', 'zen@wellness.gr', 'Yoga, Massage, Pilates');

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

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_ID`, `amount`, `payment_Method`, `payment_Date`, `payment_Status`, `reservation_Reservation_Code`, `pts_Transactions_Trans_ID`) VALUES
(21, 15, 'Credit Card', '15/06/2026', 'Completed', 1, 1),
(22, 20, 'Cash', '15/06/2026', 'Completed', 2, 2),
(23, 10, 'PayPal', '16/06/2026', 'Pending', 3, 3),
(24, 30, 'Credit Card', '16/06/2026', 'Completed', 4, 4),
(25, 12, 'Cash', '16/06/2026', 'Completed', 5, 5),
(26, 15, 'Bank Transfer', '17/06/2026', 'Completed', 6, 6),
(27, 8, 'Credit Card', '17/06/2026', 'Failed', 7, 7),
(28, 15, 'Cash', '18/06/2026', 'Completed', 8, 8),
(29, 20, 'PayPal', '18/06/2026', 'Completed', 9, 9),
(30, 10, 'Credit Card', '19/06/2026', 'Pending', 10, 10);

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
  `payment_Payment_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pts_transactions`
--

INSERT INTO `pts_transactions` (`trans_ID`, `amount`, `source`, `date`, `description`, `customer_Customer_ID`, `payment_Payment_ID`) VALUES
(21, 50, 'Subscription', '15/06/2026', 'Πόντοι από μηνιαία συνδρομή', 68, NULL),
(22, 20, 'Class Booking', '15/06/2026', 'Κράτηση μαθήματος CrossFit', 69, NULL),
(23, 10, 'Class Booking', '16/06/2026', 'Κράτηση μαθήματος Yoga', 70, NULL),
(24, 100, 'Promo', '16/06/2026', 'Bonus εγγραφής νέου μέλους', 71, NULL),
(25, 15, 'Class Booking', '16/06/2026', 'Κράτηση μαθήματος Pilates', 72, NULL),
(26, 50, 'Subscription', '17/06/2026', 'Ανανέωση συνδρομής', 73, NULL),
(27, 10, 'Class Booking', '17/06/2026', 'Κράτηση μαθήματος TRX', 74, NULL),
(28, 30, 'Referral', '18/06/2026', 'Πόντοι από προσθήκη φίλου', 75, NULL),
(29, 20, 'Class Booking', '18/06/2026', 'Κράτηση μαθήματος Zumba', 76, NULL),
(30, 15, 'Class Booking', '19/06/2026', 'Κράτηση μαθήματος Powerlifting', 77, NULL);

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

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_Code`, `date_And_Time`, `invoice_Needed`, `reservation_Status`, `session_Session_Code`, `customer_Customer_ID`) VALUES
(1, '14/06/2026 10:30', 0, 'Confirmed', 1, 68),
(2, '14/06/2026 11:15', 1, 'Confirmed', 2, 69),
(3, '14/06/2026 15:00', 0, 'Pending', 3, 70),
(4, '15/06/2026 09:20', 0, 'Confirmed', 4, 71),
(5, '15/06/2026 12:00', 1, 'Cancelled', 5, 72),
(6, '15/06/2026 17:45', 0, 'Confirmed', 6, 73),
(7, '16/06/2026 08:30', 0, 'Confirmed', 7, 74),
(8, '16/06/2026 14:10', 0, 'Pending', 8, 75),
(9, '16/06/2026 19:00', 1, 'Confirmed', 9, 76),
(10, '17/06/2026 11:00', 0, 'Confirmed', 10, 77);

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

--
-- Dumping data for table `rewards_distribution`
--

INSERT INTO `rewards_distribution` (`distr_ID`, `available_Rewards_Reward_ID`, `is_Used`, `date_Obtained`, `date_Used`, `valid_Until`, `customer_Customer_ID`) VALUES
(1, 1, 1, '10/05/2026', '12/05/2026', '09/06/2026', 68),
(2, 2, 0, '01/06/2026', NULL, '16/06/2026', 68),
(3, 3, 1, '15/04/2026', '20/04/2026', '30/05/2026', 69),
(4, 4, 0, '05/06/2026', NULL, '05/07/2026', 70),
(5, 5, 0, '08/06/2026', NULL, '07/08/2026', 71),
(6, 6, 1, '02/05/2026', '10/05/2026', '01/06/2026', 72),
(7, 1, 0, '09/06/2026', NULL, '09/07/2026', 73),
(8, 7, 0, '01/06/2026', NULL, '30/08/2026', 74),
(9, 8, 1, '01/03/2026', '01/03/2026', '30/05/2026', 75),
(10, 2, 0, '07/06/2026', NULL, '22/06/2026', 76);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_Name` varchar(20) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_Name`, `gym_Gym_Code`) VALUES
('Cardio', 7516),
('CrossFit', 7516),
('Pilates', 7516),
('Pilates', 7518),
('Pool', 7518),
('Sauna', 7517),
('TRX', 7517),
('Weights', 7516),
('Yoga', 7517),
('Zumba', 7518);

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `session_Code` int(11) NOT NULL,
  `session_Type` varchar(50) NOT NULL,
  `description` varchar(800) NOT NULL,
  `max_Participants` int(11) NOT NULL,
  `duration` int(20) NOT NULL,
  `price` int(10) NOT NULL,
  `availability` tinyint(1) NOT NULL,
  `trainer_Trainer_ID` int(11) NOT NULL,
  `gym_Gym_Code` int(11) NOT NULL,
  `date_Αnd_Τime` varchar(20) NOT NULL,
  `amount_Of_Participants` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`session_Code`, `session_Type`, `description`, `max_Participants`, `duration`, `price`, `availability`, `trainer_Trainer_ID`, `gym_Gym_Code`, `date_Αnd_Τime`, `amount_Of_Participants`) VALUES
(1, 'Yoga', 'Πρωινή χαλάρωση και ευεξία', 15, 60, 10, 1, 4, 7522, '15/06/2026 09:00', 5),
(2, 'CrossFit', 'Έντονη διαλειμματική προπόνηση', 10, 45, 15, 1, 3, 7521, '15/06/2026 18:00', 8),
(3, 'Pilates', 'Ενδυνάμωση κορμού', 12, 50, 12, 0, 1, 7521, '16/06/2026 10:00', 12),
(4, 'Weightlifting', 'Ολυμπιακή άρση βαρών', 8, 90, 20, 1, 1, 7519, '16/06/2026 17:00', 4),
(5, 'Bodybuilding', 'Μυϊκή υπερτροφία με βάρη', 5, 60, 15, 1, 10, 7521, '18/06/2026 20:00', 2),
(6, 'TRX', 'Προπόνηση με ιμάντες', 10, 45, 10, 1, 4, 7517, '15/06/2026 19:00', 9),
(7, 'Personal Training', 'Ατομικό πρόγραμμα ενδυνάμωσης', 1, 60, 30, 1, 5, 7521, '17/06/2026 11:00', 0),
(8, 'Aerobics', 'Αερόβια προπόνηση για όλους', 20, 45, 8, 1, 5, 7521, '16/06/2026 18:00', 2),
(9, 'Powerlifting', 'Ανάπτυξη μέγιστης δύναμης', 6, 90, 20, 0, 9, 7519, '18/06/2026 16:00', 12),
(10, 'Zumba', 'Χορός και γυμναστική', 25, 50, 8, 1, 5, 7520, '17/06/2026 19:30', 5);

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
-- Dumping data for table `trainer`
--

INSERT INTO `trainer` (`trainer_ID`, `name`, `specialty`, `phone`, `email`, `gym_Gym_Code`) VALUES
(1, 'Αλέξανδρος Ρήγας', 'Weightlifting', '6912345678', 'alex.r@email.com', 7515),
(2, 'Μαρία Κωνσταντίνου', 'Pilates', '6923456789', 'maria.k@email.com', 7516),
(3, 'Κώστας Ιωάννου', 'CrossFit', '6934567890', 'kostas.i@email.com', 7517),
(4, 'Ελένη Σταύρου', 'Yoga', '6945678901', 'eleni.st@email.com', 7519),
(5, 'Δημήτρης Παππάς', 'Bodybuilding', '6956789012', 'dim.pappas@email.com', 7520),
(6, 'Σοφία Νικολάου', 'TRX', '6967890123', 'sofia.n@email.com', 7519),
(7, 'Νίκος Ανδρέου', 'Personal Training', '6978901234', 'nikos.a@email.com', 7520),
(8, 'Κατερίνα Μιχαήλ', 'Aerobics', '6989012345', 'kat.m@email.com', 7515),
(9, 'Γιώργος Λάμπρου', 'Powerlifting', '6990123456', 'george.l@email.com', 7522),
(10, 'Άννα Γεωργίου', 'Zumba', '6901234567', 'anna.g@email.com', 7515);

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
  ADD PRIMARY KEY (`session_Code`),
  ADD KEY `Session_Trainer_FK` (`trainer_Trainer_ID`),
  ADD KEY `Session_GYM_FK` (`gym_Gym_Code`);

--
-- Indexes for table `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`trainer_ID`),
  ADD KEY `Trainer_GYM_FK` (`gym_Gym_Code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `available_rewards`
--
ALTER TABLE `available_rewards`
  MODIFY `reward_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `gym`
--
ALTER TABLE `gym`
  MODIFY `gym_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7525;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `pts_transactions`
--
ALTER TABLE `pts_transactions`
  MODIFY `trans_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_Code` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `rewards_distribution`
--
ALTER TABLE `rewards_distribution`
  MODIFY `distr_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `session_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `trainer`
--
ALTER TABLE `trainer`
  MODIFY `trainer_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
  ADD CONSTRAINT `pts_transactions_Customer_FK` FOREIGN KEY (`customer_Customer_ID`) REFERENCES `Customer` (`customer_ID`),
  ADD CONSTRAINT `pts_transactions_Payment_FK` FOREIGN KEY (`payment_Payment_ID`) REFERENCES `Payment` (`payment_ID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `Reservation_Customer_FK` FOREIGN KEY (`Customer_Customer_ID`) REFERENCES `customer` (`customer_ID`),
  ADD CONSTRAINT `Reservation_Session_FK` FOREIGN KEY (`Session_Session_Code`) REFERENCES `session` (`session_Code`);

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `Session_GYM_FK` FOREIGN KEY (`gym_Gym_Code`) REFERENCES `gym` (`gym_Code`),
  ADD CONSTRAINT `Session_Trainer_FK` FOREIGN KEY (`trainer_Trainer_ID`) REFERENCES `trainer` (`trainer_ID`);

--
-- Constraints for table `trainer`
--
ALTER TABLE `trainer`
  ADD CONSTRAINT `Trainer_GYM_FK` FOREIGN KEY (`gym_Gym_Code`) REFERENCES `gym` (`gym_Code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
