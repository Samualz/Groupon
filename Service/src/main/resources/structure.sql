CREATE DATABASE IF NOT EXISTS `groupon`  DEFAULT CHARACTER SET utf8;
USE `groupon`;


--
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_id` bigint(20) NOT NULL,
  `area_name` varchar(64) NOT NULL,
  `sku_id` bigint(20) NOT NULL COMMENT '商品ID',
  `deal_class` int(2) NOT NULL,
  `merchant_id` bigint(20) NOT NULL COMMENT '厂商ID',
  `merchant_sku` bigint(20) NOT NULL,
  `deal_title` varchar(200) NOT NULL COMMENT '商品标题',
  `deal_price` decimal(10,0) NOT NULL COMMENT '商品价格',
  `merchant_price` decimal(10,0) NOT NULL COMMENT '进货价',
  `market_price` decimal(10,0) NOT NULL COMMENT '市场价',
  `settlement_price` decimal(10,0) NOT NULL,
  `settlement_price_max` decimal(10,0) DEFAULT NULL COMMENT '最大可接受结算价格',
  `discount` int(3) DEFAULT NULL COMMENT '折扣',
  `bonus_points` int(5) DEFAULT NULL COMMENT '积分',
  `deal_type` int(3) NOT NULL COMMENT '商品类型',
  `image_id` bigint(20) DEFAULT '0' COMMENT '对应商品图片',
  `deal_level` int(4) NOT NULL COMMENT '商品优先级',
  `max_purchase_count` int(4) DEFAULT NULL,
  `publish_status` int(2) NOT NULL COMMENT '发布状态',
  `inventory_amount` int(4) NOT NULL COMMENT '商品库存数量',
  `vendibility_amount` int(4) NOT NULL COMMENT '商品可售数量',
  `oos_status` int(2) NOT NULL,
  `start_time` datetime NOT NULL COMMENT '销售开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '销售结束时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `merchant_code` varchar(15) DEFAULT NULL COMMENT '商家编码',
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  `category_id` bigint(20) unsigned NOT NULL COMMENT '商品类别ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `deal_sku_UNIQUE` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `deal_detail`
--

DROP TABLE IF EXISTS `deal_detail`;
CREATE TABLE `deal_detail` (
  `id` bigint(20) NOT NULL,
  `deal_id` bigint(20) NOT NULL,
  `deal_detail` varchar(8000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `detail_deal_id_UNIQUE` (`deal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `deal_category`
--

DROP TABLE IF EXISTS `deal_category`;
CREATE TABLE `deal_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `url_name` varchar(32) NOT NULL COMMENT '分类URL名称',
  `publish_status` int(2) NOT NULL COMMENT '发布状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `order_num` int(10) unsigned NOT NULL COMMENT '排序号码',
  `deep` int(10) unsigned NOT NULL COMMENT '层次深度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `deal_category_url_name_UNIQUE` (`url_name`),
  UNIQUE KEY `deal_category_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) NOT NULL,
  `common` int(4) NOT NULL,
  `type` varchar(16) NOT NULL COMMENT '类型:省,市',
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `area_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `image_info`
--

DROP TABLE IF EXISTS `image_info`;
CREATE TABLE `image_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `width` int(4) DEFAULT NULL COMMENT '图片的宽',
  `height` int(4) DEFAULT NULL COMMENT '图片的高',
  `source_path` varchar(100) DEFAULT NULL COMMENT '图片的源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `deal_id` bigint(20) NOT NULL,
  `deal_sku_id` bigint(20) NOT NULL,
  `count` int(4) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  KEY `cart_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `order_basic`
--

DROP TABLE IF EXISTS `order_basic`;
CREATE TABLE `order_basic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `order_status` int(11) NOT NULL COMMENT '订单状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `total_price` int(11) NOT NULL COMMENT '订单总价',
  `total_settlement_price` int(11) NOT NULL,
  `address` mediumtext COMMENT '收货地址',
  `receiver` varchar(128) DEFAULT NULL COMMENT '收件人 ',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `pay_type` int(2) DEFAULT '0' COMMENT '支付方式，1：微信，2：支付宝，3：货到付款',
  PRIMARY KEY (`id`),
  KEY `order_user_INDEX` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_sku` int(20) DEFAULT NULL COMMENT '商家商品SKU',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商家编码',
  `merchant_code` varchar(32) DEFAULT NULL COMMENT '商家编码',
  `deal_id` bigint(20) NOT NULL COMMENT 'deal ID',
  `deal_sku_id` bigint(20) NOT NULL,
  `deal_img_id` bigint(20) NOT NULL,
  `deal_title` varchar(200) NOT NULL COMMENT 'deal名称',
  `deal_count` int(11) NOT NULL COMMENT 'Deal数量',
  `deal_price` int(11) NOT NULL COMMENT 'Deal单价',
  `total_price` int(11) NOT NULL COMMENT 'Deal总价',
  `settlement_price` int(11) NOT NULL,
  `total_settlement_price` int(11) NOT NULL,
  `detail_status` int(11) NOT NULL COMMENT '详情状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `detail_user_id_INDEX` (`user_id`),
  KEY `detail_order_id_UNIQUE` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `login_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user_basic_info`
--

DROP TABLE IF EXISTS `user_basic_info`;
CREATE TABLE `user_basic_info` (
  `id` int(10) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `real_name` varchar(32) NOT NULL,
  `mail` varchar(32) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `receiver` varchar(64) NOT NULL,
  `area` varchar(256) NOT NULL,
  `detail` varchar(256) NOT NULL,
  `type` varchar(8) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  KEY `INDEX_USER_ID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `deal_id` bigint(20) NOT NULL,
  `deal_sku_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  KEY `favorite_user_deal_id` (`user_id`,`deal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(64) NOT NULL,
  `content` varchar(256) NOT NULL,
  `readed` varchar(4) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  KEY `message_user_INDEX` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;














--
-- Table structure for table `admin_user`
--

DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_user_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_ROLE_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `admin_function`
--

DROP TABLE IF EXISTS `admin_function`;
CREATE TABLE `admin_function` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '名称',
  `state` varchar(10) NOT NULL DEFAULT 'open' COMMENT '状态，open/closed',
  `parent_id` int(10) NOT NULL COMMENT '父节点ID',
  `url` varchar(64) NOT NULL COMMENT '链接',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_func_url_UNIQUE` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ERP菜单表';

--
-- Table structure for table `admin_role_function`
--

DROP TABLE IF EXISTS `admin_role_function`;
CREATE TABLE `admin_role_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_role_id` int(10) NOT NULL COMMENT '角色ID',
  `admin_func_id` int(10) NOT NULL COMMENT '功能ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_role_func_UNIQUE` (`admin_role_id`,`admin_func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='admin角色功能树对应关系表';

--
-- Table structure for table `admin_user_role`
--

DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(10) NOT NULL COMMENT '用户ID',
  `admin_role_id` int(10) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `admin_user_role_id` (`admin_user_id`,`admin_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='admin用户角色对应关系表';

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '商家名称',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '商家描述',
  `image_id` bigint(20) NOT NULL COMMENT '关联图片',
  `level` int(4) NOT NULL COMMENT '商家级别',
  `hot_level` int(4) NOT NULL COMMENT '热度等级',
  `status` int(2) NOT NULL COMMENT '发布状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `url` varchar(100) NOT NULL COMMENT '商家URL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `merchant_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `start_remind`
--

DROP TABLE IF EXISTS `start_remind`;
CREATE TABLE `start_remind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `deal_id` varchar(64) NOT NULL,
  `deal_sku_id` varchar(64) NOT NULL,
  `deal_title` varchar(200) NOT NULL,
  `start_time` datetime NOT NULL COMMENT '开团时间 ',
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime NOT NULL COMMENT '更新时间 ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `remind_user_deal_UNIQUE` (`user_id`,`deal_id`),
  KEY `remind_user_INDEX` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



























