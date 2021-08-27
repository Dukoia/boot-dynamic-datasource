/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : slave2

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/08/2021 10:00:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pico_promote_image
-- ----------------------------
DROP TABLE IF EXISTS `pico_promote_image`;
CREATE TABLE `pico_promote_image`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `subject_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主题名称',
  `image_url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片URL',
  `image_type` tinyint(0) NULL DEFAULT NULL COMMENT '图片类型',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态 0可用 1过期',
  `create_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pico_promote_image
-- ----------------------------
INSERT INTO `pico_promote_image` VALUES (1, 'slave2', '<p style=\"margin: 0px; padding: 0px 0px 10px; color: #1d1d1d; font-size: 14px; line-height: 28px;\">在这样的特殊时期，居家娱乐变得非常火热。除了日常的大屏观影和主机游戏之外，越来越多的人开始把玩起了取得长足进步，可玩性不断提升的VR设备，Pico Neo 2就是其中的新旗舰。</p>\r\n<p style=\"margin: 0px; padding: 0px 0px 10px; color: #1d1d1d; font-size: 14px; line-height: 28px;\"><br />处理器升级、自由度更高、电磁手柄新技术等都成为2020年这款新品的主要卖点，今天，小编带大家开箱体验，看看这款全新的VR一体机有多大本事。</p>\r\n<figure class=\"image\"><img src=\"http://bbs-resource.oss-cn-zhangjiakou.aliyuncs.com/picture/1621562796321zyw.jpg\" alt=\"开箱\" width=\"1200\" height=\"900\" />\r\n<figcaption>Caption</figcaption>\r\n</figure>\r\n<p style=\"margin: 0px; padding: 0px 0px 10px; color: #1d1d1d; font-size: 14px; line-height: 28px;\">&nbsp;</p>', 0, 1, '北极光', NULL, '2021-06-11 17:07:41', NULL);

SET FOREIGN_KEY_CHECKS = 1;
