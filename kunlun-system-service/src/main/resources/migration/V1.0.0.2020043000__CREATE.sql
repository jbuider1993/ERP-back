-- 系统通知消息表
CREATE TABLE IF NOT EXISTS t_message_notice (
	id varchar(50) primary key,
	user_id varchar(50) default null,
	type INT default 0,
    title varchar(255) default null,
    description varchar(10000) default null,
	content varchar(50000) default null,
	create_time timestamp default null,
	modified_time timestamp default null
);
COMMENT ON TABLE t_message_notice IS '系统通知消息表';
COMMENT ON COLUMN t_message_notice.id IS '消息id';
COMMENT ON COLUMN t_message_notice.user_id IS '用户Id';
COMMENT ON COLUMN t_message_notice.type IS '消息类型：1——通知；2——消息';
COMMENT ON COLUMN t_message_notice.title IS '消息标题';
COMMENT ON COLUMN t_message_notice.description IS '消息摘要';
COMMENT ON COLUMN t_message_notice.content IS '消息内容';
COMMENT ON COLUMN t_message_notice.create_time IS '创建时间';
COMMENT ON COLUMN t_message_notice.modified_time IS '修改时间';

-- 系统数据字典主表
CREATE TABLE IF NOT EXISTS t_system_dictionary (
	id varchar(50) primary key,
	dict_name varchar(50) default null,
	dict_code varchar(50) default 0,
	status varchar(50) default 0,
    description varchar(10000) default null,
	create_time timestamp default null,
	modified_time timestamp default null
);
COMMENT ON TABLE t_system_dictionary IS '系统数据字典主表';
COMMENT ON COLUMN t_system_dictionary.id IS '字典主表id';
COMMENT ON COLUMN t_system_dictionary.dict_name IS '字典名称';
COMMENT ON COLUMN t_system_dictionary.dict_code IS '字典编码';
COMMENT ON COLUMN t_system_dictionary.status IS '字典状态';
COMMENT ON COLUMN t_system_dictionary.description IS '备注描述';
COMMENT ON COLUMN t_system_dictionary.create_time IS '创建时间';
COMMENT ON COLUMN t_system_dictionary.modified_time IS '修改时间';

-- 系统数据字典子表
CREATE TABLE IF NOT EXISTS t_system_dictionary_sub (
	id varchar(50) primary key,
	dict_sub_name varchar(50) default null,
	dict_sub_code varchar(50) default null,
	dict_value INT default 0,
    description varchar(10000) default null,
    dict_id varchar(50) default null,
	create_time timestamp default null,
	modified_time timestamp default null
);
COMMENT ON TABLE t_system_dictionary_sub IS '系统数据字典子表';
COMMENT ON COLUMN t_system_dictionary_sub.id IS '数据字典子表id';
COMMENT ON COLUMN t_system_dictionary_sub.dict_sub_code IS '编码';
COMMENT ON COLUMN t_system_dictionary_sub.dict_value IS '字典值';
COMMENT ON COLUMN t_system_dictionary_sub.description IS '备注描述';
COMMENT ON COLUMN t_system_dictionary_sub.dict_id IS '字典主表Id';
COMMENT ON COLUMN t_system_dictionary_sub.create_time IS '创建时间';
COMMENT ON COLUMN t_system_dictionary_sub.modified_time IS '修改时间';