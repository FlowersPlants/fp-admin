/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : fp-admin

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/08/2020 21:10:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
set global log_bin_trust_function_creators=true; # 开启自定义函数

-- ----------------------------
-- Table structure for boot_file
-- ----------------------------
DROP TABLE IF EXISTS `boot_file`;
CREATE TABLE `boot_file`  (
                              `unique_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识',
                              `storage_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储策略（aliyun, tencent, qiniu, local）',
                              `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称，包含扩展名',
                              `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储桶名字',
                              `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'endpoint',
                              `access_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问地址',
                              `content_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容类型',
                              `content_length` bigint(20) NULL DEFAULT NULL COMMENT '内容大小',
                              `content_disposition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Object被下载时的名称',
                              PRIMARY KEY (`unique_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
                            `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一请求号',
                            `type` int(11) NULL DEFAULT 0 COMMENT '日志类型',
                            `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '日志标题',
                            `remote_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
                            `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
                            `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
                            `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方式',
                            `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作提交的数据',
                            `execute_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行的结果',
                            `execute_time` bigint(20) NULL DEFAULT NULL COMMENT '执行时间',
                            `has_exception` tinyint(4) NULL DEFAULT NULL COMMENT '是否异常',
                            `exception` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
                            `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                            `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '删除标记：1-正常，0-删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级部门',
                             `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
                             `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
                             `sort` int(11) NULL DEFAULT NULL COMMENT '排序号',
                             `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                             `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
                             `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
                             `sort` int(11) NULL DEFAULT 10 COMMENT '排序号',
                             `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'gender', 10, NULL, '用户性别字典，包括男、女和保密', 'admin', '2020-07-21 14:29:49', NULL, NULL, 1);
INSERT INTO `sys_dict` VALUES ('1294480534875832322', '系统状态', 'status', 10, NULL, NULL, 'admin', '2020-08-15 11:46:24', 'admin', '2020-08-15 11:59:44', 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
                                  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                                  `dict_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典id',
                                  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标签名称',
                                  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
                                  `sort` int(11) NULL DEFAULT 10 COMMENT '排序号',
                                  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                                  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                                  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                                  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                                  `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '保密', '0', 10, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_dict_item` VALUES ('1294483835168129025', '1294480534875832322', '启用', 'true', 10, NULL, NULL, 'admin', '2020-08-15 11:59:31', 'admin', '2020-08-16 11:46:03', 1);
INSERT INTO `sys_dict_item` VALUES ('1294483949580353537', '1294480534875832322', '禁用', 'false', 10, NULL, NULL, 'admin', '2020-08-15 11:59:59', 'admin', '2020-08-16 11:46:08', 1);
INSERT INTO `sys_dict_item` VALUES ('2', '1', '男', '1', 20, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_dict_item` VALUES ('3', '1', '女', '2', 30, 1, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                             `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父菜单ID',
                             `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
                             `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '菜单类型(0目录 1菜单 2按钮)',
                             `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
                             `hidden` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否隐藏，0-显示，1-隐藏',
                             `cache` tinyint(4) NOT NULL COMMENT '是否缓存，1-缓存，0-不缓存',
                             `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后端请求URL',
                             `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端页面组件地址',
                             `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
                             `sort` int(11) NULL DEFAULT 10 COMMENT '排序号',
                             `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1073240195020052534', '1075245354642425654', '栏目管理', '1', 'item', 0, 0, 'col', 'cms/col/index', NULL, 30, NULL, NULL, '1', '2018-12-03 12:55:24', '1', '2019-01-03 22:25:57', 1);
INSERT INTO `sys_menu` VALUES ('1075123423456656412', '0', '系统管理', '0', 'system', 0, 0, '/sys', 'Layout', NULL, 30, NULL, NULL, '1', '2018-12-03 12:46:00', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075245354642425654', '0', '内容管理', '0', 'bug', 0, 0, '/cms', 'Layout', NULL, 60, NULL, NULL, '1', '2018-12-03 12:48:37', '1', '2019-01-03 22:25:47', 1);
INSERT INTO `sys_menu` VALUES ('1075245354642454543', '1075123423456656412', '用户管理', '1', 'user', 0, 0, 'user', 'sys/user/index', 'sys:user:list', 30, NULL, NULL, '1', '2018-12-03 12:54:00', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075246434642425654', '1075123423456656412', '菜单管理', '1', 'menu', 0, 0, 'menu', 'sys/menu/index', 'sys:menu:list', 60, NULL, NULL, '1', '2018-12-03 12:54:30', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075254309429450342', '1075123423456656412', '角色管理', '1', 'role', 0, 0, 'role', 'sys/role/index', 'sys:role:list', 10, NULL, NULL, '1', '2018-12-03 12:54:54', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075745536235057154', '0', '测试案例', '0', 'item', 1, 0, '/test', 'Layout', NULL, 90, NULL, NULL, '1', '2018-12-20 21:31:37', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075747800362291202', '1075745536235057154', 'svg测试', '1', 'bug', 0, 0, 'svg', 'test/svg/index', NULL, 30, NULL, NULL, '1', '2018-12-20 21:40:37', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075749565459656706', '1075745536235057154', 'upload测试', '1', 'bug', 0, 0, 'upload', 'test/upload/index', NULL, 60, NULL, NULL, '1', '2018-12-20 21:47:38', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075751623998910466', '1075745536235057154', '富文本测试', '1', NULL, 0, 0, 'fwb', 'test/fwb/index', NULL, 90, NULL, NULL, '1', '2018-12-20 21:55:48', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1076740404667281409', '1075245354642425654', '新闻管理', '1', 'bug', 0, 0, 'news', 'cms/news/index', NULL, 60, NULL, NULL, '1', '2018-12-23 15:24:52', '1', '2019-01-03 22:26:20', 1);
INSERT INTO `sys_menu` VALUES ('111', '1075123423456656412', '字典管理', '1', 'item', 0, 0, 'dict', 'sys/dict/index', 'sys:dict:list', 5, NULL, NULL, '1', '2020-08-06 19:58:18', 'admin', '2020-08-17 19:51:08', 1);
INSERT INTO `sys_menu` VALUES ('1278608541538488322', '1075245354642454543', '新增', '2', NULL, 1, 0, NULL, NULL, 'sys:user:add', 10, NULL, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1278608541865644034', '1075245354642454543', '编辑', '2', NULL, 1, 0, NULL, NULL, 'sys:user:edit', 20, NULL, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1278608541874032642', '1075245354642454543', '删除', '2', NULL, 1, 0, NULL, NULL, 'sys:user:del', 30, NULL, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295325611089096705', '1075254309429450342', '新增', '2', NULL, 1, 0, NULL, NULL, 'sys:role:add', 10, NULL, NULL, 'admin', '2020-08-17 19:44:26', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295325971618885633', '1075254309429450342', '编辑', '2', NULL, 1, 0, NULL, NULL, 'sys:role:edit', 20, NULL, NULL, 'admin', '2020-08-17 19:45:52', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326083619385345', '1075254309429450342', '删除', '2', NULL, 1, 0, NULL, NULL, 'sys:role:del', 30, NULL, NULL, 'admin', '2020-08-17 19:46:19', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326179350179841', '1075246434642425654', '新增', '2', NULL, 1, 0, NULL, NULL, 'sys:menu:add', 10, NULL, NULL, 'admin', '2020-08-17 19:46:42', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326255707484162', '1075246434642425654', '编辑', '2', NULL, 1, 0, NULL, NULL, 'sys:menu:edit', 20, NULL, NULL, 'admin', '2020-08-17 19:47:00', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326322464026626', '1075246434642425654', '删除', '2', NULL, 1, 0, NULL, NULL, 'sys:menu:del', 30, NULL, NULL, 'admin', '2020-08-17 19:47:16', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326490596896769', '1075254309429450342', '角色授权', '2', NULL, 1, 0, NULL, NULL, 'sys:role:auth', 40, NULL, NULL, 'admin', '2020-08-17 19:47:56', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326623778631681', '1075254309429450342', '分配用户', '2', NULL, 1, 0, NULL, NULL, 'sys:role:assign', 50, NULL, NULL, 'admin', '2020-08-17 19:48:28', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295326994429276161', '111', '新增', '2', NULL, 1, 0, NULL, NULL, 'sys:dict:add', 10, NULL, NULL, 'admin', '2020-08-17 19:49:56', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295327070614614017', '111', '编辑', '2', NULL, 1, 0, NULL, NULL, 'sys:dict:edit', 20, NULL, NULL, 'admin', '2020-08-17 19:50:14', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295327151036198914', '111', '删除', '2', NULL, 1, 0, NULL, NULL, 'sys:dict:del', 30, NULL, NULL, 'admin', '2020-08-17 19:50:33', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1295329560747732993', '1075123423456656412', '日志管理', '1', 'bug', 0, 0, 'log', 'sys/log/index', 'sys:log:list', 90, NULL, NULL, 'admin', '2020-08-17 20:00:08', 'admin', '2020-08-17 20:01:21', 1);
INSERT INTO `sys_menu` VALUES ('1295329702720729090', '1295329560747732993', '删除', '2', NULL, 1, 0, NULL, NULL, 'sys:log:del', 10, NULL, NULL, 'admin', '2020-08-17 20:00:42', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                             `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
                             `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
                             `level` int(11) NULL DEFAULT 999 COMMENT '角色级别，数字越小级别越高，0最高，默认999',
                             `sort` int(11) NULL DEFAULT 10 COMMENT '排序号',
                             `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表	' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'ADMIN', 0, 10, NULL, '开发时账号，拥有最高权限', '1', '2018-11-13 05:20:53', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('1290162067792101377', '测试', 'TEST', 666, 100, NULL, NULL, 'admin', '2020-08-03 13:46:22', 'admin', '2020-08-03 13:46:37', 0);
INSERT INTO `sys_role` VALUES ('2', '系统管理员', 'SYSTEM', 10, 20, NULL, '客户使用的最高权限的管理员', '1', '2018-11-13 05:21:40', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('3', '普通管理员', 'GENERAL', 20, 30, NULL, '普通管理员', '1', '2018-11-13 05:23:51', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('4', '普通用户', 'USER', 999, 40, NULL, '普通用户', '1', '2018-11-13 05:24:29', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
                                  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, '1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                             `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
                             `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
                             `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                             `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
                             `gender` int(11) NULL DEFAULT 0 COMMENT '性别（0-保密，1-男，2-女）',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
                             `sort` int(10) NULL DEFAULT 10 COMMENT '排序号',
                             `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-启用，0-禁用',
                             `admin` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否超级管理员，1-是，默认否',
                             `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                             `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                             `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，推荐状态（1-正常；0-删除）',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', NULL, 'admin', '{bcrypt}$2a$10$f6.gcn3ooOYZ4ruqkseCp.Kv7piF0M.T276yVwb5/dpZQxUclIduG', 'fpwag', 'jj@qqc.com', '18861822740', 0, NULL, 10, 1, 1, '超级管理员，开发时使用，拥有所有权限，无需分配', NULL, '2020-06-09 15:20:38', 'admin', '2020-08-16 13:28:59', 1);
INSERT INTO `sys_user` VALUES ('1289821798223810561', NULL, 'wang', '{bcrypt}$2a$10$4PPXjm3ckDAILhqyJp89teydW7TgLrCqPQyuttlPD1rwQkb5YAJRC', '小王', NULL, NULL, 1, NULL, 30, 0, 0, '测试用户，小王', 'admin', '2020-08-02 15:14:15', 'admin', '2020-08-03 11:30:26', 1);
INSERT INTO `sys_user` VALUES ('1290979490397093890', NULL, 'test', '{bcrypt}$2a$10$eVae/H9gLUKnY8aEEgdDOO0fx0hfYq6yFCYxQaJ78/T991jnJQbKC', 'test', NULL, NULL, 0, NULL, 100, 0, 0, 'test', 'admin', '2020-08-05 19:54:30', 'admin', '2020-08-05 19:55:46', 0);

-- ----------------------------
-- Function structure for FIND_DEPT_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `FIND_DEPT_CHILDREN`;
delimiter ;;
CREATE FUNCTION `FIND_DEPT_CHILDREN`(rootId VARCHAR ( 32 ))
    RETURNS varchar(2000) CHARSET utf8mb4
BEGIN
    DECLARE
        nodes VARCHAR ( 2000 );
    DECLARE
        cid VARCHAR ( 2000 );
    SET nodes = '$';
    SET cid = rootId;
    WHILE
    cid IS NOT NULL DO
    SET nodes = CONCAT( nodes, ',', cid );
    SELECT
        GROUP_CONCAT( id ) INTO cid
    FROM
        sys_dept
    WHERE
        FIND_IN_SET( parent_id, cid );
    END WHILE;
    RETURN nodes;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for FIND_MENU_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `FIND_MENU_CHILDREN`;
delimiter ;;
CREATE FUNCTION `FIND_MENU_CHILDREN`(rootId VARCHAR ( 32 ))
    RETURNS varchar(2000) CHARSET utf8mb4
BEGIN
    DECLARE
        nodes VARCHAR ( 2000 );
    DECLARE
        cid VARCHAR ( 2000 );
    SET nodes = '$';
    SET cid = rootId;
    WHILE
    cid IS NOT NULL DO
    SET nodes = CONCAT( nodes, ',', cid );
    SELECT
        GROUP_CONCAT( id ) INTO cid
    FROM
        sys_menu
    WHERE
        FIND_IN_SET( parent_id, cid );
    END WHILE;
    RETURN nodes;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
