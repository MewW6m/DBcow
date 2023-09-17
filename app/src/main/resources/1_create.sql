-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: dbcow
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.22.04.2

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
-- Table structure for table `connect`
--

DROP TABLE IF EXISTS `connect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connect` (
  `id` int NOT NULL,
  `conname` varchar(60) NOT NULL,
  `connectstring` varchar(255) NOT NULL,
  `createDate` date NOT NULL,
  `datadeleteflag` bit(1) NOT NULL,
  `dataregistflag` bit(1) NOT NULL,
  `dataupdateflag` bit(1) NOT NULL,
  `dbtype` int NOT NULL,
  `deleteFlag` bit(1) NOT NULL DEFAULT b'0',
  `host` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `status` int NOT NULL,
  `updateDate` date NOT NULL,
  `user` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1gvxqeseguxvqdpb5x7r8jyqx` (`conname`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `connect_id_seq`
--

DROP TABLE IF EXISTS `connect_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connect_id_seq` (
  `next_val` bigint DEFAULT NULL
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `id` int NOT NULL,
  `candidate` varchar(200) DEFAULT NULL,
  `createDate` date NOT NULL,
  `deleteFlag` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(200) NOT NULL,
  `type` varchar(60) NOT NULL,
  `updateDate` date NOT NULL,
  `username` varchar(60) NOT NULL,
  `value` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setting_id_seq`
--

DROP TABLE IF EXISTS `setting_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting_id_seq` (
  `next_val` bigint DEFAULT NULL
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `createDate` date NOT NULL,
  `deleteFlag` bit(1) NOT NULL DEFAULT b'0',
  `updateDate` date NOT NULL,
  `password` varchar(60) NOT NULL,
  `roles` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_user_id_seq`
--

DROP TABLE IF EXISTS `user_user_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user_id_seq` (
  `next_val` bigint DEFAULT NULL
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'dbcow'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-29 18:53:25
