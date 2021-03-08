-- 系统用户表
CREATE TABLE IF NOT EXISTS t_system_user (
    id varchar(50) primary key,
    user_name varchar(50) default null,
    password varchar(50) default null,
    sex varchar(4) default '男',
    phone_number varchar(15) default null,
    email varchar(50) default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_system_user IS '系统用户表';
COMMENT ON COLUMN t_system_user.id IS '用户id';
COMMENT ON COLUMN t_system_user.user_name IS '账号';
COMMENT ON COLUMN t_system_user.password IS '密码';
COMMENT ON COLUMN t_system_user.sex IS '性别';
COMMENT ON COLUMN t_system_user.phone_number IS '手机号';
COMMENT ON COLUMN t_system_user.email IS '邮箱';
COMMENT ON COLUMN t_system_user.create_time IS '创建时间';
COMMENT ON COLUMN t_system_user.modified_time IS '修改时间';

-- 系统用户角色表
CREATE TABLE IF NOT EXISTS t_user_role (
	id varchar(50) primary key,
    role_name varchar(50) default null,
    role_word varchar(50) default null,
    status varchar(15) default null,
    menu_ids varchar(5000) default null,
    user_ids varchar(5000) default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_user_role IS '系统用户角色表';
COMMENT ON COLUMN t_user_role.id IS '角色id';
COMMENT ON COLUMN t_user_role.role_name IS '角色名称';
COMMENT ON COLUMN t_user_role.role_word IS '角色字符';
COMMENT ON COLUMN t_user_role.status IS '状态';
COMMENT ON COLUMN t_user_role.menu_ids IS '菜单权限';
COMMENT ON COLUMN t_user_role.user_ids IS '用户权限';
COMMENT ON COLUMN t_user_role.create_time IS '创建时间';
COMMENT ON COLUMN t_user_role.modified_time IS '修改时间';

-- 系统在线用户表
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
COMMENT ON TABLE t_online_user IS '系统在线用户表';
COMMENT ON COLUMN t_online_user.id IS '用户id';
COMMENT ON COLUMN t_online_user.login_name IS '登录账号';
COMMENT ON COLUMN t_online_user.login_ip IS '登录主机';
COMMENT ON COLUMN t_online_user.login_address IS '登录地点';
COMMENT ON COLUMN t_online_user.used_browser IS '浏览器';
COMMENT ON COLUMN t_online_user.used_window IS '操作系统';
COMMENT ON COLUMN t_online_user.online IS '登录状态';
COMMENT ON COLUMN t_online_user.login_time IS '登录时间';
COMMENT ON COLUMN t_online_user.last_time IS '最后访问时间';

-- 系统显示菜单表
CREATE TABLE IF NOT EXISTS t_display_menu (
	id VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50) DEFAULT NULL,
	key VARCHAR(30) DEFAULT NULL,
	url VARCHAR(255) DEFAULT NULL,
	icon VARCHAR(50) DEFAULT NULL,
	show BOOLEAN DEFAULT true,
	carry_token BOOLEAN DEFAULT true,
	parent_id VARCHAR(50) DEFAULT NULL,
	long_code VARCHAR(255) DEFAULT NULL,
	create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_display_menu IS '系统显示菜单表';
COMMENT ON COLUMN t_display_menu.id IS '表主键id';
COMMENT ON COLUMN t_display_menu.name IS '菜单名称';
COMMENT ON COLUMN t_display_menu.key IS '菜单key';
COMMENT ON COLUMN t_display_menu.url IS '菜单url';
COMMENT ON COLUMN t_display_menu.icon IS '图标';
COMMENT ON COLUMN t_display_menu.show IS '是否显示';
COMMENT ON COLUMN t_display_menu.carry_token IS '是否携带Token';
COMMENT ON COLUMN t_display_menu.parent_id IS '上一级id';
COMMENT ON COLUMN t_display_menu.long_code IS '根到当前级id组合';
COMMENT ON COLUMN t_display_menu.create_time IS '创建时间';
COMMENT ON COLUMN t_display_menu.modified_time IS '修改时间';

-- 系统图标表
CREATE TABLE IF NOT EXISTS t_display_icon (
	id VARCHAR(50) PRIMARY KEY,
	name VARCHAR(50) DEFAULT NULL,
	key VARCHAR(50) DEFAULT NULL,
	create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_display_icon IS '系统图标表';
COMMENT ON COLUMN t_display_icon.id IS '表主键id';
COMMENT ON COLUMN t_display_icon.name IS '图标名称';
COMMENT ON COLUMN t_display_icon.key IS '图标key';
COMMENT ON COLUMN t_display_icon.create_time IS '创建时间';
COMMENT ON COLUMN t_display_icon.modified_time IS '修改时间';
