-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chungwoo_db
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_tbl`
--

DROP TABLE IF EXISTS `user_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tbl` (
  `id` varchar(20) NOT NULL,
  `pw` varchar(60) NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `branch` varchar(15) NOT NULL,
  `position` varchar(15) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'MEMBER',
  `authorization` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `user_tbl_chk_1` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_2` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_3` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_4` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_5` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_6` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_7` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_8` CHECK ((`authorization` in (0,1))),
  CONSTRAINT `user_tbl_chk_9` CHECK ((`authorization` in (0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tbl`
--

LOCK TABLES `user_tbl` WRITE;
/*!40000 ALTER TABLE `user_tbl` DISABLE KEYS */;
INSERT INTO `user_tbl` VALUES ('a','$2a$10$suuJl5e0kVNEm25iAD5DEuQbggAYPttV63cQ9wIfHFKB6MkWdHLYO','a','123123123','a@a','대구본점','asd','MEMBER',1),('asdfasdfasdf','$2a$10$zh9Ghjnf7iI5VVnJy7I/q.7O2U5X9stLjcefraSi/8lsE0K6sdxSi','member','010-1234-1234','a@s','대구본점','이사','MEMBER',1),('jhh591232392','$2a$10$eWsakN2Csp7oJ1.vW4kf5.jYMXx2UldgtEAMqNl0Ad2zGg4gyHRr2','testtest','010-1234-1234','asdfjklsjldsfn9099@naver.com','대구본점','이사','MEMBER',1),('t0','$2a$10$f9V.5CYumfpUC7mTXEZt9.K1mzGpjrMIQISG2OW6fkFDtl/bsRdBa','t0','010-1234-1234','asd@asd','대구본점','이사','MEMBER',0),('user','$2a$10$b/aRwodGUedirAhC70QlseYnlQmkPE8PDn7rmIP1Oo64iLNeiLiTW','user1','','','','','ADMIN',1);
/*!40000 ALTER TABLE `user_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 18:36:58
