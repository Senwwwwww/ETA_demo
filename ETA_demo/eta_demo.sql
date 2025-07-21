-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: eta_demo
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `application_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `application_type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `application_reason` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `application_status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `application_is_delete` smallint DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `FK_Relationship_9` (`user_id`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (523791261,1578538650,'病假申请','头疼','2024-08-23 16:00:00','2024-08-24 16:00:00','待审批',0),(549385953,1578538650,'事假申请','家里有事','2024-08-22 16:00:00','2024-08-24 16:00:00','通过',0),(988317382,1578538650,'加班申请','加班','2024-08-25 12:00:00','2024-08-25 13:00:00','待审批',0),(1130649936,1578538650,'病假申请','头疼','2024-08-23 16:00:00','2024-08-24 16:00:00','待审批',0),(1140673037,1578538650,'病假申请','头疼','2024-08-23 16:00:00','2024-08-24 16:00:00','待审批',0),(1235343929,1578538650,'病假申请','头疼','2024-08-23 16:00:00','2024-08-24 16:00:00','通过',0),(1929842200,1578538650,'事假申请','家里有事','2024-08-22 16:00:00','2024-08-24 16:00:00','拒绝',0);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_information`
--

DROP TABLE IF EXISTS `attendance_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_information` (
  `Attendance_info_id` bigint NOT NULL,
  `Clock_in_date` date NOT NULL,
  `clock_in_start_time` time NOT NULL,
  `Clock_in_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `clock_in_end_time` time DEFAULT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`Attendance_info_id`),
  KEY `FK_attendanceInfo_employee` (`employee_id`),
  CONSTRAINT `FK_attendanceInfo_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_information`
--

LOCK TABLES `attendance_information` WRITE;
/*!40000 ALTER TABLE `attendance_information` DISABLE KEYS */;
INSERT INTO `attendance_information` VALUES (1,'2024-08-24','08:10:33','成功打卡','17:11:06',10000000000),(2,'2024-08-25','08:11:54','已打卡未签退','16:12:43',10000000000),(3,'2024-08-26','09:13:57','迟到','17:14:24',10000000000),(4,'2024-07-20','08:26:33','成功打卡','17:26:47',10000000000),(202499475,'2024-08-24','17:05:38','缺勤','17:14:33',10000000001),(1127324074,'2024-08-24','17:12:13','缺勤','17:12:31',10000000005),(1487494313,'2024-08-24','17:09:33','缺勤','17:10:25',10000000004);
/*!40000 ALTER TABLE `attendance_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bioinformatics`
--

DROP TABLE IF EXISTS `bioinformatics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bioinformatics` (
  `bioinfo_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `Fingerprint_number` varchar(100) DEFAULT NULL,
  `Face_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bioinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bioinformatics`
--

LOCK TABLES `bioinformatics` WRITE;
/*!40000 ALTER TABLE `bioinformatics` DISABLE KEYS */;
/*!40000 ALTER TABLE `bioinformatics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` bigint NOT NULL,
  `department_name` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `department_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `department_is_delete` smallint NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (0,'其他','其他',0),(1,'软件开发','负责前后端软件开发',0),(2,'硬件开发','负责硬件开发',0),(3,'系统运维','负责系统的维护',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL,
  `real_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `employee_is_delete` smallint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (10000000000,'老板',0,569676674),(10000000001,'李四',0,NULL),(10000000002,'张三',0,1578538650),(10000000003,'李四五',0,NULL),(10000000004,'苏绮雯',0,1785191953),(10000000005,'张驰',0,641285627),(10000000006,'裴胡洁',0,1512202260),(10000000007,'郝舒森',0,2013611661);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `feedback_type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `feedback_time` timestamp NOT NULL,
  `feedback_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `feedback_is_delete` smallint NOT NULL,
  `feedback_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `FK_user_feedback` (`user_id`),
  CONSTRAINT `FK_user_feedback` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (7,1578538650,'功能问题','2024-08-24 07:06:25','好',0,'处理中');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `message_time` timestamp NOT NULL,
  `message_content` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `message_status` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK_user_message` (`user_id`),
  CONSTRAINT `FK_user_message` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (964845864,1578538650,'2024-08-24 06:50:56','您的关于事假申请的申请已经通过！','已读'),(1127005260,1578538650,'2024-08-24 06:51:31','您的关于事假申请的申请已被拒绝，请重新申请！','已读'),(1503485041,1578538650,'2024-08-24 06:57:08','您的关于病假申请的申请已经通过！','已读');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_information`
--

DROP TABLE IF EXISTS `personal_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_information` (
  `Personal_Info_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `person_is_delete` smallint NOT NULL,
  `head_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Personal_Info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_information`
--

LOCK TABLES `personal_information` WRITE;
/*!40000 ALTER TABLE `personal_information` DISABLE KEYS */;
INSERT INTO `personal_information` VALUES (80565323,2013611661,'男','2004-08-06',0,NULL),(604853670,1512202260,'女','1999-06-22',0,NULL),(1124470336,641285627,'男','1960-02-17',0,NULL),(1285330262,1785191953,'女','2004-05-06',0,NULL),(1607337583,1578538650,'男','2000-07-10',0,NULL),(1836502130,569676674,'男','2001-10-24',0,NULL);
/*!40000 ALTER TABLE `personal_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `role_level` int NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'user',10),(1,'employee',100),(2,'admin',1000);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `bioinfo_id` bigint DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `Personal_Info_id` bigint DEFAULT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_is_delete` smallint NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_bioinfo` (`bioinfo_id`),
  KEY `FK_user_department` (`department_id`),
  KEY `FK_user_personal` (`Personal_Info_id`),
  KEY `FK_user_role` (`role_id`),
  KEY `FK_user_employee` (`employee_id`),
  CONSTRAINT `FK_user_bioinfo` FOREIGN KEY (`bioinfo_id`) REFERENCES `bioinformatics` (`bioinfo_id`),
  CONSTRAINT `FK_user_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `FK_user_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FK_user_personal` FOREIGN KEY (`Personal_Info_id`) REFERENCES `personal_information` (`Personal_Info_id`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (569676674,NULL,0,2,1836502130,'08d29c6c9c794dd4a806277091ac852a','ETA_admin','2427814198@qq.com','admin',0,10000000000),(641285627,NULL,3,0,1124470336,'28e70f5bc0a0c98a3a7754465630169f','zc666666','3273447162@qq.com','张驰',0,10000000005),(1512202260,NULL,0,0,604853670,'db825ca86f09360cb72aa9c8ca327379','123456','2523552792@qq.com','Aa',0,10000000006),(1578538650,NULL,3,0,1607337583,'db825ca86f09360cb72aa9c8ca327379','123456789','2523552792@qq.com','Bb',0,10000000002),(1785191953,NULL,0,0,1285330262,'7bed1b2550744ba1faa9df212c23f5b9','supass','2174656528@qq.com','1个萝卜',0,10000000004),(2013611661,NULL,1,0,80565323,'bc100fc0d9d925bd774b574696a56c1a','2427814198','2427814198@qq.com','sen',0,10000000007),(2091917586,NULL,0,0,NULL,'8335fca692d2992393a1276327514cda','1234567890','2523552792@qq.com','Cc',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-24 18:06:46
