/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.135.99
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : 192.168.135.99:3306
 Source Schema         : printer

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 06/06/2020 17:32:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yx_t_printer
-- ----------------------------
DROP TABLE IF EXISTS `yx_t_printer`;
CREATE TABLE `yx_t_printer`  (
  `id` int(11) NOT NULL,
  `mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `port` int(11) NOT NULL,
  `key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `editor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isValid` int(11) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yx_t_printer
-- ----------------------------
INSERT INTO `yx_t_printer` VALUES (1, 'IT部测试用佳博打印机1', '192.168.36.254', 9100, 'ZB_CS_1', '2020-05-15 18:52:59', '熊', 1);
INSERT INTO `yx_t_printer` VALUES (6, '五里交叉带打印机1', '192.168.114.201', 9100, 'WL_JCD_1', '2020-05-22 10:19:35', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (7, '五里交叉带打印机2', '192.168.114.202', 9100, 'WL_JCD_2', '2020-05-22 10:19:39', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (8, '五里交叉带打印机3', '192.168.114.203', 9100, 'WL_JCD_3', '2020-05-22 10:19:41', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (9, '五里交叉带打印机4', '192.168.114.204', 9100, 'WL_JCD_4', '2020-05-22 10:19:43', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (10, '五里交叉带打印机5', '192.168.114.205', 9100, 'WL_JCD_5', '2020-05-22 10:19:45', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (11, '五里交叉带打印机6', '192.168.114.206', 9100, 'WL_JCD_6', '2020-05-22 10:19:49', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (12, '五里交叉带打印机7', '192.168.114.207', 9100, 'WL_JCD_7', '2020-05-22 10:19:51', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (13, '五里交叉带打印机8', '192.168.114.208', 9100, 'WL_JCD_8', '2020-05-22 10:19:53', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (14, '五里交叉带打印机9', '192.168.114.209', 9100, 'WL_JCD_9', '2020-05-22 10:19:55', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (15, '五里交叉带打印机10', '192.168.114.210', 9100, 'WL_JCD_10', '2020-05-22 10:20:02', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (16, '五里交叉带打印机11', '192.168.114.211', 9100, 'WL_JCD_11', '2020-05-22 10:20:06', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (17, '五里交叉带打印机12', '192.168.114.212', 9100, 'WL_JCD_12', '2020-05-22 10:20:10', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (18, '五里交叉带打印机13', '192.168.114.213', 9100, 'WL_JCD_13', '2020-05-22 10:20:14', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (19, '五里交叉带打印机14', '192.168.114.214', 9100, 'WL_JCD_14', '2020-05-22 10:20:18', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (20, '五里交叉带打印机15', '192.168.114.215', 9100, 'WL_JCD_15', '2020-05-22 10:20:20', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (21, '五里交叉带打印机16', '192.168.114.216', 9100, 'WL_JCD_16', '2020-05-22 10:20:23', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (22, '五里交叉带打印机17', '192.168.114.217', 9100, 'WL_JCD_17', '2020-05-22 10:20:24', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (23, '五里交叉带打印机18', '192.168.114.218', 9100, 'WL_JCD_18', '2020-05-22 10:20:26', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (24, '五里交叉带打印机19', '192.168.114.219', 9100, 'WL_JCD_19', '2020-05-22 10:20:28', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (25, '五里交叉带打印机20', '192.168.114.220', 9100, 'WL_JCD_20', '2020-05-22 10:20:30', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (26, '五里交叉带打印机21', '192.168.114.221', 9100, 'WL_JCD_21', '2020-05-22 10:20:33', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (27, '五里交叉带打印机22', '192.168.114.222', 9100, 'WL_JCD_22', '2020-05-22 10:20:43', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (28, '五里交叉带打印机23', '192.168.114.223', 9100, 'WL_JCD_23', '2020-05-22 10:20:46', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (29, '五里交叉带打印机24', '192.168.114.224', 9100, 'WL_JCD_24', '2020-05-22 10:20:50', '薛灵敏', 1);
INSERT INTO `yx_t_printer` VALUES (30, '五里交叉带打印机25', '192.168.114.225', 9100, 'WL_JCD_25', '2020-05-22 10:20:54', '薛灵敏', 1);

-- ----------------------------
-- Table structure for yx_t_printerconf
-- ----------------------------
DROP TABLE IF EXISTS `yx_t_printerconf`;
CREATE TABLE `yx_t_printerconf`  (
  `id` int(11) NOT NULL COMMENT '距离单位转换为：1mm=8dot，设置时均设置成dot',
  `bid` int(11) NOT NULL DEFAULT 0 COMMENT '关联表ID',
  `templateid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '模板ID',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '打印顺序',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '打印名',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'text' COMMENT '打印类型',
  `defaults_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '默认值',
  `wrap` int(11) NOT NULL DEFAULT 0 COMMENT '是否换行，0不换行，1换行，-1倒退一行',
  `position` int(11) NOT NULL DEFAULT 0 COMMENT '定位方式，0相对定位，1绝对定位',
  `width` int(11) NOT NULL DEFAULT 0 COMMENT '打印内容的宽度',
  `height` int(11) NOT NULL DEFAULT 50 COMMENT '打印内容的高度',
  `row_height` int(11) NOT NULL DEFAULT 0 COMMENT '行高',
  `size` int(11) NOT NULL DEFAULT 1 COMMENT '打印大小',
  `rotate` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '旋转角度',
  `left_offset` int(11) NOT NULL DEFAULT 10 COMMENT '左偏移量',
  `top_offset` int(11) NOT NULL DEFAULT 0 COMMENT '上偏移量，如果是绝对定位，该值就是y坐标的值，如果是相对定位，该值为行内的上偏移量',
  `enable` int(11) NOT NULL DEFAULT 1 COMMENT '是否启用，0禁用，1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yx_t_printerconf
-- ----------------------------
INSERT INTO `yx_t_printerconf` VALUES (1, 0, '1', 1, 'title', 'text', '', 1, 0, 0, 0, 100, 2, 0, 320, 20, 1);
INSERT INTO `yx_t_printerconf` VALUES (2, 0, '1', 2, 'storeName', 'text', '平台店铺：', 1, 0, 0, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (3, 0, '1', 3, 'orderid', 'text', '订单编号：', 0, 0, 400, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (4, 0, '1', 4, 'orderTime', 'text', '下单时间：', 1, 0, 0, 0, 50, 1, 0, 400, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (5, 0, '1', 5, 'memberName', 'text', '会员名：', 0, 0, 400, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (6, 0, '1', 6, 'transactionNumber', 'text', '交易号：', 1, 0, 0, 0, 50, 1, 0, 400, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (7, 0, '1', 7, 'receiver', 'text', '收货人：', 0, 0, 400, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (8, 0, '1', 8, 'contactNumber', 'text', '联系电话：', 1, 0, 0, 0, 50, 1, 0, 400, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (9, 0, '1', 9, 'receiverAddress', 'text', '收货地址：', 1, 0, 0, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (10, 0, '1', 10, 'defaults', 'line', '2', 1, 0, 800, 2, 25, 2, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (11, 0, '1', 11, 'defaults', 'text', '货号', 0, 0, 150, 0, 50, 1, 0, 50, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (12, 0, '1', 12, 'defaults', 'text', '尺码', 0, 0, 150, 0, 50, 1, 0, 210, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (13, 0, '1', 13, 'defaults', 'text', '数量', 1, 0, 150, 0, 50, 1, 0, 370, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (14, 0, '1', 14, 'orderList', 'list', '2', 1, 0, 0, 0, 0, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (15, 0, '1', 15, 'defaults', 'text', '合计', 0, 0, 150, 0, 50, 1, 0, 50, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (16, 0, '1', 16, 'sum', 'text', '', 1, 0, 0, 0, 50, 1, 0, 370, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (17, 0, '1', 17, 'defaults', 'line', '2', 1, 0, 800, 2, 25, 2, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (18, 0, '1', 18, 'defaults', 'text', '售后服务：如需退换，请随货品一同返回发货清单。如订单有赠品，退货', 1, 0, 0, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (19, 0, '1', 19, 'defaults', 'text', '时请连同赠品一并退回！感谢您的合作！有疑问请联系在线客服或拨打客', 1, 0, 0, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (20, 0, '1', 20, 'defaults', 'text', '服电话 0595-82037299  0595-8203767', 1, 0, 0, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (21, 0, '1', 21, 'defaults', 'line', '2', 1, 0, 800, 2, 25, 2, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (22, 0, '1', 22, 'defaults', 'text', '退换货原因：', 0, 0, 200, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (23, 0, '1', 23, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 180, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (24, 0, '1', 24, 'defaults', 'text', '尺寸不合适', 0, 0, 200, 0, 50, 1, 0, 210, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (25, 0, '1', 25, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 380, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (26, 0, '1', 26, 'defaults', 'text', '款式不喜欢', 0, 0, 200, 0, 50, 1, 0, 410, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (27, 0, '1', 27, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 580, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (28, 0, '1', 28, 'defaults', 'text', '质量问题', 1, 0, 200, 0, 50, 1, 0, 610, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (29, 0, '1', 29, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (30, 0, '1', 30, 'defaults', 'text', '发错货', 0, 0, 200, 0, 50, 1, 0, 40, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (31, 0, '1', 31, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 180, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (32, 0, '1', 32, 'defaults', 'text', '对快递不满意', 0, 0, 200, 0, 50, 1, 0, 210, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (33, 0, '1', 33, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 380, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (34, 0, '1', 34, 'defaults', 'text', '其他请注明：_ _ _ _ _', 1, 0, 0, 0, 50, 1, 0, 410, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (35, 0, '2', 1, 'id', 'text', '', 0, 0, 150, 0, 50, 1, 0, 10, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (36, 0, '2', 2, 'size', 'text', '', 0, 0, 150, 0, 50, 1, 0, 210, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (37, 0, '2', 3, 'num', 'text', '', 0, 0, 150, 0, 50, 1, 0, 370, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (38, 0, '2', 4, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 450, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (39, 0, '2', 5, 'defaults', 'text', '退货', 0, 0, 80, 0, 50, 1, 0, 480, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (40, 0, '2', 6, 'defaults', 'rectangle', '2', 0, 0, 20, 20, 50, 2, 0, 560, 0, 1);
INSERT INTO `yx_t_printerconf` VALUES (41, 0, '2', 7, 'defaults', 'text', '换货', 1, 0, 80, 0, 50, 1, 0, 590, 0, 1);

-- ----------------------------
-- Table structure for yx_t_printerloc
-- ----------------------------
DROP TABLE IF EXISTS `yx_t_printerloc`;
CREATE TABLE `yx_t_printerloc`  (
  `id` int(11) NOT NULL,
  `printerkey` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `locid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `editor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NonClusteredIndex-20200523-105111`(`locid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yx_t_printerloc
-- ----------------------------
INSERT INTO `yx_t_printerloc` VALUES (1, 'ZB_CS_1', 'T1', '2020-05-19 10:10:21', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (15, 'WL_JCD_1', '0001', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (16, 'WL_JCD_1', '0002', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (17, 'WL_JCD_1', '0003', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (18, 'WL_JCD_1', '0004', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (19, 'WL_JCD_1', '0005', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (20, 'WL_JCD_2', '0006', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (21, 'WL_JCD_2', '0007', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (22, 'WL_JCD_2', '0008', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (23, 'WL_JCD_2', '0009', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (24, 'WL_JCD_2', '0010', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (25, 'WL_JCD_3', '0011', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (26, 'WL_JCD_3', '0012', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (27, 'WL_JCD_3', '0013', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (28, 'WL_JCD_3', '0014', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (29, 'WL_JCD_3', '0015', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (30, 'WL_JCD_4', '0016', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (31, 'WL_JCD_4', '0017', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (32, 'WL_JCD_4', '0018', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (33, 'WL_JCD_4', '0019', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (34, 'WL_JCD_4', '0020', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (35, 'WL_JCD_5', '0021', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (36, 'WL_JCD_5', '0022', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (37, 'WL_JCD_5', '0023', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (38, 'WL_JCD_5', '0024', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (39, 'WL_JCD_5', '0025', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (40, 'WL_JCD_6', '0026', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (41, 'WL_JCD_6', '0027', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (42, 'WL_JCD_6', '0028', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (43, 'WL_JCD_6', '0029', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (44, 'WL_JCD_6', '0030', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (45, 'WL_JCD_7', '0031', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (46, 'WL_JCD_7', '0032', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (47, 'WL_JCD_7', '0033', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (48, 'WL_JCD_7', '0034', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (49, 'WL_JCD_7', '0035', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (50, 'WL_JCD_8', '0036', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (51, 'WL_JCD_8', '0037', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (52, 'WL_JCD_8', '0038', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (53, 'WL_JCD_8', '0039', '2020-05-22 10:34:00', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (54, 'WL_JCD_8', '0040', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (55, 'WL_JCD_9', '0041', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (56, 'WL_JCD_9', '0042', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (57, 'WL_JCD_9', '0043', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (58, 'WL_JCD_9', '0044', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (59, 'WL_JCD_9', '0045', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (60, 'WL_JCD_10', '0046', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (61, 'WL_JCD_10', '0047', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (62, 'WL_JCD_10', '0048', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (63, 'WL_JCD_10', '0049', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (64, 'WL_JCD_10', '0050', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (65, 'WL_JCD_11', '0051', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (66, 'WL_JCD_11', '0052', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (67, 'WL_JCD_11', '0053', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (68, 'WL_JCD_11', '0054', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (69, 'WL_JCD_11', '0055', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (70, 'WL_JCD_12', '0056', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (71, 'WL_JCD_12', '0057', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (72, 'WL_JCD_12', '0058', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (73, 'WL_JCD_12', '0059', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (74, 'WL_JCD_12', '0060', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (75, 'WL_JCD_13', '1001', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (76, 'WL_JCD_13', '1002', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (77, 'WL_JCD_13', '1003', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (78, 'WL_JCD_13', '1004', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (79, 'WL_JCD_13', '1005', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (80, 'WL_JCD_14', '1006', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (81, 'WL_JCD_14', '1007', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (82, 'WL_JCD_14', '1008', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (83, 'WL_JCD_14', '1009', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (84, 'WL_JCD_14', '1010', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (85, 'WL_JCD_15', '1011', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (86, 'WL_JCD_15', '1012', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (87, 'WL_JCD_15', '1013', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (88, 'WL_JCD_15', '1014', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (89, 'WL_JCD_15', '1015', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (90, 'WL_JCD_16', '1016', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (91, 'WL_JCD_16', '1017', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (92, 'WL_JCD_16', '1018', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (93, 'WL_JCD_16', '1019', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (94, 'WL_JCD_16', '1020', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (95, 'WL_JCD_17', '1021', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (96, 'WL_JCD_17', '1022', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (97, 'WL_JCD_17', '1023', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (98, 'WL_JCD_17', '1024', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (99, 'WL_JCD_17', '1025', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (100, 'WL_JCD_18', '1026', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (101, 'WL_JCD_18', '1027', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (102, 'WL_JCD_18', '1028', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (103, 'WL_JCD_18', '1029', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (104, 'WL_JCD_18', '1030', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (105, 'WL_JCD_19', '1031', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (106, 'WL_JCD_19', '1032', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (107, 'WL_JCD_19', '1033', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (108, 'WL_JCD_19', '1034', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (109, 'WL_JCD_19', '1035', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (110, 'WL_JCD_20', '1036', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (111, 'WL_JCD_20', '1037', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (112, 'WL_JCD_20', '1038', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (113, 'WL_JCD_20', '1039', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (114, 'WL_JCD_20', '1040', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (115, 'WL_JCD_21', '1041', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (116, 'WL_JCD_21', '1042', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (117, 'WL_JCD_21', '1043', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (118, 'WL_JCD_21', '1044', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (119, 'WL_JCD_21', '1045', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (120, 'WL_JCD_22', '1046', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (121, 'WL_JCD_22', '1047', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (122, 'WL_JCD_22', '1048', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (123, 'WL_JCD_22', '1049', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (124, 'WL_JCD_22', '1050', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (125, 'WL_JCD_23', '1051', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (126, 'WL_JCD_23', '1052', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (127, 'WL_JCD_23', '1053', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (128, 'WL_JCD_23', '1054', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (129, 'WL_JCD_23', '1055', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (130, 'WL_JCD_24', '1056', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (131, 'WL_JCD_24', '1057', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (132, 'WL_JCD_24', '1058', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (133, 'WL_JCD_24', '1059', '2020-05-22 10:34:01', '薛灵敏');
INSERT INTO `yx_t_printerloc` VALUES (134, 'WL_JCD_24', '1060', '2020-05-22 10:34:01', '薛灵敏');

SET FOREIGN_KEY_CHECKS = 1;
