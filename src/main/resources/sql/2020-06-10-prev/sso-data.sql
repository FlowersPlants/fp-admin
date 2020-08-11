
-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1073240195020052534', '1075245354642425654', '0,1074323454678456231,1075245354642425654', '栏目管理', 'col', '1', 0, 'col', 'cms/col/index', 'example', 30, '1', '2018-12-03 12:55:24', '1', '2019-01-03 22:25:57', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1074323454678456231', '0', '0', '功能菜单', '', '0', 0, NULL, 'Layout', NULL, 0, '1', '2018-12-03 12:44:58', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075123423456656412', '1074323454678456231', '0,1074323454678456231', '系统管理', 'sys', '0', 0, '/sys', 'Layout', 'setting', 30, '1', '2018-12-03 12:46:00', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075245354642425654', '1074323454678456231', '0,1074323454678456231', '内容管理', 'cms', '0', 0, '/cms', 'Layout', 'label', 60, '1', '2018-12-03 12:48:37', '1', '2019-01-03 22:25:47', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075245354642454543', '1075123423456656412', '0,1074323454678456231,1075123423456656412', '用户管理', 'user', '1', 0, 'user', 'sys/user/index', 'user', 30, '1', '2018-12-03 12:54:00', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075246434642425654', '1075123423456656412', '0,1074323454678456231,1075123423456656412', '菜单管理', 'menu', '1', 0, 'menu', 'sys/menu/index', 'component', 60, '1', '2018-12-03 12:54:30', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075254309429450342', '1075123423456656412', '0,1074323454678456231,1075123423456656412', '角色管理', 'role', '1', 0, 'role', 'sys/role/index', 'peoples', 90, '1', '2018-12-03 12:54:54', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075745536235057154', '1074323454678456231', '0,1074323454678456231', '测试案例', 'test', '0', 1, '/test', 'Layout', 'bug', 90, '1', '2018-12-20 21:31:37', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075747800362291202', '1075745536235057154', '0,1074323454678456231,1075745536235057154', 'svg测试', 'svg', '1', 0, 'svg', 'test/svg/index', 'icon', 30, '1', '2018-12-20 21:40:37', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075749565459656706', '1075745536235057154', '0,1074323454678456231,1075745536235057154', 'upload测试', 'upload', '1', 0, 'upload', 'test/upload/index', 'upload', 60, '1', '2018-12-20 21:47:38', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1075751623998910466', '1075745536235057154', '0,1074323454678456231,1075745536235057154', '富文本测试', 'fwb', '1', 0, 'fwb', 'test/fwb/index', NULL, 90, '1', '2018-12-20 21:55:48', NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1076740404667281409', '1075245354642425654', '0,1074323454678456231,1075245354642425654', '新闻管理', 'news', '1', 0, 'news', 'cms/news/index', 'message', 60, '1', '2018-12-23 15:24:52', '1', '2019-01-03 22:26:20', 0, NULL);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'ROLE_ADMIN', '1', 10, '1', '2018-11-13 05:20:53', NULL, NULL, 0, '开发时账号，用哟最高权限');
INSERT INTO `sys_role` VALUES ('2', '系统管理员', 'ROLE_SYSTEM', '2', 20, '1', '2018-11-13 05:21:40', NULL, NULL, 0, '客户使用的最高权限的管理员');
INSERT INTO `sys_role` VALUES ('3', '普通管理员', 'ROLE_GENERAL', '3', 30, '1', '2018-11-13 05:23:51', NULL, NULL, 0, '普通管理员');
INSERT INTO `sys_role` VALUES ('4', '普通用户', 'ROLE_USER', '0', 40, '1', '2018-11-13 05:24:29', NULL, NULL, 0, '普通用户');

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('4', '1073240195020052534');
INSERT INTO `sys_role_menu` VALUES ('3', '1075245354642425654');
INSERT INTO `sys_role_menu` VALUES ('3', '1073240195020052534');

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('4', '1062333229627310082');
INSERT INTO `sys_role_user` VALUES ('4', '1');

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$g6viskarc823RbgwoHc/ZOLsBmq5UHqYQu4tw0iVIIod4Xoc7sRCm', 'wzj', NULL, '0', NULL, 10, NULL, '1', '2018-11-06 13:14:12', NULL, NULL, 0, NULL);
INSERT INTO `sys_user` VALUES ('1062333229627310082', 'FlowersPlants', '$2a$10$EZSQH7QnfYQwiNAvDL33aefwc7nU1MrNpIlKUwXv2zDGK86H2g9Vy', 'flowersplants', NULL, '1', NULL, 20, NULL, '1', '2018-11-13 07:15:54', NULL, NULL, 0, NULL);
