-- ----------------------------
-- Table structure for animal
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
