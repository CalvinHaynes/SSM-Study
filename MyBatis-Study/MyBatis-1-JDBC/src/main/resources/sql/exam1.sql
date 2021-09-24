-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: exam1
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(30) NOT NULL COMMENT '博客标题',
  `author` varchar(30) NOT NULL COMMENT '博客作者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `views` int NOT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20210824 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`id`, `title`, `author`, `create_time`, `views`) VALUES (20210101,'LinuxBasics','Linus Benedict Torvalds','2021-01-01 16:43:21',10000),(20210622,'CBasics','Dennis M.Ritchie','2021-06-22 16:42:41',1900),(20210722,'PythonBascics','Guido van Rossum','2021-07-22 16:41:14',199),(20210822,'JavaBasics','James Gosling','2021-08-22 16:40:18',10),(20210823,'MyBatisBasics','Calvin Haynes','2021-08-23 15:26:03',888888);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `cust_name` char(50) NOT NULL,
  `cust_address` char(50) DEFAULT NULL,
  `cust_city` char(50) DEFAULT NULL,
  `cust_state` char(5) DEFAULT NULL,
  `cust_zip` char(10) DEFAULT NULL,
  `cust_country` char(50) DEFAULT NULL,
  `cust_contact` char(50) DEFAULT NULL,
  `cust_email` char(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`cust_id`, `cust_name`, `cust_address`, `cust_city`, `cust_state`, `cust_zip`, `cust_country`, `cust_contact`, `cust_email`) VALUES (10001,'Coyote Inc.','200 Maple Lane','Detroit','MI','44444','USA','Y Lee','ylee@coyote.com'),(10002,'Mouse House','333 Fromage Lane','Columbus','OH','43333','USA','Jerry Mouse',NULL),(10003,'Wascals','1 Sunny Place','Muncie','IN','42222','USA','Jim Jones','rabbit@wascally.com'),(10004,'Yosemite Place','829 Riverside Drive','Phoenix','AZ','88888','USA','Y Sam','sam@yosemite.com'),(10005,'E Fudd','4545 53rd Street','Chicago','IL','54545','USA','E Fudd',NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitems` (
  `order_num` int NOT NULL,
  `order_item` int NOT NULL,
  `prod_id` char(10) NOT NULL,
  `quantity` int NOT NULL,
  `item_price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`order_num`,`order_item`),
  KEY `fk_orderitems_products` (`prod_id`),
  CONSTRAINT `fk_orderitems_orders` FOREIGN KEY (`order_num`) REFERENCES `orders` (`order_num`),
  CONSTRAINT `fk_orderitems_products` FOREIGN KEY (`prod_id`) REFERENCES `products` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` (`order_num`, `order_item`, `prod_id`, `quantity`, `item_price`) VALUES (20005,1,'ANV01',10,5.99),(20005,2,'ANV02',3,9.99),(20005,3,'TNT2',5,10.00),(20005,4,'FB',1,10.00),(20006,1,'JP2000',1,55.00),(20007,1,'TNT2',100,10.00),(20008,1,'FC',50,2.50),(20009,1,'FB',1,10.00),(20009,2,'OL1',1,8.99),(20009,3,'SLING',1,4.49),(20009,4,'ANV03',1,14.99),(20009,8,'FB',20,23.99);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_num` int NOT NULL AUTO_INCREMENT,
  `order_date` datetime NOT NULL,
  `cust_id` int NOT NULL,
  PRIMARY KEY (`order_num`),
  KEY `fk_orders_customers` (`cust_id`),
  CONSTRAINT `fk_orders_customers` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`order_num`, `order_date`, `cust_id`) VALUES (20005,'2005-09-01 00:00:00',10001),(20006,'2005-09-12 00:00:00',10003),(20007,'2005-09-30 00:00:00',10004),(20008,'2005-10-03 00:00:00',10005),(20009,'2005-10-08 00:00:00',10001);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productnotes`
--

DROP TABLE IF EXISTS `productnotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productnotes` (
  `note_id` int NOT NULL AUTO_INCREMENT,
  `prod_id` char(10) NOT NULL,
  `note_date` datetime NOT NULL,
  `note_text` text,
  PRIMARY KEY (`note_id`),
  FULLTEXT KEY `note_text` (`note_text`)
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productnotes`
--

LOCK TABLES `productnotes` WRITE;
/*!40000 ALTER TABLE `productnotes` DISABLE KEYS */;
INSERT INTO `productnotes` (`note_id`, `prod_id`, `note_date`, `note_text`) VALUES (101,'TNT2','2005-08-17 00:00:00','Customer complaint:\nSticks not individually wrapped, too easy to mistakenly detonate all at once.\nRecommend individual wrapping.'),(102,'OL1','2005-08-18 00:00:00','Can shipped full, refills not available.\nNeed to order new can if refill needed.'),(103,'SAFE','2005-08-18 00:00:00','Safe is combination locked, combination not provided with safe.\nThis is rarely a problem as safes are typically blown up or dropped by customers.'),(104,'FC','2005-08-19 00:00:00','Quantity varies, sold by the sack load.\nAll guaranteed to be bright and orange, and suitable for use as rabbit bait.'),(105,'TNT2','2005-08-20 00:00:00','Included fuses are short and have been known to detonate too quickly for some customers.\nLonger fuses are available (item FU1) and should be recommended.'),(106,'TNT2','2005-08-22 00:00:00','Matches not included, recommend purchase of matches or detonator (item DTNTR).'),(107,'SAFE','2005-08-23 00:00:00','Please note that no returns will be accepted if safe opened using explosives.'),(108,'ANV01','2005-08-25 00:00:00','Multiple customer returns, anvils failing to drop fast enough or falling backwards on purchaser. Recommend that customer considers using heavier anvils.'),(109,'ANV03','2005-09-01 00:00:00','Item is extremely heavy. Designed for dropping, not recommended for use with slings, ropes, pulleys, or tightropes.'),(110,'FC','2005-09-01 00:00:00','Customer complaint: rabbit has been able to detect trap, food apparently less effective now.'),(111,'SLING','2005-09-02 00:00:00','Shipped unassembled, requires common tools (including oversized hammer).'),(112,'SAFE','2005-09-02 00:00:00','Customer complaint:\nCircular hole in safe floor can apparently be easily cut with handsaw.'),(113,'ANV01','2005-09-05 00:00:00','Customer complaint:\nNot heavy enough to generate flying stars around head of victim. If being purchased for dropping, recommend ANV02 or ANV03 instead.'),(114,'SAFE','2005-09-07 00:00:00','Call from individual trapped in safe plummeting to the ground, suggests an escape hatch be added.\nComment forwarded to vendor.');
/*!40000 ALTER TABLE `productnotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `prod_id` char(10) NOT NULL,
  `vend_id` int NOT NULL,
  `prod_name` char(255) NOT NULL,
  `prod_price` decimal(8,2) NOT NULL,
  `prod_desc` text,
  PRIMARY KEY (`prod_id`),
  KEY `fk_products_vendors` (`vend_id`),
  CONSTRAINT `fk_products_vendors` FOREIGN KEY (`vend_id`) REFERENCES `vendors` (`vend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`prod_id`, `vend_id`, `prod_name`, `prod_price`, `prod_desc`) VALUES ('ANV01',1001,'.5 ton anvil',5.99,'.5 ton anvil, black, complete with handy hook'),('ANV02',1001,'1 ton anvil',9.99,'1 ton anvil, black, complete with handy hook and carrying case'),('ANV03',1001,'2 ton anvil',14.99,'2 ton anvil, black, complete with handy hook and carrying case'),('DTNTR',1003,'Detonator',13.00,'Detonator (plunger powered), fuses not included'),('FB',1003,'Bird seed',10.00,'Large bag (suitable for road runners)'),('FC',1003,'Carrots',2.50,'Carrots (rabbit hunting season only)'),('FU1',1002,'Fuses',3.42,'1 dozen, extra long'),('JP1000',1005,'JetPack 1000',35.00,'JetPack 1000, intended for single use'),('JP2000',1005,'JetPack 2000',55.00,'JetPack 2000, multi-use'),('OL1',1002,'Oil can',8.99,'Oil can, red'),('SAFE',1003,'Safe',50.00,'Safe with combination lock'),('SLING',1003,'Sling',4.49,'Sling, one size fits all'),('TNT1',1003,'TNT (1 stick)',2.50,'TNT, red, single stick'),('TNT2',1003,'TNT (5 sticks)',10.00,'TNT, red, pack of 10 sticks');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `tid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (1,'小明',1),(2,'小红',1),(3,'小张',1),(4,'小李',1),(5,'小王',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` int NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `name`) VALUES (1,'大司马');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `password`, `last_name`) VALUES (1001,'Jack','123456','London'),(1002,'Mary','654321','Smith'),(1003,'Calvin','987654','Haynes'),(1005,'Luise','5985625','Anna'),(1007,'LiHua','8905467577','Wang');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendors` (
  `vend_id` int NOT NULL AUTO_INCREMENT,
  `vend_name` char(50) NOT NULL,
  `vend_address` char(50) DEFAULT NULL,
  `vend_city` char(50) DEFAULT NULL,
  `vend_state` char(5) DEFAULT NULL,
  `vend_zip` char(10) DEFAULT NULL,
  `vend_country` char(50) DEFAULT NULL,
  PRIMARY KEY (`vend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` (`vend_id`, `vend_name`, `vend_address`, `vend_city`, `vend_state`, `vend_zip`, `vend_country`) VALUES (1001,'Anvils R Us','123 Main Street','Southfield','MI','48075','USA'),(1002,'LT Supplies','500 Park Street','Anytown','OH','44333','USA'),(1003,'ACME','555 High Street','Los Angeles','CA','90046','USA'),(1004,'Furball Inc.','1000 5th Avenue','New York','NY','11111','USA'),(1005,'Jet Set','42 Galaxy Road','London',NULL,'N16 6PS','England'),(1006,'Jouets Et Ours','1 Rue Amusement','Paris',NULL,'45678','France');
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-24 21:13:59
