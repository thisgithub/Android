/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2014-03-23 21:50:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ownerId` bigint(20) DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  `receiverId` bigint(20) DEFAULT NULL,
  `message` blob,
  `flag` varchar(1) DEFAULT NULL,
  `deleted` varchar(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO message VALUES ('4', '1', '4', 0x73, '0', '0', '2014-03-23 21:35:12', '1');
INSERT INTO message VALUES ('5', '1', '5', 0x68656C6C6F2061646D696E342074686973207369206120, '0', '0', '2014-03-23 21:36:49', '2');
INSERT INTO message VALUES ('4', '1', '4', 0x68656C6C6F2065766572796F6E65, '0', '0', '2014-03-23 21:38:07', '3');
INSERT INTO message VALUES ('5', '1', '5', 0x68656C6C6F2065766572796F6E65, '0', '0', '2014-03-23 21:38:07', '4');
INSERT INTO message VALUES ('4', '1', '4', 0x736466736466, '0', '0', '2014-03-23 21:48:27', '5');
INSERT INTO message VALUES ('1', '1', '1', 0x68616F206C65206D6120, '0', '0', '2014-03-23 21:49:00', '6');
INSERT INTO message VALUES ('2', '1', '2', 0x68616F206C65206D6120, '0', '0', '2014-03-23 21:49:00', '7');
INSERT INTO message VALUES ('3', '1', '3', 0x68616F206C65206D6120, '0', '0', '2014-03-23 21:49:00', '8');
INSERT INTO message VALUES ('4', '1', '4', 0x68616F206C65206D6120, '0', '0', '2014-03-23 21:49:00', '9');
INSERT INTO message VALUES ('5', '1', '5', 0x68616F206C65206D6120, '0', '0', '2014-03-23 21:49:00', '10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `online` int(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('admin', 'admin', '1', '1', '2014-03-24');
INSERT INTO user VALUES ('admin1', 'admin1', '0', '2', '2014-03-24');
INSERT INTO user VALUES ('admin2', 'admin2', '1', '3', '2014-03-23');
INSERT INTO user VALUES ('admin3', 'admin3', '1', '4', '2014-03-23');
INSERT INTO user VALUES ('admin4', 'admin4', '1', '5', '2014-03-23');
