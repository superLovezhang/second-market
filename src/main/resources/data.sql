/*
Navicat MySQL Data Transfer

Source Server         : a
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sencond-market

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-04-11 16:27:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL COMMENT '评论主键',
  `reply_id` varchar(255) DEFAULT '0' COMMENT '回复id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `goods_id` varchar(255) DEFAULT NULL COMMENT '商品id',
  `content` varchar(255) DEFAULT NULL COMMENT '商品内容',
  `reply_username` varchar(255) DEFAULT NULL COMMENT '回复的评论昵称',
  `username` varchar(255) DEFAULT NULL COMMENT '评论人昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '评论人头像',
  `create_time` datetime DEFAULT NULL COMMENT '发表日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1260154915209125890', '0', '1259105950065770498', '1259673468878761986', '真的假的？这么便宜', null, '海绵宝宝', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/10/abf49e356a4e4d319917395440708114gudu.jpg', '2020-05-12 18:28:39');
INSERT INTO `comment` VALUES ('1260158033627992065', '1260154915209125890', '1260156298884849666', '1259673468878761986', '不要信，这是个骗子', '海绵宝宝', '默认昵称2ffbc', 'https://iknow-pic.cdn.bcebos.com/b3b7d0a20cf431adeef1310e4d36acaf2edd98e1?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1', '2020-05-12 18:41:02');
INSERT INTO `comment` VALUES ('1260158426789466113', '1260154915209125890', '1259105950065770498', '1259673468878761986', '真的吗？谢谢你了', '默认昵称2ffbc', '海绵宝宝', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/10/abf49e356a4e4d319917395440708114gudu.jpg', '2020-05-12 18:42:36');
INSERT INTO `comment` VALUES ('1260159016781238273', '1260154915209125890', '1260156298884849666', '1259673468878761986', '是真的，我被骗过', '海绵宝宝', '默认昵称2ffbc', 'https://iknow-pic.cdn.bcebos.com/b3b7d0a20cf431adeef1310e4d36acaf2edd98e1?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1', '2020-05-12 18:44:57');
INSERT INTO `comment` VALUES ('1260159671977660418', '1260154915209125890', '1260159109634740225', '1259673468878761986', '你又被骗了？傻逼', '默认昵称2ffbc', '默认昵称8e0a5', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/12/4bcc2d537de5455bb0be4417ebc2bbab奔跑.jpg', '2020-05-12 18:47:33');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(20) NOT NULL COMMENT '商品id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '商品封面',
  `intro` varchar(255) DEFAULT NULL COMMENT '商品介绍',
  `buy_price` decimal(10,2) DEFAULT NULL COMMENT '购买价格',
  `sale_price` decimal(10,2) DEFAULT NULL COMMENT '出售价格',
  `parent_subject` varchar(255) DEFAULT NULL COMMENT '二级分类',
  `subject` varchar(255) DEFAULT NULL COMMENT '二级分类',
  `status` int(1) DEFAULT '0' COMMENT '商品状态 0-上架 1-下架',
  `sold_status` int(1) DEFAULT '0' COMMENT '商品是否售出 0-未售出 1-已售出',
  `recommend` int(1) DEFAULT '0' COMMENT '是否推荐 0-未推荐 1-已推荐',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `school` varchar(255) DEFAULT NULL COMMENT '学校',
  `view_count` int(20) DEFAULT '0' COMMENT '浏览次数',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除 0-正常 1-删除',
  `update_time` datetime DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('111111111', '1', 'dsf', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/09/d57099b5cf9e4a5692f049715f901d67gudu.jpg', 'jjj', '8999.00', '999.00', '1', '', '0', '0', '0', 'http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg', '一路向东', '电子信息工程啊啊啊啊啊', '武汉交通职业学院', '0', '0', '2020-05-09 15:37:40');
INSERT INTO `goods` VALUES ('1259440404617617410', '1259105950065770498', '小米8', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/10/ff9489102bf54cfd84df1f623f3af886love.png', '小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8小米8', '8899.00', '888.00', '1', '14', '0', '1', '0', 'http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg', '一路向东', '计算机信息工程', '武汉交通职业学院', '6', '0', '2020-05-13 09:56:51');
INSERT INTO `goods` VALUES ('1259673468878761986', '1259105950065770498', '雅迪电瓶车', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/11/594072c600aa4e95baea52f53415b9caa555d85f4175a1d8.jpg', '买来一年了，要出去打工用不着了，低价卖吧。哈哈哈哈哈', '5688.00', '2000.00', '9', '31', '0', '0', '0', 'http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg', '一路向东', '计算机信息工程', '武汉交通职业学院', '83', '0', '2020-05-11 10:35:33');
INSERT INTO `goods` VALUES ('22222222', '2', 'ad', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/09/d57099b5cf9e4a5692f049715f901d67gudu.jpg', 'asdw54', '11515.00', '15.00', '2', null, '0', '0', '0', 'http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg', '一路向东', '电子信息工程', '武汉交通职业学院', '2', '0', '2020-05-09 20:00:15');
INSERT INTO `goods` VALUES ('333333333', '3', 'a', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/09/d57099b5cf9e4a5692f049715f901d67gudu.jpg', 'asdw54', '11515.00', '123.00', '3', null, '0', '0', '0', 'http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg', '一路向东', '电子信息工程', '武汉交通职业学院', '0', '0', '2020-05-09 15:37:40');

-- ----------------------------
-- Table structure for hotword
-- ----------------------------
DROP TABLE IF EXISTS `hotword`;
CREATE TABLE `hotword` (
  `id` varchar(255) NOT NULL COMMENT '热词主键id',
  `keyword` varchar(255) DEFAULT NULL COMMENT '热词关键词',
  `count` int(11) DEFAULT '0' COMMENT '热词搜索次数',
  `create_time` datetime DEFAULT NULL COMMENT '热词创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotword
-- ----------------------------
INSERT INTO `hotword` VALUES ('1259695489800998914', '小米', '3', '2020-05-11 12:03:03');
INSERT INTO `hotword` VALUES ('1259696360987299841', '电瓶车', '0', '2020-05-11 12:06:31');
INSERT INTO `hotword` VALUES ('1259696404033441794', '滑板鞋', '1', '2020-05-11 12:06:41');
INSERT INTO `hotword` VALUES ('1259696497503506433', '神舟笔记本', '0', '2020-05-11 12:07:04');
INSERT INTO `hotword` VALUES ('1259696538246975489', '华为p20', '0', '2020-05-11 12:07:13');
INSERT INTO `hotword` VALUES ('1259696603892027393', '哑铃', '0', '2020-05-11 12:07:29');
INSERT INTO `hotword` VALUES ('1260160646524194818', '', '0', '2020-05-12 18:51:25');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(20) NOT NULL COMMENT '举报表主键id',
  `report_content` varchar(255) DEFAULT NULL COMMENT '举报信息',
  `user_id` varchar(20) NOT NULL COMMENT '举报用户id',
  `goods_id` varchar(20) NOT NULL COMMENT '举报商品id',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('1259778539977211906', '涉嫌诈骗！', '1259105950065770498', '1259673468878761986', '2020-05-11 17:33:04');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `pid` int(11) DEFAULT '0' COMMENT '一级分类id',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '0', '手机');
INSERT INTO `subject` VALUES ('2', '0', '电脑');
INSERT INTO `subject` VALUES ('3', '0', '影音娱乐');
INSERT INTO `subject` VALUES ('4', '0', '数码配件');
INSERT INTO `subject` VALUES ('5', '0', '运动健身');
INSERT INTO `subject` VALUES ('6', '0', '衣物鞋帽');
INSERT INTO `subject` VALUES ('7', '0', '生活娱乐');
INSERT INTO `subject` VALUES ('8', '0', '图书教材');
INSERT INTO `subject` VALUES ('9', '0', '交通出行');
INSERT INTO `subject` VALUES ('10', '0', '个人技能');
INSERT INTO `subject` VALUES ('11', '0', '其他');
INSERT INTO `subject` VALUES ('12', '1', '翻盖机');
INSERT INTO `subject` VALUES ('13', '1', '老人机');
INSERT INTO `subject` VALUES ('14', '1', '全面屏');
INSERT INTO `subject` VALUES ('15', '2', '台式机');
INSERT INTO `subject` VALUES ('16', '2', '笔记本');
INSERT INTO `subject` VALUES ('17', '1', '平板');
INSERT INTO `subject` VALUES ('18', '3', 'CD片');
INSERT INTO `subject` VALUES ('19', '4', '耳机');
INSERT INTO `subject` VALUES ('20', '3', '照相机');
INSERT INTO `subject` VALUES ('21', '4', '充电宝');
INSERT INTO `subject` VALUES ('22', '4', '手机壳');
INSERT INTO `subject` VALUES ('23', '5', '哑铃');
INSERT INTO `subject` VALUES ('24', '6', '大衣');
INSERT INTO `subject` VALUES ('25', '6', 'T恤');
INSERT INTO `subject` VALUES ('26', '7', '护肤霜');
INSERT INTO `subject` VALUES ('27', '8', '编程书籍');
INSERT INTO `subject` VALUES ('28', '8', '少儿童画');
INSERT INTO `subject` VALUES ('29', '8', '科幻悬疑');
INSERT INTO `subject` VALUES ('30', '9', '自行车');
INSERT INTO `subject` VALUES ('31', '9', '电瓶车');
INSERT INTO `subject` VALUES ('32', '9', '汽车');
INSERT INTO `subject` VALUES ('33', '10', '书法作品');
INSERT INTO `subject` VALUES ('34', '10', '手工作品');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `avatar` varchar(255) NOT NULL COMMENT '头像地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `school` varchar(255) DEFAULT NULL COMMENT '学校名',
  `school_code` varchar(25) NOT NULL COMMENT '学号',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '账户状态 0-正常 1-冻结',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq号',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `grade` varchar(255) DEFAULT NULL COMMENT '年级',
  `publish_goods` int(5) DEFAULT '0' COMMENT '已发布商品数',
  `sold_goods` int(5) DEFAULT '0' COMMENT '已售出商品数',
  `sale_goods` int(5) DEFAULT '0' COMMENT '正在出售的商品数',
  `off_goods` int(5) DEFAULT '0' COMMENT '已下架商品数',
  `want_goods` int(5) DEFAULT '0' COMMENT '求购商品数',
  `report_goods` int(5) DEFAULT '0' COMMENT '已举报商品数',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1259105950065770498', '海绵宝宝大人', '$2a$10$2Z7dITqiXvEferyMZLJjruS34Hl95Y.bdlQapnlrpR3oU1hxINFU2', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/10/abf49e356a4e4d319917395440708114gudu.jpg', '16607925248', '武汉交通职业学院', '18033801153', '0', '2321885538', '计算机信息工程', '计算机应用一班', '2', '1', '2', '0', '1', '1', '2020-05-09 21:00:26');
INSERT INTO `user` VALUES ('1260156298884849666', '默认昵称2ffbc', '$2a$10$F/AP0VRwueBiFMdoU2wApeLTd25HzPrNu3nmnoRLph8/ySAjqhVOW', 'https://iknow-pic.cdn.bcebos.com/b3b7d0a20cf431adeef1310e4d36acaf2edd98e1?x-bce-process=image/resize,m_lfit,w_600,h_800,limit_1', null, null, '2321885538', '0', '2321885538', null, null, '0', '0', '0', '0', '0', '0', '2020-05-12 18:34:09');
INSERT INTO `user` VALUES ('1260159109634740225', '默认昵称8e0a5', '$2a$10$iRzdkbvHFTLW060p/sPw6e5aid0F0udu0xELAA/N/OrsbdA3dXHnO', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/12/4bcc2d537de5455bb0be4417ebc2bbab奔跑.jpg', null, null, '1870611039', '0', '1870611039', null, null, '0', '0', '0', '0', '0', '0', '2020-05-12 18:45:19');

-- ----------------------------
-- Table structure for want
-- ----------------------------
DROP TABLE IF EXISTS `want`;
CREATE TABLE `want` (
  `id` varchar(20) NOT NULL COMMENT '求购商品id',
  `title` varchar(255) DEFAULT NULL COMMENT '求购商品标题',
  `intro` varchar(255) DEFAULT NULL COMMENT '求购商品描述',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `username` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `qq` varchar(255) DEFAULT NULL COMMENT '用户qq',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `want_price` decimal(10,2) DEFAULT NULL COMMENT '期望价格',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除 0-正常 1-删除',
  `want_address` varchar(255) DEFAULT NULL COMMENT '期望交易地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of want
-- ----------------------------
INSERT INTO `want` VALUES ('1259809404480401410', '阿斯顿', '阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿', '1259105950065770498', 'https://edu-teachers.oss-cn-shenzhen.aliyuncs.com/2020/05/10/abf49e356a4e4d319917395440708114gudu.jpg', '海绵宝宝', '16607925248', '2321885538', '2020-05-11 19:35:43', '1.00', '0', '阿斯顿');
