-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'gender', 10, '用户性别字典，包括男、女和保密', 'admin', '2020-07-21 14:29:49', NULL, NULL, 1);

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '保密', '0', 10, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_dict_item` VALUES ('2', '1', '男', '1', 20, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_dict_item` VALUES ('3', '1', '女', '2', 30, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1073240195020052534', '1075245354642425654', '栏目管理', 'fun_cms_col', '1', 'item', 0, 0, 'col', 'cms/col/index', NULL, NULL, 30, NULL, '1', '2018-12-03 12:55:24', '1', '2019-01-03 22:25:57', 1);
INSERT INTO `sys_menu` VALUES ('1075123423456656412', '0', '系统管理', 'fun_sys', '0', 'system', 0, 0, '/sys', 'Layout', NULL, NULL, 30, NULL, '1', '2018-12-03 12:46:00', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075245354642425654', '0', '内容管理', 'fun_cms', '0', 'bug', 0, 0, '/cms', 'Layout', NULL, NULL, 60, NULL, '1', '2018-12-03 12:48:37', '1', '2019-01-03 22:25:47', 1);
INSERT INTO `sys_menu` VALUES ('1075245354642454543', '1075123423456656412', '用户管理', 'fun_sys_user', '1', 'user', 0, 0, 'user', 'sys/user/index', NULL, 'sys:user:list', 30, NULL, '1', '2018-12-03 12:54:00', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075246434642425654', '1075123423456656412', '菜单管理', 'fun_sys_menu', '1', 'menu', 0, 0, 'menu', 'sys/menu/index', NULL, 'sys:menu:list', 60, NULL, '1', '2018-12-03 12:54:30', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075254309429450342', '1075123423456656412', '角色管理', 'fun_sys_role', '1', 'role', 0, 0, 'role', 'sys/role/index', NULL, 'sys:role:list', 10, NULL, '1', '2018-12-03 12:54:54', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075745536235057154', '0', '测试案例', 'fun_test', '0', 'item', 1, 0, '/test', 'Layout', NULL, NULL, 90, NULL, '1', '2018-12-20 21:31:37', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075747800362291202', '1075745536235057154', 'svg测试', 'fun_test_svg', '1', 'bug', 0, 0, 'svg', 'test/svg/index', NULL, NULL, 30, NULL, '1', '2018-12-20 21:40:37', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075749565459656706', '1075745536235057154', 'upload测试', 'fun_test_upload', '1', 'bug', 0, 0, 'upload', 'test/upload/index', NULL, NULL, 60, NULL, '1', '2018-12-20 21:47:38', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1075751623998910466', '1075745536235057154', '富文本测试', 'fun_test_fwb', '1', NULL, 0, 0, 'fwb', 'test/fwb/index', NULL, NULL, 90, NULL, '1', '2018-12-20 21:55:48', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1076740404667281409', '1075245354642425654', '新闻管理', 'fun_cms_news', '1', 'bug', 0, 0, 'news', 'cms/news/index', NULL, NULL, 60, NULL, '1', '2018-12-23 15:24:52', '1', '2019-01-03 22:26:20', 1);
INSERT INTO `sys_menu` VALUES ('111', '1075123423456656412', '字典管理', 'fun_sys_dict', '1', 'item', 0, 0, 'dict', 'sys/dict/index', NULL, 'sys:dict:list', 90, NULL, '1', '2020-08-06 19:58:18', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1278608541538488322', '1075245354642454543', '新增', 'fun_sys_user:insert', '2', NULL, 1, 0, NULL, NULL, NULL, 'sys:user:add', 10, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1278608541865644034', '1075245354642454543', '编辑', 'fun_sys_user:update', '2', NULL, 1, 0, NULL, NULL, NULL, 'sys:user:edit', 20, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('1278608541874032642', '1075245354642454543', '删除', 'fun_sys_user:del', '2', NULL, 1, 0, NULL, NULL, NULL, 'sys:user:del', 30, NULL, NULL, '2020-07-02 16:36:46', NULL, NULL, 1);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'ADMIN', 0, 10, '开发时账号，拥有最高权限', '1', '2018-11-13 05:20:53', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('1290162067792101377', '测试', 'TEST', 666, 100, NULL, 'admin', '2020-08-03 13:46:22', 'admin', '2020-08-03 13:46:37', 0);
INSERT INTO `sys_role` VALUES ('2', '系统管理员', 'SYSTEM', 10, 20, '客户使用的最高权限的管理员', '1', '2018-11-13 05:21:40', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('3', '普通管理员', 'GENERAL', 20, 30, '普通管理员', '1', '2018-11-13 05:23:51', NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('4', '普通用户', 'USER', 999, 40, '普通用户', '1', '2018-11-13 05:24:29', NULL, NULL, 1);

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1073240195020052534');
INSERT INTO `sys_role_menu` VALUES ('1', '1075123423456656412');
INSERT INTO `sys_role_menu` VALUES ('1', '1075245354642425654');
INSERT INTO `sys_role_menu` VALUES ('1', '1075245354642454543');
INSERT INTO `sys_role_menu` VALUES ('1', '1075246434642425654');
INSERT INTO `sys_role_menu` VALUES ('1', '1075254309429450342');
INSERT INTO `sys_role_menu` VALUES ('1', '1075745536235057154');
INSERT INTO `sys_role_menu` VALUES ('1', '1075747800362291202');
INSERT INTO `sys_role_menu` VALUES ('1', '1075749565459656706');
INSERT INTO `sys_role_menu` VALUES ('1', '1075751623998910466');
INSERT INTO `sys_role_menu` VALUES ('1', '1076740404667281409');
INSERT INTO `sys_role_menu` VALUES ('2', '1075245354642425654');
INSERT INTO `sys_role_menu` VALUES ('2', '1076740404667281409');
INSERT INTO `sys_role_menu` VALUES ('2', '1073240195020052534');
INSERT INTO `sys_role_menu` VALUES ('1', '1278608541538488322');
INSERT INTO `sys_role_menu` VALUES ('1', '1278608541865644034');
INSERT INTO `sys_role_menu` VALUES ('1', '1278608541874032642');
INSERT INTO `sys_role_menu` VALUES ('1', '111');

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', '{bcrypt}$2a$10$f6.gcn3ooOYZ4ruqkseCp.Kv7piF0M.T276yVwb5/dpZQxUclIduG', 'fpwag', NULL, NULL, '0', NULL, 10, 1, NULL, NULL, '2020-06-09 15:20:38', 'admin', '2020-08-02 15:52:45', 1);
INSERT INTO `sys_user` VALUES ('1289821798223810561', '4', 'wang', '{bcrypt}$2a$10$4PPXjm3ckDAILhqyJp89teydW7TgLrCqPQyuttlPD1rwQkb5YAJRC', '小王', NULL, NULL, '0', NULL, 30, 0, '测试用户，小王', 'admin', '2020-08-02 15:14:15', 'admin', '2020-08-03 11:30:26', 1);
INSERT INTO `sys_user` VALUES ('1290979490397093890', '1290162067792101377', 'test', '{bcrypt}$2a$10$eVae/H9gLUKnY8aEEgdDOO0fx0hfYq6yFCYxQaJ78/T991jnJQbKC', 'test', NULL, NULL, '0', NULL, 100, 0, 'test', 'admin', '2020-08-05 19:54:30', 'admin', '2020-08-05 19:55:46', 1);
