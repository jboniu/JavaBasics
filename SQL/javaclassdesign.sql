/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : javaclassdesign

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 14/12/2023 13:31:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `aid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `aname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apassword` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('111', '管理员1', 'password1');
INSERT INTO `administrator` VALUES ('666', '管理员6', 'password6');
INSERT INTO `administrator` VALUES ('888', '管理员8', 'password8');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit` int NULL DEFAULT NULL COMMENT '选课人数',
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `tid`(`tid` ASC) USING BTREE,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('9090001', '语文', '229001', 9);
INSERT INTO `course` VALUES ('9090002', '数学', '229002', 9);
INSERT INTO `course` VALUES ('9090003', '英语', '229003', 9);
INSERT INTO `course` VALUES ('9090004', '物理', '229004', 3);
INSERT INTO `course` VALUES ('9090005', '化学', '229005', 4);
INSERT INTO `course` VALUES ('9090006', '生物', '229006', 5);
INSERT INTO `course` VALUES ('9090007', '历史', '229007', 6);
INSERT INTO `course` VALUES ('9090008', '地理', '229008', 4);
INSERT INTO `course` VALUES ('9090009', '政治', '229009', 5);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `sid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`sid`, `cid`) USING BTREE,
  INDEX `cid`(`cid` ASC) USING BTREE,
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('210101', '9090001', 112);
INSERT INTO `grade` VALUES ('210101', '9090002', 102);
INSERT INTO `grade` VALUES ('210101', '9090003', 125);
INSERT INTO `grade` VALUES ('210101', '9090004', 78);
INSERT INTO `grade` VALUES ('210101', '9090005', 86);
INSERT INTO `grade` VALUES ('210101', '9090006', NULL);
INSERT INTO `grade` VALUES ('210101', '9090007', NULL);
INSERT INTO `grade` VALUES ('210101', '9090008', 82);
INSERT INTO `grade` VALUES ('210101', '9090009', NULL);
INSERT INTO `grade` VALUES ('210102', '9090001', 105);
INSERT INTO `grade` VALUES ('210102', '9090002', 136);
INSERT INTO `grade` VALUES ('210102', '9090003', 110);
INSERT INTO `grade` VALUES ('210102', '9090004', 62);
INSERT INTO `grade` VALUES ('210102', '9090005', 88);
INSERT INTO `grade` VALUES ('210102', '9090006', 90);
INSERT INTO `grade` VALUES ('210102', '9090007', 72);
INSERT INTO `grade` VALUES ('210102', '9090008', 71);
INSERT INTO `grade` VALUES ('210102', '9090009', 66);
INSERT INTO `grade` VALUES ('210103', '9090001', 95);
INSERT INTO `grade` VALUES ('210103', '9090002', 99);
INSERT INTO `grade` VALUES ('210103', '9090003', 112);
INSERT INTO `grade` VALUES ('210103', '9090004', 66);
INSERT INTO `grade` VALUES ('210103', '9090005', NULL);
INSERT INTO `grade` VALUES ('210103', '9090006', 72);
INSERT INTO `grade` VALUES ('210103', '9090007', NULL);
INSERT INTO `grade` VALUES ('210103', '9090008', 69);
INSERT INTO `grade` VALUES ('210103', '9090009', NULL);
INSERT INTO `grade` VALUES ('210104', '9090001', 88);
INSERT INTO `grade` VALUES ('210104', '9090002', 105);
INSERT INTO `grade` VALUES ('210104', '9090003', 115);
INSERT INTO `grade` VALUES ('210104', '9090004', 62);
INSERT INTO `grade` VALUES ('210104', '9090005', 74);
INSERT INTO `grade` VALUES ('210104', '9090006', 78);
INSERT INTO `grade` VALUES ('210104', '9090007', NULL);
INSERT INTO `grade` VALUES ('210104', '9090008', NULL);
INSERT INTO `grade` VALUES ('210104', '9090009', NULL);
INSERT INTO `grade` VALUES ('210105', '9090001', 93);
INSERT INTO `grade` VALUES ('210105', '9090002', 95);
INSERT INTO `grade` VALUES ('210105', '9090003', 83);
INSERT INTO `grade` VALUES ('210105', '9090004', NULL);
INSERT INTO `grade` VALUES ('210105', '9090005', NULL);
INSERT INTO `grade` VALUES ('210105', '9090006', NULL);
INSERT INTO `grade` VALUES ('210105', '9090007', 73);
INSERT INTO `grade` VALUES ('210105', '9090008', 62);
INSERT INTO `grade` VALUES ('210105', '9090009', 60);
INSERT INTO `grade` VALUES ('210106', '9090001', 95);
INSERT INTO `grade` VALUES ('210106', '9090002', 85);
INSERT INTO `grade` VALUES ('210106', '9090003', 100);
INSERT INTO `grade` VALUES ('210106', '9090004', 68);
INSERT INTO `grade` VALUES ('210106', '9090005', NULL);
INSERT INTO `grade` VALUES ('210106', '9090006', 82);
INSERT INTO `grade` VALUES ('210106', '9090007', NULL);
INSERT INTO `grade` VALUES ('210106', '9090008', NULL);
INSERT INTO `grade` VALUES ('210106', '9090009', 76);
INSERT INTO `grade` VALUES ('210107', '9090001', 99);
INSERT INTO `grade` VALUES ('210107', '9090002', 123);
INSERT INTO `grade` VALUES ('210107', '9090003', 105);
INSERT INTO `grade` VALUES ('210107', '9090004', 81);
INSERT INTO `grade` VALUES ('210107', '9090005', 88);
INSERT INTO `grade` VALUES ('210107', '9090006', 78);
INSERT INTO `grade` VALUES ('210107', '9090007', NULL);
INSERT INTO `grade` VALUES ('210107', '9090008', NULL);
INSERT INTO `grade` VALUES ('210107', '9090009', NULL);
INSERT INTO `grade` VALUES ('210108', '9090001', 128);
INSERT INTO `grade` VALUES ('210108', '9090002', 95);
INSERT INTO `grade` VALUES ('210108', '9090003', 132);
INSERT INTO `grade` VALUES ('210108', '9090004', 66);
INSERT INTO `grade` VALUES ('210108', '9090005', 75);
INSERT INTO `grade` VALUES ('210108', '9090006', 83);
INSERT INTO `grade` VALUES ('210108', '9090007', 81);
INSERT INTO `grade` VALUES ('210108', '9090008', 78);
INSERT INTO `grade` VALUES ('210108', '9090009', 80);
INSERT INTO `grade` VALUES ('210109', '9090001', 121);
INSERT INTO `grade` VALUES ('210109', '9090002', 119);
INSERT INTO `grade` VALUES ('210109', '9090003', 116);
INSERT INTO `grade` VALUES ('210109', '9090004', 72);
INSERT INTO `grade` VALUES ('210109', '9090005', 78);
INSERT INTO `grade` VALUES ('210109', '9090006', 84);
INSERT INTO `grade` VALUES ('210109', '9090007', NULL);
INSERT INTO `grade` VALUES ('210109', '9090008', NULL);
INSERT INTO `grade` VALUES ('210109', '9090009', NULL);
INSERT INTO `grade` VALUES ('210110', '9090001', 89);
INSERT INTO `grade` VALUES ('210110', '9090002', 100);
INSERT INTO `grade` VALUES ('210110', '9090003', 94);
INSERT INTO `grade` VALUES ('210110', '9090004', 68);
INSERT INTO `grade` VALUES ('210110', '9090005', 72);
INSERT INTO `grade` VALUES ('210110', '9090006', 77);
INSERT INTO `grade` VALUES ('210110', '9090007', NULL);
INSERT INTO `grade` VALUES ('210110', '9090008', NULL);
INSERT INTO `grade` VALUES ('210110', '9090009', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `spassword` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sclass` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('210101', '现读1', '210101', '女', '高三（2）班', '18');
INSERT INTO `student` VALUES ('210102', '现读2', '210102', '女', '高一（1）班', '16');
INSERT INTO `student` VALUES ('210103', '现读3', '210103', '女', '高三（2）班', '18');
INSERT INTO `student` VALUES ('210104', '现读4', '210104', '女', '高二（1）班', '17');
INSERT INTO `student` VALUES ('210105', '现读5', '210104', '女', '高三（2）班', '18');
INSERT INTO `student` VALUES ('210106', '现读6', '210106', '女', '高三（2）班', '18');
INSERT INTO `student` VALUES ('210107', '现读7', '210107', '女', '高三（1）班', '19');
INSERT INTO `student` VALUES ('210108', '现读8', '210108', '女', '高一（1）班', '15');
INSERT INTO `student` VALUES ('210109', '现读9', '210109', '女', '高三（1）班', '19');
INSERT INTO `student` VALUES ('210110', '现读10', '210110', '女', '高三（2）班', '18');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tpassword` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1111', '张静', '1111', '11', '女');
INSERT INTO `teacher` VALUES ('229001', '任教1', '1234', '42', '男');
INSERT INTO `teacher` VALUES ('229002', '任教2', '229002', '34', '女');
INSERT INTO `teacher` VALUES ('229003', '任教3', '229003', '26', '女');
INSERT INTO `teacher` VALUES ('229004', '任教4', '229004', '29', '男');
INSERT INTO `teacher` VALUES ('229005', '任教5', '229005', '39', '女');
INSERT INTO `teacher` VALUES ('229006', '任教6', '229006', '44', '女');
INSERT INTO `teacher` VALUES ('229007', '任教7', '229007', '32', '女');
INSERT INTO `teacher` VALUES ('229008', '任教8', '229008', '45', '女');
INSERT INTO `teacher` VALUES ('229009', '任教9', '229009', '38', '男');

SET FOREIGN_KEY_CHECKS = 1;
