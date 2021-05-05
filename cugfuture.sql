-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 8.140.103.153    Database: cug_future
-- ------------------------------------------------------
-- Server version	5.7.33

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
-- Table structure for table `ACTIVITIES`
--

DROP TABLE IF EXISTS `ACTIVITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ACTIVITIES` (
  `NUM` int(11) NOT NULL,
  `TITLE` varchar(45) COLLATE utf8_bin NOT NULL,
  `CONT_INDEX` varchar(100) COLLATE utf8_bin NOT NULL,
  `VIEW_VOL` varchar(45) COLLATE utf8_bin NOT NULL,
  `ST_TIME` date NOT NULL,
  `END_TIME` date NOT NULL,
  `IMG_INDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACTIVITIES`
--

LOCK TABLES `ACTIVITIES` WRITE;
/*!40000 ALTER TABLE `ACTIVITIES` DISABLE KEYS */;
INSERT INTO `ACTIVITIES` VALUES (1,'机器人实验室参观','/root/Documents/weixin/activities/content/1.txt','1','2021-04-29','2021-05-01','../../activities/pic/1/pic1.png;'),(2,'食堂领鸡腿','/root/Documents/weixin/activities/content/2.txt','0','2021-04-29','2021-04-30','../../activities/pic/2/pic1.png;'),(3,'烛光祭','/root/Documents/weixin/activities/content/3.txt','4','2021-04-22','2021-04-22','../../activities/pic/2/pic1.png;');
/*!40000 ALTER TABLE `ACTIVITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMUNITY`
--

DROP TABLE IF EXISTS `COMMUNITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMUNITY` (
  `NUM` int(11) NOT NULL,
  `NAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `HEAD` varchar(255) COLLATE utf8_bin NOT NULL,
  `CONTENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `LIKE_VOL` int(11) NOT NULL,
  `TC_TIME` datetime NOT NULL,
  `IMG_INDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  `LIKELIST` varchar(255) COLLATE utf8_bin NOT NULL,
  `STORELIST` varchar(255) COLLATE utf8_bin NOT NULL,
  `COMMENT` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMUNITY`
--

LOCK TABLES `COMMUNITY` WRITE;
/*!40000 ALTER TABLE `COMMUNITY` DISABLE KEYS */;
INSERT INTO `COMMUNITY` VALUES (1,'美咲','https://thirdwx.qlogo.cn/mmopen/vi_32/VK3sAgzYxZsiblF9ocxWI79xL57b5uXRecIhIr7fh0F8hxujPPJAIx7640pRViaoV3Aa70dQ9Sh1GW6vrTMeOv1A/132','meimeizi',4,'2021-05-05 21:20:46','http://tmp/FlzKU8qncWkB1d124d97e03d5da45908b4647a5ea523.jpg;',';;;;','美咲;;;;;',''),(2,'美咲','https://thirdwx.qlogo.cn/mmopen/vi_32/VK3sAgzYxZsiblF9ocxWI79xL57b5uXRecIhIr7fh0F8hxujPPJAIx7640pRViaoV3Aa70dQ9Sh1GW6vrTMeOv1A/132','147',3,'2021-05-05 21:33:44','http://tmp/xbl9uSvpYUrJa0df508c2ab992be477901092f1ef9f9.jpg;',';;;',';',''),(3,'美咲','https://thirdwx.qlogo.cn/mmopen/vi_32/VK3sAgzYxZsiblF9ocxWI79xL57b5uXRecIhIr7fh0F8hxujPPJAIx7640pRViaoV3Aa70dQ9Sh1GW6vrTMeOv1A/132','484',3,'2021-05-05 21:39:53','http://tmp/q3917kVjLhGJd577d210709d21d3b48197b12ee01c0e.png;',';;;','美咲;;;;',''),(4,'美咲','https://thirdwx.qlogo.cn/mmopen/vi_32/VK3sAgzYxZsiblF9ocxWI79xL57b5uXRecIhIr7fh0F8hxujPPJAIx7640pRViaoV3Aa70dQ9Sh1GW6vrTMeOv1A/132','487',4,'2021-05-05 21:48:30','http://tmp/fko5g3Dpp6Tr86df369f4dd99a00f08d6449413e07dc.png;','美咲;;;;',';','美咲:喵喵喵/#/美咲:帅哥你好/#/'),(5,'美咲','https://thirdwx.qlogo.cn/mmopen/vi_32/VK3sAgzYxZsiblF9ocxWI79xL57b5uXRecIhIr7fh0F8hxujPPJAIx7640pRViaoV3Aa70dQ9Sh1GW6vrTMeOv1A/132','帅哥自拍',1,'2021-05-06 02:17:19','http://tmp/ZO2emzNP8fSr8066a6ead8f521db40a5fdaf84e17040.png;','美咲;','美咲;','美咲:哥哥好帅/#/');
/*!40000 ALTER TABLE `COMMUNITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOST`
--

DROP TABLE IF EXISTS `LOST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOST` (
  `NUM` int(11) NOT NULL,
  `TITLE` varchar(45) COLLATE utf8_bin NOT NULL,
  `CONT_INDEX` varchar(45) COLLATE utf8_bin NOT NULL,
  `ST_TIME` datetime NOT NULL,
  `STATE` varchar(45) COLLATE utf8_bin NOT NULL,
  `IMG_INDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOST`
--

LOCK TABLES `LOST` WRITE;
/*!40000 ALTER TABLE `LOST` DISABLE KEYS */;
INSERT INTO `LOST` VALUES (1,'水杯','/root/Documents/weixin/lost/content/1.txt','2021-04-25 00:00:00','0','../../lost/pic/1/pic1.png;'),(2,'校园卡','/root/Documents/weixin/lost/content/2.txt','2021-04-26 00:00:00','1','../../lost/pic/2/pic1.png;'),(3,'高泽宇','/root/Documents/weixin/lost/content/3.txt','2021-04-27 00:00:00','0','../../lost/pic/3/pic1.png;');
/*!40000 ALTER TABLE `LOST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NEWS`
--

DROP TABLE IF EXISTS `NEWS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NEWS` (
  `NUM` int(11) NOT NULL,
  `TITLE` varchar(45) COLLATE utf8_bin NOT NULL,
  `CONT_INDEX` varchar(45) COLLATE utf8_bin NOT NULL,
  `VIEW_VOL` int(11) NOT NULL,
  `NEWS_TIME` datetime NOT NULL,
  `IMG_INDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NEWS`
--

LOCK TABLES `NEWS` WRITE;
/*!40000 ALTER TABLE `NEWS` DISABLE KEYS */;
INSERT INTO `NEWS` VALUES (1,'地大红色故事04 | 迎着新中国朝阳毅然回国克己奉公的地大教学元勋和南建主帅——池际尚','/root/Documents/weixin/news/content/1.txt',0,'2021-04-29 00:00:00','../../news/pic/1/pic1.png;'),(2,'环境学院党委：注入红色基因铸魂育人','/root/Documents/weixin/news/content/2.txt',4,'2021-04-29 00:00:00','../../news/pic/2/pic1.png');
/*!40000 ALTER TABLE `NEWS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RELEASED`
--

DROP TABLE IF EXISTS `RELEASED`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RELEASED` (
  `NAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `RELEINDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RELEASED`
--

LOCK TABLES `RELEASED` WRITE;
/*!40000 ALTER TABLE `RELEASED` DISABLE KEYS */;
INSERT INTO `RELEASED` VALUES ('',''),('M.i.n.e',''),('美咲','1;2;3;4;5;');
/*!40000 ALTER TABLE `RELEASED` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STORE`
--

DROP TABLE IF EXISTS `STORE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `STORE` (
  `NAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `ST_INDEX` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STORE`
--

LOCK TABLES `STORE` WRITE;
/*!40000 ALTER TABLE `STORE` DISABLE KEYS */;
INSERT INTO `STORE` VALUES ('','3;1;1;3;1;2;3;4;1;5;'),('M.i.n.e','3;1;1;3;1;2;3;4;1;5;'),('美咲','3;1;1;3;1;2;3;4;1;5;');
/*!40000 ALTER TABLE `STORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERS` (
  `ACCOUNT` varchar(45) COLLATE utf8_bin NOT NULL,
  `PASSWD` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES ('123','123'),('826325699','123698');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-06  2:18:52
