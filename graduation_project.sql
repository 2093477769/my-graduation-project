/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : graduation_project

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 15/02/2024 16:29:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deliveryman
-- ----------------------------
DROP TABLE IF EXISTS `deliveryman`;
CREATE TABLE `deliveryman`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `gender` int NOT NULL,
  `age` int NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `status` int NOT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deliveryman
-- ----------------------------
INSERT INTO `deliveryman` VALUES (1, '张三', 1, 23, '15811111111', 1, '2023-12-29 16:22:35', NULL);
INSERT INTO `deliveryman` VALUES (2, '李四', 1, 24, '13811111111', 1, '2023-12-29 22:15:00', '2023-12-30 20:23:51');
INSERT INTO `deliveryman` VALUES (6, '王五', 1, 25, '13811111111', 1, '2024-01-13 17:40:01', NULL);

-- ----------------------------
-- Table structure for express_company
-- ----------------------------
DROP TABLE IF EXISTS `express_company`;
CREATE TABLE `express_company`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `base_fee` decimal(8, 2) NOT NULL,
  `weight_fee` decimal(8, 2) NOT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of express_company
-- ----------------------------
INSERT INTO `express_company` VALUES (1, '顺丰速运', 12.00, 2.00, '2023-12-24 15:44:40', '2023-12-29 16:04:31');
INSERT INTO `express_company` VALUES (2, '圆通速递', 10.00, 2.00, '2023-12-24 15:45:23', '2023-12-29 16:06:56');
INSERT INTO `express_company` VALUES (18, '中通快递', 10.00, 2.00, '2023-12-29 16:06:39', NULL);
INSERT INTO `express_company` VALUES (19, '韵达快递', 11.00, 2.00, '2023-12-29 16:07:11', NULL);
INSERT INTO `express_company` VALUES (20, '申通快递', 11.00, 1.00, '2023-12-29 16:07:25', NULL);
INSERT INTO `express_company` VALUES (21, '邮政EMS', 15.00, 2.00, '2023-12-29 16:07:48', NULL);
INSERT INTO `express_company` VALUES (22, '百世快递', 12.00, 2.00, '2023-12-29 16:08:08', NULL);
INSERT INTO `express_company` VALUES (23, '天天快递', 10.00, 2.00, '2023-12-29 16:08:29', NULL);
INSERT INTO `express_company` VALUES (24, '京东物流', 15.00, 2.00, '2023-12-29 16:08:47', NULL);
INSERT INTO `express_company` VALUES (25, '德邦快递', 10.00, 1.00, '2023-12-29 16:09:03', '2024-01-13 17:52:03');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `status` int NOT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (3, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 1, '2024-01-08 15:48:00', '2024-01-08 19:50:51');
INSERT INTO `feedback` VALUES (4, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 1, '2024-01-08 15:48:00', '2024-01-08 19:08:46');
INSERT INTO `feedback` VALUES (13, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 15:44:00', NULL);
INSERT INTO `feedback` VALUES (14, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 15:30:00', NULL);
INSERT INTO `feedback` VALUES (15, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 15:44:00', NULL);
INSERT INTO `feedback` VALUES (16, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 15:44:00', NULL);
INSERT INTO `feedback` VALUES (17, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 17:00:00', NULL);
INSERT INTO `feedback` VALUES (18, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 17:00:00', NULL);
INSERT INTO `feedback` VALUES (19, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-20 17:00:00', NULL);
INSERT INTO `feedback` VALUES (20, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-26 22:02:05', NULL);
INSERT INTO `feedback` VALUES (21, 3, '当人们面临挑战和机遇时，总会有一些人害怕失败，而另一些人则会迎难而上。成功者之所以成功，并不是因为他们从未经历过失败，而是因为他们学会了从失败中汲取教训，并继续前进。生活就像一场旅程，有时候路途坎坷，但正是这些困难和挫折，激发了我们不断前行的勇气和决心。在未来的路途上，无论遇到什么样的困难，只要我们保持积极心态，始终相信自己，就一定能够克服一切困难，实现自己的梦想。', 0, '2024-01-26 22:02:51', NULL);

-- ----------------------------
-- Table structure for in_express
-- ----------------------------
DROP TABLE IF EXISTS `in_express`;
CREATE TABLE `in_express`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `company_id` int NOT NULL,
  `deliveryman_id` int NULL DEFAULT NULL,
  `status` int NOT NULL,
  `sender_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sender_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sender_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `company_id`(`company_id`) USING BTREE,
  CONSTRAINT `in_express_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `express_company` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of in_express
-- ----------------------------
INSERT INTO `in_express` VALUES (1, '/img/inExpressImg/0db40118-b4af-4e10-a8a3-a83d41a8d31d.jpg', 21, NULL, 0, '张三', '13811111111', 'aaaaaaaa', '李四', '13822222222', 'bbbbbbbbbb', '2024-01-21 18:31:40', '2024-01-21 18:31:40');
INSERT INTO `in_express` VALUES (2, '/img/inExpressImg/987b96a4-e82f-4184-aa14-31d93077e9c2.jpg', 22, 6, 2, '张三', '15811111111', '广东......', '李四', '13822222222', '郑州........', '2024-01-20 17:48:07', '2024-01-20 17:48:07');
INSERT INTO `in_express` VALUES (4, '/img/inExpressImg/f6a4ec11-6560-4175-9cf2-e2b115743e8a.jpg', 25, NULL, 0, '张三', '13511111111', '12faf', '李四', '13822222222', 'afniwng', '2024-01-29 21:51:22', NULL);
INSERT INTO `in_express` VALUES (5, '/img/inExpressImg/998094f2-bdba-4e4e-ac73-0169da4acb2a.jpg', 23, NULL, 0, 'zhangsan', '13511111111', 'asfagdsg', '李四', '13822222222', 'asdaf', '2024-01-29 21:57:09', NULL);

-- ----------------------------
-- Table structure for out_express
-- ----------------------------
DROP TABLE IF EXISTS `out_express`;
CREATE TABLE `out_express`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `company_id` int NOT NULL,
  `deliveryman_id` int NULL DEFAULT NULL,
  `status` int NOT NULL,
  `weight` double(8, 2) NOT NULL,
  `expense` decimal(8, 2) NULL DEFAULT NULL,
  `sender_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sender_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sender_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `receiver_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `company_id`(`company_id`) USING BTREE,
  CONSTRAINT `out_express_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `express_company` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of out_express
-- ----------------------------
INSERT INTO `out_express` VALUES (3, '/img/outExpressImg/79b5f3ed-acfe-4c63-80ca-cad7483201ac.jpg', 22, 2, 2, 1.20, 16.00, '张三', '13211111111', 'aaaaaaaaaa', '王五', '13511111111', 'bbbbbbbbbbbb', '2024-01-18 19:23:00', '2024-01-18 19:23:00');
INSERT INTO `out_express` VALUES (5, '/img/outExpressImg/88b8fd4a-c21e-4707-97d0-e9cac0b33861.jpg', 24, NULL, 0, 1.23, 21.00, '李四', '13822222222', '河南省郑州市惠济区英才街国基生活园区', '张三', '13211111111', '驻马店', '2024-02-15 14:52:57', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` int NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `update_time` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, '13500000000', NULL, 0, '/img/avatar/618061e5-d178-4702-b3aa-cc4fd37de410.jpg', '2023-12-18 14:59:46', '2024-01-29 21:50:00');
INSERT INTO `user` VALUES (3, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四', '13822222222', '河南省郑州市惠济区英才街国基生活园区', 1, '/img/avatar/64400c51-70af-45e8-a6e3-5ed4b555db09.jpg', '2023-12-20 16:47:27', '2023-12-23 23:30:58');
INSERT INTO `user` VALUES (4, 'admin1', 'e10adc3949ba59abbe56e057f20f883e', NULL, '13511111111', NULL, 0, '/img/avatar/64a49cd9-30da-40e7-af37-c12f3cc2f590.jpg', '2023-12-23 16:35:31', '2024-01-29 18:43:21');
INSERT INTO `user` VALUES (5, 'admin2', 'e10adc3949ba59abbe56e057f20f883e', NULL, '13522222222', NULL, 0, '/img/avatar/defaultAvatar.jpg', '2023-12-23 20:08:46', NULL);
INSERT INTO `user` VALUES (6, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '13211111111', '驻马店', 1, '/img/avatar/5a6caade-325a-4d45-9e06-8a18336a8b81.jpg', '2024-02-03 12:17:13', '2024-02-03 12:33:56');

SET FOREIGN_KEY_CHECKS = 1;
