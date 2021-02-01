-- ----------------------------
-- Table structure for animal(id,名字,品种,位置,性别,出生日期)
-- ----------------------------
DROP TABLE IF EXISTS `animal`;
CREATE TABLE `animal` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) DEFAULT NULL,
  `breed` varchar(255) DEFAULT NULL,
  `p_addr` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of animal
-- ----------------------------
INSERT INTO `animal`(aname, breed, p_addr, gender, birth) VALUES ('加菲猫', '异国短毛猫', '四海楼', '1', '2020-11-19');
INSERT INTO `animal`(aname, breed, p_addr, gender, birth) VALUES ('多啦A梦', '机器猫', '英华五', '1', '2020-10-10');
INSERT INTO `animal`(aname, breed, p_addr, gender, birth) VALUES ('凯蒂猫', '招财猫', '湖畔书屋', '2', '2020-2-10');

-- ----------------------------
-- Table structure for manager(id,姓名,密码,性别,出生日期,手机,邮箱)
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
                           `mid` int(11) NOT NULL AUTO_INCREMENT,
                           `password` varchar(255) default NULL,
                           `mname` varchar(255) DEFAULT NULL,
                           `mphone` varchar(255) DEFAULT NULL,
                           `memail` varchar(255) DEFAULT NULL,
                           `gender` int(11) DEFAULT NULL,
                           `enabled` int(11) DEFAULT NULL,
                           `birth` date DEFAULT NULL,
                           PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager  密码：123456
-- ----------------------------
INSERT INTO `manager`(mid,mname, password, mphone, memail, gender, enabled, birth) VALUES (1,'Meteor', '$2a$10$BfA2mtDp7fHvyjNq8rX65epX1VwfAV3C5nyfz1G4gtt7JFc5ta4y6', '15965076221', '2290502632@qq.com', '1', '1','1999-10-29');
INSERT INTO `manager`(mid,mname, password, mphone, memail, gender, enabled, birth) VALUES (2,'smartdog', '$2a$10$BfA2mtDp7fHvyjNq8rX65epX1VwfAV3C5nyfz1G4gtt7JFc5ta4y6', '13204069875', '15965076221@163.com', '2', '1','1999-9-21');
INSERT INTO `manager`(mid,mname, password, mphone, memail, gender, enabled, birth) VALUES (3,'天才', '$2a$10$BfA2mtDp7fHvyjNq8rX65epX1VwfAV3C5nyfz1G4gtt7JFc5ta4y6', '12345678910', '123456789@qq.com', '1', '1','1999-10-29');

-- ----------------------------
-- Table structure for role(id,角色code，角色名称)
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
insert  into `role`(`id`,`name`,`nameZh`) values (1,'ROLE_manager','部门经理'),
(2,'ROLE_personnel','人事专员'),
(3,'ROLE_recruiter','招聘主管'),
(4,'ROLE_train','培训主管'),
(5,'ROLE_performance','薪酬绩效主管'),
(6,'ROLE_admin','系统管理员');

-- ----------------------------
-- Table structure for manage_role(id,manageid，rid)
-- ----------------------------
DROP TABLE IF EXISTS `manage_role`;
CREATE TABLE `manage_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manageid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manage_role
-- ----------------------------
insert  into `hr_role`(`id`,`manageid`,`rid`) values
(1,4),(1,3),(1,2),
(2,3),(2,2),(2,4),(2,5),
(3,3),(3,4),
(4,1),(4,2),(4,3);


/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`url`,`path`,`component`,`name`,`iconCls`,`keepAlive`,`requireAuth`,`parentId`,`enabled`) values
(1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),
(2,'/','/home','Home','员工资料','fa fa-user-circle-o',NULL,1,1,1),
(3,'/','/home','Home','人事管理','fa fa-address-card-o',NULL,1,1,1),
(4,'/','/home','Home','薪资管理','fa fa-money',NULL,1,1,1),
(5,'/','/home','Home','统计管理','fa fa-bar-chart',NULL,1,1,1),
(6,'/','/home','Home','系统管理','fa fa-windows',NULL,1,1,1),
(7,'/employee/basic/**','/emp/basic','EmpBasic','基本资料',NULL,NULL,1,2,1),
(8,'/employee/advanced/**','/emp/adv','EmpAdv','高级资料',NULL,NULL,1,2,1),
(9,'/personnel/emp/**','/per/emp','PerEmp','员工资料',NULL,NULL,1,3,1),
(10,'/personnel/ec/**','/per/ec','PerEc','员工奖惩',NULL,NULL,1,3,1),
(11,'/personnel/train/**','/per/train','PerTrain','员工培训',NULL,NULL,1,3,1),
(12,'/personnel/salary/**','/per/salary','PerSalary','员工调薪',NULL,NULL,1,3,1),
(13,'/personnel/remove/**','/per/mv','PerMv','员工调动',NULL,NULL,1,3,1),
(14,'/salary/sob/**','/sal/sob','SalSob','工资账套管理',NULL,NULL,1,4,1),
(15,'/salary/sobcfg/**','/sal/sobcfg','SalSobCfg','员工账套设置',NULL,NULL,1,4,1),
(16,'/salary/table/**','/sal/table','SalTable','工资表管理',NULL,NULL,1,4,1),
(17,'/salary/month/**','/sal/month','SalMonth','月末处理',NULL,NULL,1,4,1),
(18,'/salary/search/**','/sal/search','SalSearch','工资表查询',NULL,NULL,1,4,1),
(19,'/statistics/all/**','/sta/all','StaAll','综合信息统计',NULL,NULL,1,5,1),
(20,'/statistics/score/**','/sta/score','StaScore','员工积分统计',NULL,NULL,1,5,1),
(21,'/statistics/personnel/**','/sta/pers','StaPers','人事信息统计',NULL,NULL,1,5,1),
(22,'/statistics/recored/**','/sta/record','StaRecord','人事记录统计',NULL,NULL,1,5,1),
(23,'/system/basic/**','/sys/basic','SysBasic','基础信息设置',NULL,NULL,1,6,1),
(24,'/system/cfg/**','/sys/cfg','SysCfg','系统管理',NULL,NULL,1,6,1),
(25,'/system/log/**','/sys/log','SysLog','操作日志管理',NULL,NULL,1,6,1),
(26,'/system/hr/**','/sys/hr','SysHr','操作员管理',NULL,NULL,1,6,1),
(27,'/system/data/**','/sys/data','SysData','备份恢复数据库',NULL,NULL,1,6,1),
(28,'/system/init/**','/sys/init','SysInit','初始化数据库',NULL,NULL,1,6,1);

/*Table structure for table `menu_role` */

DROP TABLE IF EXISTS `menu_role`;

CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8;

/*Data for the table `menu_role` */

insert  into `menu_role`(`id`,`mid`,`rid`) values
(161,7,3),
(162,7,6),(163,9,6),(164,10,6),(165,11,6),(166,12,6),(167,13,6),(168,14,6),(169,15,6),(170,16,6),(171,17,6),(172,18,6),(173,19,6),(174,20,6),(175,21,6),(176,22,6),(177,23,6),(178,25,6),(179,26,6),(180,27,6),(181,28,6),(182,24,6),
(247,7,4),(248,8,4),(249,11,4),
(250,7,2),(251,8,2),(252,9,2),(253,10,2),(254,12,2),(255,13,2),
(256,7,1),(257,8,1),(258,9,1),(259,10,1),(260,11,1),(261,12,1),(262,13,1),(263,14,1),(264,15,1),(265,16,1),(266,17,1),(267,18,1),(268,19,1),(269,20,1),(270,21,1),(271,22,1),(272,23,1),(273,24,1),(274,25,1),(275,26,1),(276,27,1),(277,28,1),
(280,7,14),(281,8,14),(282,9,14);

-- ----------------------------
-- Table structure for 邮件发送日志
-- ----------------------------
CREATE TABLE `mail_send_log` (
  `msgId` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `empId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0发送中，1发送成功，2发送失败',
  `routeKey` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `exchange` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '重试次数',
  `tryTime` date DEFAULT NULL COMMENT '第一次重试时间',
  `createTime` date DEFAULT NULL,
  `updateTime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;