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
-- Table structure for table `board_tbl`
--

DROP TABLE IF EXISTS `board_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_tbl` (
  `no` int NOT NULL AUTO_INCREMENT,
  `boardType` varchar(10) NOT NULL,
  `id` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `contents` text NOT NULL,
  `fileAddr` varchar(100) DEFAULT NULL,
  `writeDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no`),
  KEY `fk_board_type_idx` (`boardType`),
  KEY `fk_id_idx` (`id`),
  CONSTRAINT `fk_board_type` FOREIGN KEY (`boardType`) REFERENCES `board_type_tbl` (`boardType`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id` FOREIGN KEY (`id`) REFERENCES `user_tbl` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_tbl`
--

LOCK TABLES `board_tbl` WRITE;
/*!40000 ALTER TABLE `board_tbl` DISABLE KEYS */;
INSERT INTO `board_tbl` VALUES (27,'공지사항','user','test1','<p>Hello World!</p><p>Some initial <strong>bold</strong> texttest</p><p><br></p>','C:/asdfasdf','2023-05-03 21:43:07'),(28,'공지사항','user','test2','<p>Hello World!</p><p>Some initial <strong>bold</strong> textteste</p><p><br></p>','C:/asdfasdf','2023-05-03 21:43:14'),(29,'공지사항','user','asdf','<p>Hello World!asdf</p><p>Some initial <strong>bold</strong> text</p><p><br></p>','C:/asdfasdf','2023-05-04 18:48:51'),(30,'공지사항','user','ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹ','<p>Hello World!</p><p><span class=\"ql-size-large\">Some initial </span><strong class=\"ql-size-large\">bold</strong><span class=\"ql-size-large\"> text</span></p><p><span class=\"ql-size-large\">ㅁㄴㅇㄻㄴㅇㄹ</span></p><p><br></p><h1><strong class=\"ql-size-large\" style=\"background-color: rgb(230, 0, 0); color: rgb(255, 255, 255);\">제목<span class=\"ql-cursor\">﻿</span></strong></h1>','C:/asdfasdf','2023-05-04 21:35:11'),(31,'공지사항','user','ㅇㄴㅀㄴㅇㅀ','<p>Hello World!</p><p>Some initial <strong>bold</strong> text</p><p>ㅁㄴㅇㄻ</p><p><br></p><p>ㅁㄴㅇㄹ</p><p>ㅁㅇㄴㄹ</p><p><br></p><p><br></p><p>ㅇㅁㄴㄹ</p><p>ㅁㄴㅇㄹ</p><p>ㅁㄴㅇㄹ</p><p><br></p><h1 class=\"ql-align-center\"><span style=\"color: rgb(230, 0, 0);\">ㅁㄴㅇㄹ</span></h1>','C:/asdfasdf','2023-05-04 21:40:26'),(32,'공지사항','user','1','','C:/asdfasdf','2023-05-08 20:14:37'),(33,'공지사항','user','2','','C:/asdfasdf','2023-05-08 20:14:43'),(34,'공지사항','user','3 ','','C:/asdfasdf','2023-05-08 20:14:47'),(35,'공지사항','user','4 ','','C:/asdfasdf','2023-05-08 20:14:51'),(36,'공지사항','user','5','','C:/asdfasdf','2023-05-08 20:14:54'),(37,'공지사항','user','6','','C:/asdfasdf','2023-05-08 20:14:57'),(38,'공지사항','user','7','','C:/asdfasdf','2023-05-08 20:15:01'),(39,'공지사항','user','8  ','','C:/asdfasdf','2023-05-08 20:15:05'),(40,'공지사항','user','9','','C:/asdfasdf','2023-05-08 20:15:09'),(41,'공지사항','user','10','','C:/asdfasdf','2023-05-08 20:15:12'),(42,'공지사항','user','11','','C:/asdfasdf','2023-05-08 20:15:16'),(43,'공지사항','user','12','','C:/asdfasdf','2023-05-08 20:15:18'),(44,'공지사항','user','13','','C:/asdfasdf','2023-05-08 20:15:21'),(45,'공지사항','user','14','','C:/asdfasdf','2023-05-08 20:15:23'),(46,'공지사항','user','15','','C:/asdfasdf','2023-05-08 20:15:25'),(47,'공지사항','user','16','','C:/asdfasdf','2023-05-08 20:15:28'),(48,'공지사항','user','17','','C:/asdfasdf','2023-05-08 20:15:31'),(49,'공지사항','user','18','','C:/asdfasdf','2023-05-08 20:15:34'),(50,'공지사항','user','19','','C:/asdfasdf','2023-05-08 20:15:40'),(51,'공지사항','user','20','','C:/asdfasdf','2023-05-08 20:15:42'),(52,'공지사항','user','asd','','C:/asdfasdf','2023-05-09 19:46:47'),(53,'공지사항','user','test','','C:/asdfasdf','2023-05-09 19:49:49'),(54,'공지사항','user','test0','','C:/asdfasdf','2023-05-09 19:49:49'),(55,'공지사항','user','test1','','C:/asdfasdf','2023-05-09 19:49:49'),(56,'공지사항','user','test2','','C:/asdfasdf','2023-05-09 19:49:49'),(57,'공지사항','user','test3','','C:/asdfasdf','2023-05-09 19:49:49'),(58,'공지사항','user','test4','','C:/asdfasdf','2023-05-09 19:49:49'),(59,'공지사항','user','test5','','C:/asdfasdf','2023-05-09 19:49:49'),(60,'공지사항','user','test6','','C:/asdfasdf','2023-05-09 19:49:49'),(61,'공지사항','user','test7','','C:/asdfasdf','2023-05-09 19:49:49'),(62,'공지사항','user','test8','','C:/asdfasdf','2023-05-09 19:49:49'),(63,'공지사항','user','test9','','C:/asdfasdf','2023-05-09 19:49:49'),(64,'공지사항','user','test10','','C:/asdfasdf','2023-05-09 19:49:49'),(65,'공지사항','user','test11','','C:/asdfasdf','2023-05-09 19:49:49'),(66,'공지사항','user','test12','','C:/asdfasdf','2023-05-09 19:49:49'),(67,'공지사항','user','test13','','C:/asdfasdf','2023-05-09 19:49:49'),(68,'공지사항','user','test14','','C:/asdfasdf','2023-05-09 19:49:49'),(69,'공지사항','user','test15','','C:/asdfasdf','2023-05-09 19:49:49'),(70,'공지사항','user','test16','','C:/asdfasdf','2023-05-09 19:49:49'),(71,'공지사항','user','test17','','C:/asdfasdf','2023-05-09 19:49:49'),(72,'공지사항','user','test18','','C:/asdfasdf','2023-05-09 19:49:49'),(73,'공지사항','user','test19','','C:/asdfasdf','2023-05-09 19:49:49'),(74,'공지사항','user','test20','','C:/asdfasdf','2023-05-09 19:49:49'),(75,'공지사항','user','test21','','C:/asdfasdf','2023-05-09 19:49:49'),(76,'공지사항','user','test22','','C:/asdfasdf','2023-05-09 19:49:49'),(77,'공지사항','user','test23','','C:/asdfasdf','2023-05-09 19:49:49'),(78,'공지사항','user','test24','','C:/asdfasdf','2023-05-09 19:49:49'),(79,'공지사항','user','test25','','C:/asdfasdf','2023-05-09 19:49:49'),(80,'공지사항','user','test26','','C:/asdfasdf','2023-05-09 19:49:49'),(81,'공지사항','user','test27','','C:/asdfasdf','2023-05-09 19:49:49'),(82,'공지사항','user','test28','','C:/asdfasdf','2023-05-09 19:49:49'),(83,'공지사항','user','test29','','C:/asdfasdf','2023-05-09 19:49:49'),(84,'공지사항','user','test30','','C:/asdfasdf','2023-05-09 19:49:49'),(85,'공지사항','user','test31','','C:/asdfasdf','2023-05-09 19:49:49'),(86,'공지사항','user','test32','','C:/asdfasdf','2023-05-09 19:49:49'),(87,'공지사항','user','test33','','C:/asdfasdf','2023-05-09 19:49:49'),(88,'공지사항','user','test34','','C:/asdfasdf','2023-05-09 19:49:49'),(89,'공지사항','user','test35','','C:/asdfasdf','2023-05-09 19:49:49'),(90,'공지사항','user','test36','','C:/asdfasdf','2023-05-09 19:49:49'),(91,'공지사항','user','test37','','C:/asdfasdf','2023-05-09 19:49:49'),(92,'공지사항','user','test38','','C:/asdfasdf','2023-05-09 19:49:49'),(93,'공지사항','user','test39','','C:/asdfasdf','2023-05-09 19:49:49'),(94,'공지사항','user','test40','','C:/asdfasdf','2023-05-09 19:49:49'),(95,'공지사항','user','test41','','C:/asdfasdf','2023-05-09 19:49:49'),(96,'공지사항','user','test42','','C:/asdfasdf','2023-05-09 19:49:49'),(97,'공지사항','user','test43','','C:/asdfasdf','2023-05-09 19:49:49'),(98,'공지사항','user','test44','','C:/asdfasdf','2023-05-09 19:49:49'),(99,'공지사항','user','test45','','C:/asdfasdf','2023-05-09 19:49:49'),(100,'공지사항','user','test46','','C:/asdfasdf','2023-05-09 19:49:49'),(101,'공지사항','user','test47','','C:/asdfasdf','2023-05-09 19:49:49'),(102,'공지사항','user','test48','','C:/asdfasdf','2023-05-09 19:49:49'),(103,'공지사항','user','test49','','C:/asdfasdf','2023-05-09 19:49:49'),(104,'공지사항','user','test50','','C:/asdfasdf','2023-05-09 19:49:49'),(105,'공지사항','user','test51','','C:/asdfasdf','2023-05-09 19:49:49'),(106,'공지사항','user','test52','','C:/asdfasdf','2023-05-09 19:49:49'),(107,'공지사항','user','test53','','C:/asdfasdf','2023-05-09 19:49:49'),(108,'공지사항','user','test54','','C:/asdfasdf','2023-05-09 19:49:49'),(109,'공지사항','user','test55','','C:/asdfasdf','2023-05-09 19:49:49'),(110,'공지사항','user','test56','','C:/asdfasdf','2023-05-09 19:49:49'),(111,'공지사항','user','test57','','C:/asdfasdf','2023-05-09 19:49:49'),(112,'공지사항','user','test58','','C:/asdfasdf','2023-05-09 19:49:49'),(113,'공지사항','user','test59','','C:/asdfasdf','2023-05-09 19:49:49'),(114,'공지사항','user','test60','','C:/asdfasdf','2023-05-09 19:49:49'),(115,'공지사항','user','test61','','C:/asdfasdf','2023-05-09 19:49:49'),(116,'공지사항','user','test62','','C:/asdfasdf','2023-05-09 19:49:49'),(117,'공지사항','user','test63','','C:/asdfasdf','2023-05-09 19:49:49'),(118,'공지사항','user','test64','','C:/asdfasdf','2023-05-09 19:49:49'),(119,'공지사항','user','test65','','C:/asdfasdf','2023-05-09 19:49:49'),(120,'공지사항','user','test66','','C:/asdfasdf','2023-05-09 19:49:49'),(121,'공지사항','user','test67','','C:/asdfasdf','2023-05-09 19:49:49'),(122,'공지사항','user','test68','','C:/asdfasdf','2023-05-09 19:49:49'),(123,'공지사항','user','test69','','C:/asdfasdf','2023-05-09 19:49:49'),(124,'공지사항','user','test70','','C:/asdfasdf','2023-05-09 19:49:49'),(125,'공지사항','user','test71','','C:/asdfasdf','2023-05-09 19:49:49'),(126,'공지사항','user','test72','','C:/asdfasdf','2023-05-09 19:49:49'),(127,'공지사항','user','test73','','C:/asdfasdf','2023-05-09 19:49:49'),(128,'공지사항','user','test74','','C:/asdfasdf','2023-05-09 19:49:49'),(129,'공지사항','user','test75','','C:/asdfasdf','2023-05-09 19:49:49'),(130,'공지사항','user','test76','','C:/asdfasdf','2023-05-09 19:49:49'),(131,'공지사항','user','test77','','C:/asdfasdf','2023-05-09 19:49:49'),(132,'공지사항','user','test78','','C:/asdfasdf','2023-05-09 19:49:49'),(133,'공지사항','user','test79','','C:/asdfasdf','2023-05-09 19:49:49'),(134,'공지사항','user','test80','','C:/asdfasdf','2023-05-09 19:49:49'),(135,'공지사항','user','test81','','C:/asdfasdf','2023-05-09 19:49:49'),(136,'공지사항','user','test82','','C:/asdfasdf','2023-05-09 19:49:49'),(137,'공지사항','user','test83','','C:/asdfasdf','2023-05-09 19:49:49'),(138,'공지사항','user','test84','','C:/asdfasdf','2023-05-09 19:49:49'),(139,'공지사항','user','test85','','C:/asdfasdf','2023-05-09 19:49:49'),(140,'공지사항','user','test86','','C:/asdfasdf','2023-05-09 19:49:49'),(141,'공지사항','user','test87','','C:/asdfasdf','2023-05-09 19:49:49'),(142,'공지사항','user','test88','','C:/asdfasdf','2023-05-09 19:49:49'),(143,'공지사항','user','test89','','C:/asdfasdf','2023-05-09 19:49:49'),(144,'공지사항','user','test90','','C:/asdfasdf','2023-05-09 19:49:49'),(145,'공지사항','user','test91','','C:/asdfasdf','2023-05-09 19:49:49'),(146,'공지사항','user','test92','','C:/asdfasdf','2023-05-09 19:49:49'),(147,'공지사항','user','test93','','C:/asdfasdf','2023-05-09 19:49:49'),(148,'공지사항','user','test94','','C:/asdfasdf','2023-05-09 19:49:49'),(149,'공지사항','user','test95','','C:/asdfasdf','2023-05-09 19:49:49'),(150,'공지사항','user','test96','','C:/asdfasdf','2023-05-09 19:49:49'),(151,'공지사항','user','test97','','C:/asdfasdf','2023-05-09 19:49:49'),(152,'공지사항','user','test98','','C:/asdfasdf','2023-05-09 19:49:49'),(153,'공지사항','user','test100','','C:/asdfasdf','2023-05-09 19:52:40'),(154,'공지사항','user','test101','','C:/asdfasdf','2023-05-09 19:52:40'),(155,'공지사항','user','test102','','C:/asdfasdf','2023-05-09 19:52:40'),(156,'공지사항','user','test103','','C:/asdfasdf','2023-05-09 19:52:40'),(157,'공지사항','user','test104','','C:/asdfasdf','2023-05-09 19:52:40'),(158,'공지사항','user','test105','','C:/asdfasdf','2023-05-09 19:52:40'),(159,'공지사항','user','test106','','C:/asdfasdf','2023-05-09 19:52:40'),(160,'공지사항','user','test107','','C:/asdfasdf','2023-05-09 19:52:40'),(161,'공지사항','user','test108','','C:/asdfasdf','2023-05-09 19:52:40'),(162,'공지사항','user','test109','','C:/asdfasdf','2023-05-09 19:52:40'),(163,'공지사항','user','test110','','C:/asdfasdf','2023-05-09 19:52:40'),(164,'공지사항','user','test111','','C:/asdfasdf','2023-05-09 19:52:40'),(165,'공지사항','user','test112','','C:/asdfasdf','2023-05-09 19:52:40'),(166,'공지사항','user','test113','','C:/asdfasdf','2023-05-09 19:52:40'),(167,'공지사항','user','test114','','C:/asdfasdf','2023-05-09 19:52:40'),(168,'공지사항','user','test115','','C:/asdfasdf','2023-05-09 19:52:40'),(169,'공지사항','user','test116','','C:/asdfasdf','2023-05-09 19:52:40'),(170,'공지사항','user','test117','','C:/asdfasdf','2023-05-09 19:52:40'),(171,'공지사항','user','test118','','C:/asdfasdf','2023-05-09 19:52:40'),(172,'공지사항','user','test119','','C:/asdfasdf','2023-05-09 19:52:40'),(173,'공지사항','user','test120','','C:/asdfasdf','2023-05-09 19:52:40'),(174,'공지사항','user','test121','','C:/asdfasdf','2023-05-09 19:52:40'),(175,'공지사항','user','test122','','C:/asdfasdf','2023-05-09 19:52:40'),(176,'공지사항','user','test123','','C:/asdfasdf','2023-05-09 19:52:40'),(177,'공지사항','user','test124','','C:/asdfasdf','2023-05-09 19:52:40'),(178,'공지사항','user','test125','','C:/asdfasdf','2023-05-09 19:52:40'),(179,'공지사항','user','test126','','C:/asdfasdf','2023-05-09 19:52:40'),(180,'공지사항','user','test127','','C:/asdfasdf','2023-05-09 19:52:40'),(181,'공지사항','user','test128','','C:/asdfasdf','2023-05-09 19:52:40'),(182,'공지사항','user','test129','','C:/asdfasdf','2023-05-09 19:52:40'),(183,'공지사항','user','test130','','C:/asdfasdf','2023-05-09 19:52:40'),(184,'공지사항','user','test131','','C:/asdfasdf','2023-05-09 19:52:40'),(185,'공지사항','user','test132','','C:/asdfasdf','2023-05-09 19:52:40'),(186,'공지사항','user','test133','','C:/asdfasdf','2023-05-09 19:52:40'),(187,'공지사항','user','test134','','C:/asdfasdf','2023-05-09 19:52:40'),(188,'공지사항','user','test135','','C:/asdfasdf','2023-05-09 19:52:40'),(189,'공지사항','user','test136','','C:/asdfasdf','2023-05-09 19:52:40'),(190,'공지사항','user','test137','','C:/asdfasdf','2023-05-09 19:52:40'),(191,'공지사항','user','test138','','C:/asdfasdf','2023-05-09 19:52:40'),(192,'공지사항','user','test139','','C:/asdfasdf','2023-05-09 19:52:40'),(193,'공지사항','user','test140','','C:/asdfasdf','2023-05-09 19:52:40'),(194,'공지사항','user','test141','','C:/asdfasdf','2023-05-09 19:52:40'),(195,'공지사항','user','test142','','C:/asdfasdf','2023-05-09 19:52:40'),(196,'공지사항','user','test143','','C:/asdfasdf','2023-05-09 19:52:40'),(197,'공지사항','user','test144','','C:/asdfasdf','2023-05-09 19:52:40'),(198,'공지사항','user','test145','','C:/asdfasdf','2023-05-09 19:52:40'),(199,'공지사항','user','test146','','C:/asdfasdf','2023-05-09 19:52:40'),(200,'공지사항','user','test147','','C:/asdfasdf','2023-05-09 19:52:40'),(201,'공지사항','user','test148','','C:/asdfasdf','2023-05-09 19:52:40'),(202,'공지사항','user','test149','','C:/asdfasdf','2023-05-09 19:52:40'),(203,'공지사항','user','test150','','C:/asdfasdf','2023-05-09 19:52:40'),(204,'공지사항','user','test151','','C:/asdfasdf','2023-05-09 19:52:40'),(205,'공지사항','user','test152','','C:/asdfasdf','2023-05-09 19:52:40'),(206,'공지사항','user','test153','','C:/asdfasdf','2023-05-09 19:52:40'),(207,'공지사항','user','test154','','C:/asdfasdf','2023-05-09 19:52:40'),(208,'공지사항','user','test155','','C:/asdfasdf','2023-05-09 19:52:40'),(209,'공지사항','user','test156','','C:/asdfasdf','2023-05-09 19:52:40'),(210,'공지사항','user','test157','','C:/asdfasdf','2023-05-09 19:52:40'),(211,'공지사항','user','test158','','C:/asdfasdf','2023-05-09 19:52:40'),(212,'공지사항','user','test159','','C:/asdfasdf','2023-05-09 19:52:40'),(213,'공지사항','user','test160','','C:/asdfasdf','2023-05-09 19:52:40'),(214,'공지사항','user','test161','','C:/asdfasdf','2023-05-09 19:52:40'),(215,'공지사항','user','test162','','C:/asdfasdf','2023-05-09 19:52:40'),(216,'공지사항','user','test163','','C:/asdfasdf','2023-05-09 19:52:40'),(217,'공지사항','user','test164','','C:/asdfasdf','2023-05-09 19:52:40'),(218,'공지사항','user','test165','','C:/asdfasdf','2023-05-09 19:52:40'),(219,'공지사항','user','test166','','C:/asdfasdf','2023-05-09 19:52:40'),(220,'공지사항','user','test167','','C:/asdfasdf','2023-05-09 19:52:40'),(221,'공지사항','user','test168','','C:/asdfasdf','2023-05-09 19:52:40'),(222,'공지사항','user','test169','','C:/asdfasdf','2023-05-09 19:52:40'),(223,'공지사항','user','test170','','C:/asdfasdf','2023-05-09 19:52:40'),(224,'공지사항','user','test171','','C:/asdfasdf','2023-05-09 19:52:40'),(225,'공지사항','user','test172','','C:/asdfasdf','2023-05-09 19:52:40'),(226,'공지사항','user','test173','','C:/asdfasdf','2023-05-09 19:52:40'),(227,'공지사항','user','test174','','C:/asdfasdf','2023-05-09 19:52:40'),(228,'공지사항','user','test175','','C:/asdfasdf','2023-05-09 19:52:40'),(229,'공지사항','user','test176','','C:/asdfasdf','2023-05-09 19:52:40'),(230,'공지사항','user','test177','','C:/asdfasdf','2023-05-09 19:52:40'),(231,'공지사항','user','test178','','C:/asdfasdf','2023-05-09 19:52:40'),(232,'공지사항','user','test179','','C:/asdfasdf','2023-05-09 19:52:40'),(233,'공지사항','user','test180','','C:/asdfasdf','2023-05-09 19:52:40'),(234,'공지사항','user','test181','','C:/asdfasdf','2023-05-09 19:52:40'),(235,'공지사항','user','test182','','C:/asdfasdf','2023-05-09 19:52:40'),(236,'공지사항','user','test183','','C:/asdfasdf','2023-05-09 19:52:40'),(237,'공지사항','user','test184','','C:/asdfasdf','2023-05-09 19:52:40'),(238,'공지사항','user','test185','','C:/asdfasdf','2023-05-09 19:52:40'),(239,'공지사항','user','test186','','C:/asdfasdf','2023-05-09 19:52:40'),(240,'공지사항','user','test187','','C:/asdfasdf','2023-05-09 19:52:40'),(241,'공지사항','user','test188','','C:/asdfasdf','2023-05-09 19:52:40'),(242,'공지사항','user','test189','','C:/asdfasdf','2023-05-09 19:52:40'),(243,'공지사항','user','test190','','C:/asdfasdf','2023-05-09 19:52:40'),(244,'공지사항','user','test191','','C:/asdfasdf','2023-05-09 19:52:40'),(245,'공지사항','user','test192','','C:/asdfasdf','2023-05-09 19:52:40'),(246,'공지사항','user','test193','','C:/asdfasdf','2023-05-09 19:52:40'),(247,'공지사항','user','test194','','C:/asdfasdf','2023-05-09 19:52:40'),(248,'공지사항','user','test195','','C:/asdfasdf','2023-05-09 19:52:40'),(249,'공지사항','user','test196','','C:/asdfasdf','2023-05-09 19:52:40'),(250,'공지사항','user','test197','','C:/asdfasdf','2023-05-09 19:52:40'),(251,'공지사항','user','test198','','C:/asdfasdf','2023-05-09 19:52:40'),(252,'공지사항','user','test199','','C:/asdfasdf','2023-05-09 19:52:40'),(253,'공지사항','user','test200','','C:/asdfasdf','2023-05-09 19:52:40'),(254,'공지사항','user','test201','','C:/asdfasdf','2023-05-09 19:52:40'),(255,'공지사항','user','test202','','C:/asdfasdf','2023-05-09 19:52:40'),(256,'공지사항','user','test203','','C:/asdfasdf','2023-05-09 19:52:40'),(257,'공지사항','user','test204','','C:/asdfasdf','2023-05-09 19:52:40'),(258,'공지사항','user','test205','','C:/asdfasdf','2023-05-09 19:52:40'),(259,'공지사항','user','test206','','C:/asdfasdf','2023-05-09 19:52:40'),(260,'공지사항','user','test207','','C:/asdfasdf','2023-05-09 19:52:40'),(261,'공지사항','user','test208','','C:/asdfasdf','2023-05-09 19:52:40'),(262,'공지사항','user','test209','','C:/asdfasdf','2023-05-09 19:52:40'),(263,'공지사항','user','test210','','C:/asdfasdf','2023-05-09 19:52:40'),(264,'공지사항','user','test211','','C:/asdfasdf','2023-05-09 19:52:40'),(265,'공지사항','user','test212','','C:/asdfasdf','2023-05-09 19:52:40'),(266,'공지사항','user','test213','','C:/asdfasdf','2023-05-09 19:52:40'),(267,'공지사항','user','test214','','C:/asdfasdf','2023-05-09 19:52:40'),(268,'공지사항','user','test215','','C:/asdfasdf','2023-05-09 19:52:40'),(269,'공지사항','user','test216','','C:/asdfasdf','2023-05-09 19:52:40'),(270,'공지사항','user','test217','','C:/asdfasdf','2023-05-09 19:52:40'),(271,'공지사항','user','test218','','C:/asdfasdf','2023-05-09 19:52:40'),(272,'공지사항','user','test219','','C:/asdfasdf','2023-05-09 19:52:40'),(273,'공지사항','user','test220','','C:/asdfasdf','2023-05-09 19:52:40'),(274,'공지사항','user','test221','','C:/asdfasdf','2023-05-09 19:52:40'),(275,'공지사항','user','test222','','C:/asdfasdf','2023-05-09 19:52:40'),(276,'공지사항','user','test223','','C:/asdfasdf','2023-05-09 19:52:40'),(277,'공지사항','user','test224','','C:/asdfasdf','2023-05-09 19:52:40'),(278,'공지사항','user','test225','','C:/asdfasdf','2023-05-09 19:52:40'),(279,'공지사항','user','test226','','C:/asdfasdf','2023-05-09 19:52:40'),(280,'공지사항','user','test227','','C:/asdfasdf','2023-05-09 19:52:40'),(281,'공지사항','user','test228','','C:/asdfasdf','2023-05-09 19:52:40'),(282,'공지사항','user','test229','','C:/asdfasdf','2023-05-09 19:52:40'),(283,'공지사항','user','test230','','C:/asdfasdf','2023-05-09 19:52:40'),(284,'공지사항','user','test231','','C:/asdfasdf','2023-05-09 19:52:40'),(285,'공지사항','user','test232','','C:/asdfasdf','2023-05-09 19:52:40'),(286,'공지사항','user','test233','','C:/asdfasdf','2023-05-09 19:52:40'),(287,'공지사항','user','test234','','C:/asdfasdf','2023-05-09 19:52:40'),(288,'공지사항','user','test235','','C:/asdfasdf','2023-05-09 19:52:40'),(289,'공지사항','user','test236','','C:/asdfasdf','2023-05-09 19:52:40'),(290,'공지사항','user','test237','','C:/asdfasdf','2023-05-09 19:52:40'),(291,'공지사항','user','test238','','C:/asdfasdf','2023-05-09 19:52:40'),(292,'공지사항','user','test239','','C:/asdfasdf','2023-05-09 19:52:40'),(293,'공지사항','user','test240','','C:/asdfasdf','2023-05-09 19:52:40'),(294,'공지사항','user','test241','','C:/asdfasdf','2023-05-09 19:52:40'),(295,'공지사항','user','test242','','C:/asdfasdf','2023-05-09 19:52:40'),(296,'공지사항','user','test243','','C:/asdfasdf','2023-05-09 19:52:40'),(297,'공지사항','user','test244','','C:/asdfasdf','2023-05-09 19:52:40'),(298,'공지사항','user','test245','','C:/asdfasdf','2023-05-09 19:52:40'),(299,'공지사항','user','test246','','C:/asdfasdf','2023-05-09 19:52:40'),(300,'공지사항','user','test247','','C:/asdfasdf','2023-05-09 19:52:40'),(301,'공지사항','user','test248','','C:/asdfasdf','2023-05-09 19:52:40'),(302,'공지사항','user','test249','','C:/asdfasdf','2023-05-09 19:52:40'),(303,'공지사항','user','test250','','C:/asdfasdf','2023-05-09 19:52:40'),(304,'공지사항','user','test251','','C:/asdfasdf','2023-05-09 19:52:40'),(305,'공지사항','user','test252','','C:/asdfasdf','2023-05-09 19:52:40'),(306,'공지사항','user','test253','','C:/asdfasdf','2023-05-09 19:52:40'),(307,'공지사항','user','test254','','C:/asdfasdf','2023-05-09 19:52:40'),(308,'공지사항','user','test255','','C:/asdfasdf','2023-05-09 19:52:40'),(309,'공지사항','user','test256','','C:/asdfasdf','2023-05-09 19:52:40'),(310,'공지사항','user','test257','','C:/asdfasdf','2023-05-09 19:52:40'),(311,'공지사항','user','test258','','C:/asdfasdf','2023-05-09 19:52:40'),(312,'공지사항','user','test259','','C:/asdfasdf','2023-05-09 19:52:40'),(313,'공지사항','user','test260','','C:/asdfasdf','2023-05-09 19:52:40'),(314,'공지사항','user','test261','','C:/asdfasdf','2023-05-09 19:52:40'),(315,'공지사항','user','test262','','C:/asdfasdf','2023-05-09 19:52:40'),(316,'공지사항','user','test263','','C:/asdfasdf','2023-05-09 19:52:40'),(317,'공지사항','user','test264','','C:/asdfasdf','2023-05-09 19:52:40'),(318,'공지사항','user','test265','','C:/asdfasdf','2023-05-09 19:52:40'),(319,'공지사항','user','test266','','C:/asdfasdf','2023-05-09 19:52:40'),(320,'공지사항','user','test267','','C:/asdfasdf','2023-05-09 19:52:40'),(321,'공지사항','user','test268','','C:/asdfasdf','2023-05-09 19:52:40'),(322,'공지사항','user','test269','','C:/asdfasdf','2023-05-09 19:52:40'),(323,'공지사항','user','test270','','C:/asdfasdf','2023-05-09 19:52:40'),(324,'공지사항','user','test271','','C:/asdfasdf','2023-05-09 19:52:40'),(325,'공지사항','user','test272','','C:/asdfasdf','2023-05-09 19:52:40'),(326,'공지사항','user','test273','','C:/asdfasdf','2023-05-09 19:52:40'),(327,'공지사항','user','test274','','C:/asdfasdf','2023-05-09 19:52:40'),(328,'공지사항','user','test275','','C:/asdfasdf','2023-05-09 19:52:40'),(329,'공지사항','user','test276','','C:/asdfasdf','2023-05-09 19:52:40'),(330,'공지사항','user','test277','','C:/asdfasdf','2023-05-09 19:52:40'),(331,'공지사항','user','test278','','C:/asdfasdf','2023-05-09 19:52:40'),(332,'공지사항','user','test279','','C:/asdfasdf','2023-05-09 19:52:40'),(333,'공지사항','user','test280','','C:/asdfasdf','2023-05-09 19:52:40'),(334,'공지사항','user','test281','','C:/asdfasdf','2023-05-09 19:52:40'),(335,'공지사항','user','test282','','C:/asdfasdf','2023-05-09 19:52:40'),(336,'공지사항','user','test283','','C:/asdfasdf','2023-05-09 19:52:40'),(337,'공지사항','user','test284','','C:/asdfasdf','2023-05-09 19:52:40'),(338,'공지사항','user','test285','','C:/asdfasdf','2023-05-09 19:52:40'),(339,'공지사항','user','test286','','C:/asdfasdf','2023-05-09 19:52:40'),(340,'공지사항','user','test287','','C:/asdfasdf','2023-05-09 19:52:40'),(341,'공지사항','user','test288','','C:/asdfasdf','2023-05-09 19:52:40'),(342,'공지사항','user','test289','','C:/asdfasdf','2023-05-09 19:52:40'),(343,'공지사항','user','test290','','C:/asdfasdf','2023-05-09 19:52:40'),(344,'공지사항','user','test291','','C:/asdfasdf','2023-05-09 19:52:40'),(345,'공지사항','user','test292','','C:/asdfasdf','2023-05-09 19:52:40'),(346,'공지사항','user','test293','','C:/asdfasdf','2023-05-09 19:52:40'),(347,'공지사항','user','test294','','C:/asdfasdf','2023-05-09 19:52:40'),(348,'공지사항','user','test295','','C:/asdfasdf','2023-05-09 19:52:40'),(349,'공지사항','user','test296','','C:/asdfasdf','2023-05-09 19:52:40'),(350,'공지사항','user','test297','','C:/asdfasdf','2023-05-09 19:52:40'),(351,'공지사항','user','test298','','C:/asdfasdf','2023-05-09 19:52:40'),(352,'공지사항','user','test299','','C:/asdfasdf','2023-05-09 19:52:40'),(353,'공지사항','user','test300','','C:/asdfasdf','2023-05-09 19:53:09'),(354,'공지사항','user','asdfasdf','<p>asdfasdf</p><p><br></p>','C:/update/file','2023-05-09 19:55:02');
/*!40000 ALTER TABLE `board_tbl` ENABLE KEYS */;
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
