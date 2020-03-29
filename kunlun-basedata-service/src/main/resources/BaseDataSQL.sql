-- 创建平台数据库：scmp_home
DROP DATABASE IF EXISTS scmp_home;
CREATE DATABASE scmp_home ENCODING 'UTF8';

-- 用户表：t_user
DROP TABLE IF EXISTS "t_user";
CREATE TABLE IF NOT EXISTS "t_user" (
	id varchar(50) primary key,
  user_name varchar(50) default null,
  password varchar(50) default null,
  phone_number varchar(15) default null,
  email varchar(50) default null,
  create_time timestamp default null,
  modified_time timestamp default null
);
COMMENT ON TABLE t_user IS '用户表t_user';
COMMENT ON COLUMN t_user.id IS '用户id';
COMMENT ON COLUMN t_user.user_name IS '账号';
COMMENT ON COLUMN t_user.password IS '密码';
COMMENT ON COLUMN t_user.phone_number IS '手机号';
COMMENT ON COLUMN t_user.email IS '邮箱';
COMMENT ON COLUMN t_user.create_time IS '创建时间';
COMMENT ON COLUMN t_user.modified_time IS '修改时间';

-- 默认管理员
INSERT INTO "public"."t_user" ("id", "user_name", "password", "phone_number", "email", "create_time", "modified_time")
VALUES
('9bc21547d12e4b82986f8f15d9934483', 'admin', 'admin', '15555555555', 'test@126.com', '2019-08-13 17:07:39', '2019-08-13 17:07:41');

-- 系统平台菜单表：t_menu
DROP TABLE IF EXISTS t_menu;
CREATE TABLE IF NOT EXISTS t_menu(
	id VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50) DEFAULT NULL,
	key VARCHAR(30) DEFAULT NULL,
	url VARCHAR(255) DEFAULT NULL,
	level VARCHAR(20) DEFAULT 0,
	icon_id VARCHAR(50) DEFAULT NULL,
	forbid VARCHAR(10) DEFAULT NULL,
	parent_id VARCHAR(50) DEFAULT NULL,
	long_code VARCHAR(255) DEFAULT NULL,
	create_time timestamp default null,
  modified_time timestamp default null
);
COMMENT ON TABLE t_menu IS '系统平台菜单表';
COMMENT ON COLUMN t_menu.id IS '表主键id';
COMMENT ON COLUMN t_menu.name IS '菜单名称';
COMMENT ON COLUMN t_menu.key IS '菜单key';
COMMENT ON COLUMN t_menu.url IS '菜单url';
COMMENT ON COLUMN t_menu.level IS '菜单级别';
COMMENT ON COLUMN t_menu.icon_id IS '图标id';
COMMENT ON COLUMN t_menu.forbid IS '是否禁用';
COMMENT ON COLUMN t_menu.parent_id IS '上一级id';
COMMENT ON COLUMN t_menu.long_code IS '根到当前级id组合';
COMMENT ON COLUMN t_menu.create_time IS '创建时间';
COMMENT ON COLUMN t_menu.modified_time IS '修改时间';

-- 系统平台图标表：t_icon
DROP TABLE IF EXISTS t_icon;
CREATE TABLE IF NOT EXISTS t_icon(
	id VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50) DEFAULT NULL,
	key VARCHAR(50) DEFAULT NULL,
	create_time timestamp default null,
  modified_time timestamp default null
);
COMMENT ON TABLE t_icon IS '系统平台图标表';
COMMENT ON COLUMN t_icon.id IS '表主键id';
COMMENT ON COLUMN t_icon.name IS '图标名称';
COMMENT ON COLUMN t_icon.key IS '图标key';
COMMENT ON COLUMN t_icon.create_time IS '创建时间';
COMMENT ON COLUMN t_icon.modified_time IS '修改时间';

-- 用户角色表：t_role
DROP TABLE IF EXISTS "t_role";
CREATE TABLE IF NOT EXISTS "t_role" (
	id varchar(50) primary key,
  role_name varchar(50) default null,
  role_word varchar(50) default null,
  status varchar(15) default null,
  menu_ids varchar(5000) default null,
  user_ids varchar(5000) default null,
  create_time timestamp default null,
  modified_time timestamp default null
);
COMMENT ON TABLE t_role IS '用户角色表t_role';
COMMENT ON COLUMN t_role.id IS '角色id';
COMMENT ON COLUMN t_role.role_name IS '角色名称';
COMMENT ON COLUMN t_role.role_word IS '角色字符';
COMMENT ON COLUMN t_role.status IS '状态';
COMMENT ON COLUMN t_role.menu_ids IS '菜单权限';
COMMENT ON COLUMN t_role.user_ids IS '用户权限';
COMMENT ON COLUMN t_role.create_time IS '创建时间';
COMMENT ON COLUMN t_role.modified_time IS '修改时间';

-- 在线用户表：t_online_user
DROP TABLE IF EXISTS t_online_user;
CREATE TABLE IF NOT EXISTS t_online_user (
	id varchar(50) default null,
  login_name varchar(50) primary key,
  login_ip varchar(50) default null,
  login_address varchar(15) default null,
  used_browser varchar(50) default null,
  used_window varchar(50) default null,
  online varchar(5) default null,
  login_time timestamp default null,
  last_time timestamp default null
);
COMMENT ON TABLE t_online_user IS '在线用户表t_online_user';
COMMENT ON COLUMN t_online_user.id IS '用户id';
COMMENT ON COLUMN t_online_user.login_name IS '登录账号';
COMMENT ON COLUMN t_online_user.login_ip IS '登录主机';
COMMENT ON COLUMN t_online_user.login_address IS '登录地点';
COMMENT ON COLUMN t_online_user.used_browser IS '浏览器';
COMMENT ON COLUMN t_online_user.used_window IS '操作系统';
COMMENT ON COLUMN t_online_user.online IS '登录状态';
COMMENT ON COLUMN t_online_user.login_time IS '登录时间';
COMMENT ON COLUMN t_online_user.last_time IS '最后访问时间';

ALTER TABLE "public"."t_online_user" ADD COLUMN "location" varchar(255);

INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('37a6836817534682a584723a25d7328c', '服务监控', 'service', '/resource/service', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_37a6836817534682a584723a25d7328c', '2019-11-29 17:36:05.244', '2019-11-29 17:36:05.244');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('b2a312193b2047ed9e0175218a416d5d', '系统管理', 'option', '', '1', '', 'false', '', 'b2a312193b2047ed9e0175218a416d5d', '2019-11-29 17:36:21.089', '2019-11-29 17:36:21.089');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('107082fcea894c75953952dfb1d11ced', '首页', 'home', '/home', '1', NULL, 'false', NULL, '107082fcea894c75953952dfb1d11ced', '2019-11-29 16:44:32.25', '2019-11-29 16:44:32.25');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('c4857bf9a7ee45429f42e87699018a86', '用户管理', 'user', NULL, '1', NULL, 'false', NULL, 'c4857bf9a7ee45429f42e87699018a86', '2019-11-29 16:44:56.932', '2019-11-29 16:44:56.932');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('53c53944d4974fef8ff81e7e15ab0464', '人员用户', 'list', '/user/list', '2', NULL, 'false', 'c4857bf9a7ee45429f42e87699018a86', 'c4857bf9a7ee45429f42e87699018a86_53c53944d4974fef8ff81e7e15ab0464', '2019-11-29 16:45:43.55', '2019-11-29 16:45:43.55');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('edd421821c7347529d49df12b2ad06a4', '角色权限', 'role', '/user/role', '2', NULL, 'false', 'c4857bf9a7ee45429f42e87699018a86', 'c4857bf9a7ee45429f42e87699018a86_edd421821c7347529d49df12b2ad06a4', '2019-11-29 16:46:06.768', '2019-11-29 16:46:06.768');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('e2ef7cd0b56046d987e7efb9ff22e619', '在线用户', 'online', '/user/online', '2', NULL, 'false', 'c4857bf9a7ee45429f42e87699018a86', 'c4857bf9a7ee45429f42e87699018a86_e2ef7cd0b56046d987e7efb9ff22e619', '2019-11-29 16:46:25.269', '2019-11-29 16:46:25.269');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('1f227378917f4f35af8a7328b41b647c', '协同管理', 'synergy', NULL, '1', NULL, 'false', NULL, '1f227378917f4f35af8a7328b41b647c', '2019-11-29 16:46:53.292', '2019-11-29 16:46:53.292');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('1e430887e2dc4099a5f6ad4907907002', '模型管理', 'model', '/synergy/model', '2', '', 'false', '1f227378917f4f35af8a7328b41b647c', '1f227378917f4f35af8a7328b41b647c_1e430887e2dc4099a5f6ad4907907002', '2019-11-29 16:53:18.669', '2019-11-29 16:53:18.669');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('8c11fde884374edd885fedb4cc2bd9fb', '资源管理', 'resource', '', '1', '', 'false', '', '8c11fde884374edd885fedb4cc2bd9fb', '2019-11-29 16:54:02.897', '2019-11-29 16:54:02.897');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('f55ca9ed9d60490f82baa65807301297', 'Druid数据库', 'druid', '', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_f55ca9ed9d60490f82baa65807301297', '2019-11-29 16:54:34.506', '2019-11-29 16:54:34.506');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('fc97503932894219b4aa5b440f18ca83', 'Eureka中心', 'eureka', '', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_fc97503932894219b4aa5b440f18ca83', '2019-11-29 16:54:51.82', '2019-11-29 16:54:51.82');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('6baac49489374cfb8853459da985665a', '流程管理', 'process', '/synergy/process', '2', '', 'false', '1f227378917f4f35af8a7328b41b647c', '1f227378917f4f35af8a7328b41b647c_6baac49489374cfb8853459da985665a', '2019-11-29 17:31:31.263', '2019-11-29 17:31:31.263');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('3db683676f2d4c1587954018f94d2ad5', '待办任务', 'todo', '/synergy/todo', '2', '', 'false', '1f227378917f4f35af8a7328b41b647c', '1f227378917f4f35af8a7328b41b647c_3db683676f2d4c1587954018f94d2ad5', '2019-11-29 17:32:10.129', '2019-11-29 17:32:10.129');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('108ecdae75ec414685dcdada3d6800cc', '操作日志', 'log', '/synergy/log', '2', '', 'false', '1f227378917f4f35af8a7328b41b647c', '1f227378917f4f35af8a7328b41b647c_108ecdae75ec414685dcdada3d6800cc', '2019-11-29 17:32:29.779', '2019-11-29 17:32:29.779');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('149f2e728da04380addd60f867881ad4', '事项日程', 'schedule', '/synergy/schedule', '2', '', 'false', '1f227378917f4f35af8a7328b41b647c', '1f227378917f4f35af8a7328b41b647c_149f2e728da04380addd60f867881ad4', '2019-11-29 17:33:16.349', '2019-11-29 17:33:16.349');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('8cd3e877836f4b94b586d55e4552d0dd', '环境监控', 'virtual', '/resource/virtual', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_8cd3e877836f4b94b586d55e4552d0dd', '2019-11-29 17:35:46.285', '2019-11-29 17:35:46.285');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('cda8273fce81467d84b85ae47671d9ad', '菜单管理', 'menu', '/option/menu', '2', '', 'false', 'b2a312193b2047ed9e0175218a416d5d', 'b2a312193b2047ed9e0175218a416d5d_cda8273fce81467d84b85ae47671d9ad', '2019-11-29 17:36:40.989', '2019-11-29 17:36:40.989');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('83faf7b22fa84a3f84f65e84e3413177', '图标管理', 'icon', '/option/icon', '2', '', 'false', 'b2a312193b2047ed9e0175218a416d5d', 'b2a312193b2047ed9e0175218a416d5d_83faf7b22fa84a3f84f65e84e3413177', '2019-11-29 17:36:58.331', '2019-11-29 17:36:58.331');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('52c39dd9e1f1490991adc2f9f2c6a898', '通知公告', 'notice', '/option/notice', '2', '', 'false', 'b2a312193b2047ed9e0175218a416d5d', 'b2a312193b2047ed9e0175218a416d5d_52c39dd9e1f1490991adc2f9f2c6a898', '2019-11-29 17:37:15.637', '2019-11-29 17:37:15.637');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('c245f1662cba4bb692a9b9646c3074fd', '接口文档', 'interface', '', '2', '', 'false', 'b2a312193b2047ed9e0175218a416d5d', 'b2a312193b2047ed9e0175218a416d5d_c245f1662cba4bb692a9b9646c3074fd', '2019-11-29 17:37:34.505', '2019-11-29 17:37:34.505');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('1de533e8d0b24546ad5b78087251de65', '缓存链路', 'trace', '', '2', '', 'false', 'c245f1662cba4bb692a9b9646c3074fd', 'b2a312193b2047ed9e0175218a416d5d_c245f1662cba4bb692a9b9646c3074fd_1de533e8d0b24546ad5b78087251de65', '2019-11-29 17:37:54.668', '2019-11-29 17:37:54.668');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('272f5d5af5514cfaa9c39cab6a3f57be', '业务服务', 'business', '', '2', '', 'false', 'c245f1662cba4bb692a9b9646c3074fd', 'b2a312193b2047ed9e0175218a416d5d_c245f1662cba4bb692a9b9646c3074fd_272f5d5af5514cfaa9c39cab6a3f57be', '2019-11-29 17:38:08.919', '2019-11-29 17:38:08.919');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('18a02337217e444797f36ce957d78e69', '了解系统', 'info', '/option/info', '2', '', 'false', 'b2a312193b2047ed9e0175218a416d5d', 'b2a312193b2047ed9e0175218a416d5d_18a02337217e444797f36ce957d78e69', '2019-11-29 17:38:26.645', '2019-11-29 17:38:26.645');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('cae9d45221914193b519bf98da7b5c98', 'RabbitMQ管理', 'rabbitmq', '', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_cae9d45221914193b519bf98da7b5c98', '2019-11-29 17:35:23.598', '2019-11-29 17:35:23.598');
INSERT INTO "public"."t_menu" ("id", "name", "key", "url", "level", "icon_id", "forbid", "parent_id", "long_code", "create_time", "modified_time") VALUES ('a9c74e4608e14373b234cfa5ce121693', '调用链追踪', 'zipkin', '/synergy/schedule', '2', '', 'false', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_a9c74e4608e14373b234cfa5ce121693', '2019-11-29 17:34:57.207', '2019-11-29 17:34:57.207');
