-- 操作日志记录：t_operator_log
DROP TABLE IF EXISTS "t_operator_log";
CREATE TABLE IF NOT EXISTS "t_operator_log" (
	id varchar(50) primary key,
	ip varchar(20) default null,
  user_name varchar(20) default null,
  operator_type INT default 0,
	operate_description varchar(255) default null,
	request_url varchar(2000) default null,
	protocal varchar(10) default null,
	params varchar(1000) default null,
	clz_name varchar(255) default null,
	method_name varchar(255) default null,
	exception_info varchar(20000) default null,
  operate_time timestamp default null
);
COMMENT ON TABLE t_operator_log IS '操作日志记录t_operator_log';
COMMENT ON COLUMN t_operator_log.id IS '操作日志id';
COMMENT ON COLUMN t_operator_log.ip IS '操作IP地址';
COMMENT ON COLUMN t_operator_log.user_name IS '操作用户';
COMMENT ON COLUMN t_operator_log.operator_type IS '操作类型';
COMMENT ON COLUMN t_operator_log.operate_description IS '操作描述';
COMMENT ON COLUMN t_operator_log.request_url IS '请求URL';
COMMENT ON COLUMN t_operator_log.protocal IS '请求协议';
COMMENT ON COLUMN t_operator_log.params IS '请求参数';
COMMENT ON COLUMN t_operator_log.clz_name IS '调用类';
COMMENT ON COLUMN t_operator_log.method_name IS '调用方法';
COMMENT ON COLUMN t_operator_log.exception_info IS '异常信息';
COMMENT ON COLUMN t_operator_log.operate_time IS '操作时间';