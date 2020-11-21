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
-- Table structure for manager(id,姓名,密码,性别,出生日期,手机,邮箱,职责)
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
                           `mid` int(11) NOT NULL AUTO_INCREMENT,
                           `password` varchar(255) default NULL,
                           `mname` varchar(255) DEFAULT NULL,
                           `mphone` varchar(255) DEFAULT NULL,
                           `memail` varchar(255) DEFAULT NULL,
                           `duty` varchar(255) DEFAULT NULL,
                           `gender` int(11) DEFAULT NULL,
                           `birth` date DEFAULT NULL,
                           PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager`(mname, password, mphone, memail, duty, gender, birth) VALUES ('Meteor', 'root', '15965076221', '2290502632@qq.com', 'CEO', '1', '1999-10-29');
INSERT INTO `manager`(mname, password, mphone, memail, duty, gender, birth) VALUES ('smartdog', '123456', '13204069875', '15965076221@163.com', 'CTO', '2', '1999-9-21');
INSERT INTO `manager`(mname, password, mphone, memail, duty, gender, birth) VALUES ('天才', '666666', '12345678910', '123456789@qq.com', 'COO', '1', '1999-10-29');
