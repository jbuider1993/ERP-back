-- 系统通知消息表：t_message_record
DROP TABLE IF EXISTS "t_message_record";
CREATE TABLE IF NOT EXISTS "t_message_record" (
	id varchar(50) primary key,
	user_id varchar(50) default null,
	type INT default 0,
  title varchar(255) default null,
  description varchar(10000) default null,
	content varchar(50000) default null,
	create_time timestamp default null,
	modified_time timestamp default null
);
COMMENT ON TABLE t_message_record IS '系统通知消息表t_message_record';
COMMENT ON COLUMN t_message_record.id IS '消息id';
COMMENT ON COLUMN t_message_record.user_id IS '用户Id';
COMMENT ON COLUMN t_message_record.type IS '消息类型：1——通知；2——消息';
COMMENT ON COLUMN t_message_record.title IS '消息标题';
COMMENT ON COLUMN t_message_record.description IS '消息摘要';
COMMENT ON COLUMN t_message_record.content IS '消息内容';
COMMENT ON COLUMN t_message_record.create_time IS '创建时间';
COMMENT ON COLUMN t_message_record.modified_time IS '修改时间';