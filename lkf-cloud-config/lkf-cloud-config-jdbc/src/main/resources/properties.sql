/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.7.17 : Database - lkf_cloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lkf_cloud` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lkf_cloud`;

/*Table structure for table `properties` */

DROP TABLE IF EXISTS `properties`;

CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `application` varchar(50) DEFAULT NULL,
  `profile` varchar(50) DEFAULT NULL,
  `lable` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `properties` */

insert  into `properties`(`id`,`key`,`value`,`application`,`profile`,`lable`) values 
(1,'name','kaifeng','cloud-client','dev','v0.0.1'),
(2,'eureka.client.serviceUrl.defaultZone','${EUREKA_SERVICE_URL:http://192.168.12.217:8890}/lb/eureka/','lkf-eureka-client','dev','v0.0.1'),
(3,'management.endpoint.conditions.enabled','true','lkf-eureka-client','dev','v0.0.1'),
(4,'eureka.instance.prefer-ip-address','true','lkf-eureka-client','dev','v0.0.1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
