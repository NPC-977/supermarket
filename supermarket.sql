/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/06/2023 16:20:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_clock_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_clock_record`;
CREATE TABLE `sys_clock_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `e_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_clock_record
-- ----------------------------
INSERT INTO `sys_clock_record` VALUES (1, '000001', '2023-06-03 11:06:07');
INSERT INTO `sys_clock_record` VALUES (6, '000001', '2023-06-03 11:08:13');
INSERT INTO `sys_clock_record` VALUES (7, '000001', '2023-06-04 19:19:08');
INSERT INTO `sys_clock_record` VALUES (8, '000001', '2023-06-05 01:50:56');
INSERT INTO `sys_clock_record` VALUES (11, '000001', '2023-06-26 18:18:08');
INSERT INTO `sys_clock_record` VALUES (14, 'undefined', '2023-06-26 23:56:40');
INSERT INTO `sys_clock_record` VALUES (15, '0001', '2023-06-26 23:58:44');
INSERT INTO `sys_clock_record` VALUES (16, '0001', '2023-06-27 00:00:13');
INSERT INTO `sys_clock_record` VALUES (17, '0001', '2023-06-27 00:00:45');
INSERT INTO `sys_clock_record` VALUES (18, '0001', '2023-06-27 00:00:45');
INSERT INTO `sys_clock_record` VALUES (19, '0001', '2023-06-27 00:00:46');
INSERT INTO `sys_clock_record` VALUES (20, '0001', '2023-06-27 00:00:46');
INSERT INTO `sys_clock_record` VALUES (21, '0002', '2023-06-27 00:00:59');
INSERT INTO `sys_clock_record` VALUES (22, '0002', '2023-06-27 00:01:05');
INSERT INTO `sys_clock_record` VALUES (23, '0002', '2023-06-27 00:02:52');
INSERT INTO `sys_clock_record` VALUES (24, '0002', '2023-06-27 00:02:55');
INSERT INTO `sys_clock_record` VALUES (25, '0001', '2023-06-27 02:24:54');
INSERT INTO `sys_clock_record` VALUES (26, '0005', '2023-06-27 14:25:41');
INSERT INTO `sys_clock_record` VALUES (27, '000004', '2023-06-27 14:25:57');
INSERT INTO `sys_clock_record` VALUES (28, '0007', '2023-06-27 14:37:17');
INSERT INTO `sys_clock_record` VALUES (29, '0001', '2023-06-27 15:39:25');

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee`  (
  `e_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证',
  `date` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '入职时间',
  `wages` int NULL DEFAULT NULL COMMENT '薪资',
  `urid` int NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`e_id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  UNIQUE INDEX `uid`(`urid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_employee
-- ----------------------------
INSERT INTO `sys_employee` VALUES ('000004', '叶清渠4', '4', '2023-06-27 14:03:15', 400, 54);
INSERT INTO `sys_employee` VALUES ('0001', '叶清渠1', '1', '2023-06-26 23:55:44', 1, 51);
INSERT INTO `sys_employee` VALUES ('0007', '叶清渠7', '777', '2023-06-27 14:35:11', 777, 56);
INSERT INTO `sys_employee` VALUES ('123123', '叶清渠123123', '123123', '2023-06-27 15:36:18', 123123, 74);
INSERT INTO `sys_employee` VALUES ('1233', '叶清渠123', '111123', '2023-06-27 15:35:15', 1111, 57);

-- ----------------------------
-- Table structure for sys_in_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_in_record`;
CREATE TABLE `sys_in_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `count` int NULL DEFAULT NULL COMMENT '入库数量',
  `date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_in_record
-- ----------------------------
INSERT INTO `sys_in_record` VALUES (14, '000004', 20, '2023-06-02 14:10:39');
INSERT INTO `sys_in_record` VALUES (15, '000005', 3, '2023-06-02 14:11:32');
INSERT INTO `sys_in_record` VALUES (17, '000001', 90, '2023-06-04 21:06:19');
INSERT INTO `sys_in_record` VALUES (18, '000006', 30, '2023-05-01 23:37:00');
INSERT INTO `sys_in_record` VALUES (20, '000006', 3, '2023-06-05 01:50:33');
INSERT INTO `sys_in_record` VALUES (21, '000012', 100, '2023-06-23 17:47:41');
INSERT INTO `sys_in_record` VALUES (22, '000004', 12, '2022-05-01 14:29:43');
INSERT INTO `sys_in_record` VALUES (23, '000123', 100, '2023-06-24 18:18:47');
INSERT INTO `sys_in_record` VALUES (24, '000111', 100, '2023-06-24 18:19:37');
INSERT INTO `sys_in_record` VALUES (25, '000111', 100, '2023-06-24 18:21:29');
INSERT INTO `sys_in_record` VALUES (26, '000031', 100, '2023-06-24 18:22:41');
INSERT INTO `sys_in_record` VALUES (27, '000031', 50, '2023-06-24 18:25:19');
INSERT INTO `sys_in_record` VALUES (28, '000021', 100, '2023-06-25 22:57:23');
INSERT INTO `sys_in_record` VALUES (29, '000033', 200, '2023-06-27 13:56:17');
INSERT INTO `sys_in_record` VALUES (30, '000016', 20, '2023-06-27 14:01:39');
INSERT INTO `sys_in_record` VALUES (31, '000017', 122, '2023-06-27 14:22:23');
INSERT INTO `sys_in_record` VALUES (32, '000018', 100, '2023-06-27 14:33:46');
INSERT INTO `sys_in_record` VALUES (33, '000018', 50, '2023-06-27 14:36:14');
INSERT INTO `sys_in_record` VALUES (34, '000006', 3, '2023-06-27 15:26:18');
INSERT INTO `sys_in_record` VALUES (35, '012345', 123, '2023-06-27 15:32:42');
INSERT INTO `sys_in_record` VALUES (36, '011111', 123, '2023-06-27 15:33:17');

-- ----------------------------
-- Table structure for sys_inventory
-- ----------------------------
DROP TABLE IF EXISTS `sys_inventory`;
CREATE TABLE `sys_inventory`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `count` int NOT NULL COMMENT '商品数量',
  `price` float NOT NULL COMMENT '上架价格',
  `sellprice` float NOT NULL COMMENT '出售价格',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_inventory
-- ----------------------------
INSERT INTO `sys_inventory` VALUES (1, '000001', '罗技 G102 游戏鼠标', 21, 80, 100);
INSERT INTO `sys_inventory` VALUES (2, '000002', 'VGN V98Pro 键盘', 19, 400, 429);
INSERT INTO `sys_inventory` VALUES (7, '000003', '罗技 G502 游戏鼠标', 34, 240, 300);
INSERT INTO `sys_inventory` VALUES (9, '000004', '柯芝kzzi K68键盘', 139, 300, 350);
INSERT INTO `sys_inventory` VALUES (16, '000005', 'VGN s99键盘', 0, 300, 350);
INSERT INTO `sys_inventory` VALUES (17, '000006', 'test1', 63, 300.66, 350);
INSERT INTO `sys_inventory` VALUES (19, '000012', 'qwe', 99, 100.1, 123.4);
INSERT INTO `sys_inventory` VALUES (20, '000123', '统一绿茶', 98, 2, 3);
INSERT INTO `sys_inventory` VALUES (21, '000111', '统一冰红茶', 199, 2, 3);
INSERT INTO `sys_inventory` VALUES (23, '000031', '老坛酸菜牛肉面', 149, 3, 5);
INSERT INTO `sys_inventory` VALUES (25, '000021', 'aaa', 100, 120, 150);
INSERT INTO `sys_inventory` VALUES (26, '000033', 'ccc', 97, 10, 20);
INSERT INTO `sys_inventory` VALUES (27, '000016', 'eeee', 18, 12, 15);
INSERT INTO `sys_inventory` VALUES (28, '000017', 'llll', 117, 14, 19);
INSERT INTO `sys_inventory` VALUES (29, '000018', 'ppp', 144, 12, 15);
INSERT INTO `sys_inventory` VALUES (32, '012345', 'qwe', 117, 123, 123);
INSERT INTO `sys_inventory` VALUES (33, '011111', '123', 115, 123, 123);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_admin', '管理员');
INSERT INTO `sys_role` VALUES (2, 'ROLE_employee', '员工');

-- ----------------------------
-- Table structure for sys_sale_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_sale_record`;
CREATE TABLE `sys_sale_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `iid`(`product_id`) USING BTREE,
  CONSTRAINT `iid` FOREIGN KEY (`product_id`) REFERENCES `sys_inventory` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sale_record
-- ----------------------------
INSERT INTO `sys_sale_record` VALUES (1, '000001', 11, '2023-06-02 21:26:44');
INSERT INTO `sys_sale_record` VALUES (2, '000001', 1, '2023-06-03 10:36:28');
INSERT INTO `sys_sale_record` VALUES (3, '000001', 1, '2023-06-03 10:36:28');
INSERT INTO `sys_sale_record` VALUES (4, '000001', 1, '2023-06-03 10:42:44');
INSERT INTO `sys_sale_record` VALUES (5, '000001', 1, '2023-06-03 10:43:01');
INSERT INTO `sys_sale_record` VALUES (6, '000001', 1, '2023-06-03 10:43:13');
INSERT INTO `sys_sale_record` VALUES (7, '000001', 1, '2023-06-03 10:44:09');
INSERT INTO `sys_sale_record` VALUES (8, '000001', 1, '2023-06-03 10:44:23');
INSERT INTO `sys_sale_record` VALUES (9, '000002', 8, '2023-06-04 21:45:32');
INSERT INTO `sys_sale_record` VALUES (10, '000002', 1, '2023-05-01 21:49:44');
INSERT INTO `sys_sale_record` VALUES (11, '000003', 1, '2023-06-05 01:50:50');
INSERT INTO `sys_sale_record` VALUES (12, '000002', 1, '2022-06-06 16:45:03');
INSERT INTO `sys_sale_record` VALUES (13, '000003', 1, '2023-06-06 18:06:49');
INSERT INTO `sys_sale_record` VALUES (14, '000003', 1, '2023-06-06 18:07:06');
INSERT INTO `sys_sale_record` VALUES (15, '000003', 1, '2023-06-06 18:07:36');
INSERT INTO `sys_sale_record` VALUES (16, '000003', 1, '2023-06-24 20:30:44');
INSERT INTO `sys_sale_record` VALUES (17, '000001', 1, '2023-06-27 01:53:36');
INSERT INTO `sys_sale_record` VALUES (18, '000005', 1, '2023-06-27 01:56:06');
INSERT INTO `sys_sale_record` VALUES (19, '000002', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (20, '000001', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (21, '000004', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (22, '000005', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (23, '000003', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (24, '000006', 1, '2023-06-27 01:57:48');
INSERT INTO `sys_sale_record` VALUES (25, '000031', 1, '2023-06-27 01:57:49');
INSERT INTO `sys_sale_record` VALUES (26, '000021', 1, '2023-06-27 01:57:49');
INSERT INTO `sys_sale_record` VALUES (27, '000111', 1, '2023-06-27 01:57:49');
INSERT INTO `sys_sale_record` VALUES (28, '000012', 1, '2023-06-27 01:57:49');
INSERT INTO `sys_sale_record` VALUES (29, '000123', 1, '2023-06-27 01:57:49');
INSERT INTO `sys_sale_record` VALUES (30, '000001', 2, '2023-06-27 02:24:10');
INSERT INTO `sys_sale_record` VALUES (31, '000005', 1, '2023-06-27 02:25:16');
INSERT INTO `sys_sale_record` VALUES (32, '000123', 1, '2023-06-27 02:25:17');
INSERT INTO `sys_sale_record` VALUES (33, '000021', 1, '2023-06-27 02:26:59');
INSERT INTO `sys_sale_record` VALUES (34, '000021', 1, '2023-06-27 02:27:01');
INSERT INTO `sys_sale_record` VALUES (35, '000021', 1, '2023-06-27 02:27:03');
INSERT INTO `sys_sale_record` VALUES (36, '000021', 1, '2023-06-27 02:27:11');
INSERT INTO `sys_sale_record` VALUES (37, '000021', 1, '2023-06-27 02:27:58');
INSERT INTO `sys_sale_record` VALUES (38, '000021', 1, '2023-06-27 02:28:20');
INSERT INTO `sys_sale_record` VALUES (39, '000021', 1, '2023-06-27 02:28:45');
INSERT INTO `sys_sale_record` VALUES (40, '000021', 1, '2023-06-27 02:30:56');
INSERT INTO `sys_sale_record` VALUES (41, '000021', 1, '2023-06-27 02:36:15');
INSERT INTO `sys_sale_record` VALUES (42, '000021', 1, '2023-06-27 02:37:16');
INSERT INTO `sys_sale_record` VALUES (43, '000021', 1, '2023-06-27 02:37:24');
INSERT INTO `sys_sale_record` VALUES (44, '000001', 3, '2023-06-27 10:57:05');
INSERT INTO `sys_sale_record` VALUES (45, '000016', 2, '2023-06-27 14:25:12');
INSERT INTO `sys_sale_record` VALUES (46, '000017', 4, '2023-06-27 14:25:12');
INSERT INTO `sys_sale_record` VALUES (47, '000033', 3, '2023-06-27 14:25:12');
INSERT INTO `sys_sale_record` VALUES (48, '000017', 3, '2023-06-27 14:25:25');
INSERT INTO `sys_sale_record` VALUES (49, '000017', 1, '2023-06-27 14:30:41');
INSERT INTO `sys_sale_record` VALUES (50, '000017', 1, '2023-06-27 14:31:28');
INSERT INTO `sys_sale_record` VALUES (51, '000017', 3, '2023-06-27 14:37:04');
INSERT INTO `sys_sale_record` VALUES (52, '000018', 6, '2023-06-27 14:37:04');
INSERT INTO `sys_sale_record` VALUES (53, '012345', 6, '2023-06-27 15:39:11');
INSERT INTO `sys_sale_record` VALUES (54, '011111', 8, '2023-06-27 15:39:11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_account_expired` tinyint NULL DEFAULT NULL,
  `is_account_locked` tinyint NULL DEFAULT NULL,
  `is_credentials_expired` tinyint NULL DEFAULT NULL,
  `is_enable` tinyint NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$FM1QCF7qhIGE4bYaeyQrMujhOTbSFLSZkcWVBakJZiCDKlVfSMTfG', 0, 0, 0, 0, '\\images\\2fdb6ed2-d79c-442b-90e5-e3ce25e93b95.png');
INSERT INTO `sys_user` VALUES (48, 'user1', '$2a$10$ABk7Jzf8BUH/Ca0LtAM.vOZv7YE9Xil/0flpepEqbkk31yUKtK22.', 0, 0, 0, 0, NULL);
INSERT INTO `sys_user` VALUES (53, 'employee1', '$2a$10$Gd2/bnlRDBCYLJxhmMPGq.HhwGHi7WFB5OZJT26OYCJ7GvOJgaCX2', 0, 0, 0, 0, NULL);
INSERT INTO `sys_user` VALUES (56, 'employee4', '$2a$10$7tUAoqFnXo5IPEji/iPDyu678XWA40wG7Y1bVkSVM/wINYbH1PmQS', 0, 0, 0, 0, NULL);
INSERT INTO `sys_user` VALUES (58, 'employee7', '$2a$10$GFy5Pwiy/cQ0qpQxueRaw.DbwV7Rl1WBbySYgUBBHN/TXPu6DenT2', 0, 0, 0, 0, NULL);
INSERT INTO `sys_user` VALUES (59, 'employee18', '$2a$10$gGFLxBu4xxH0Dq76psoopuUpwjGAXhaeTjHvdOP4Pg62lmzXj1noG', 0, 0, 0, 0, NULL);
INSERT INTO `sys_user` VALUES (76, 'qwe', '$2a$10$WNBeYEdKia/T98oZAMMz6O5YI68kAZ.PbVPA2yCdV31QWlsVWTqUq', 0, 0, 0, 0, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (10, 12, 2);
INSERT INTO `sys_user_role` VALUES (16, 18, 2);
INSERT INTO `sys_user_role` VALUES (17, 19, 2);
INSERT INTO `sys_user_role` VALUES (45, 47, 2);
INSERT INTO `sys_user_role` VALUES (46, 48, 2);
INSERT INTO `sys_user_role` VALUES (51, 53, 2);
INSERT INTO `sys_user_role` VALUES (54, 56, 2);
INSERT INTO `sys_user_role` VALUES (56, 58, 2);
INSERT INTO `sys_user_role` VALUES (57, 59, 2);
INSERT INTO `sys_user_role` VALUES (74, 76, 2);

SET FOREIGN_KEY_CHECKS = 1;
