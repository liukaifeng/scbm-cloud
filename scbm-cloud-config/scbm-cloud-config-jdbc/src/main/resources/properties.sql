/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : scbm_cloud

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 11/11/2018 14:10:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `application` varchar(50) DEFAULT NULL,
  `profile` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES (2, 'eureka.client.serviceUrl.defaultZone', '${EUREKA_SERVICE_URL:http://localhost:8888}/eureka/', 'eureka-client', 'dev', 'v0.0.1', '配置中心地址');
INSERT INTO `properties` VALUES (3, 'management.endpoint.conditions.enabled', 'true', 'eureka-client', 'dev', 'v0.0.1', '启用终结点');
INSERT INTO `properties` VALUES (4, 'eureka.instance.prefer-ip-address', 'true', 'eureka-client', 'dev', 'v0.0.1', '使用IP地址注册到注册中心');
INSERT INTO `properties` VALUES (5, 'spring.application.name', 'eureka-client', 'eureka-client', 'dev', 'v0.0.1', '应用名称');
INSERT INTO `properties` VALUES (6, 'eureka.instance.instanceId', '${spring.application.name}@${spring.cloud.client.ip-address}@${server.port}', 'eureka-client', 'dev', 'v0.0.1', '在注册中心的实例ID');
INSERT INTO `properties` VALUES (7, 'management.endpoints.web.exposure.include', '*', 'eureka-client', 'dev', 'v0.0.1', '开放哪些监控端口');
INSERT INTO `properties` VALUES (8, 'server.port', '8000', 'eureka-client', 'dev', 'v0.0.1', '应用服务端口号');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
