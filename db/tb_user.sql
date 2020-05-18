/*
 Navicat Premium Data Transfer

 Source Server         : localdb
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : run

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/05/2020 17:54:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'laowang', '112233');
INSERT INTO `tb_user` VALUES (2, 'laoli', '123456');
INSERT INTO `tb_user` VALUES (3, 'bbb', '1211221');
INSERT INTO `tb_user` VALUES (4, 'bbb', '21132');
INSERT INTO `tb_user` VALUES (5, 'cc', '3232');
INSERT INTO `tb_user` VALUES (6, '4343', '6666666');
INSERT INTO `tb_user` VALUES (7, 'gwgsf', '52');

SET FOREIGN_KEY_CHECKS = 1;
