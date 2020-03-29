-- 操作日志记录：t_operator_log
ALTER TABLE t_operator_log ADD COLUMN "style" varchar(10);
ALTER TABLE t_operator_log ADD COLUMN "service_name" varchar(100);
ALTER TABLE t_operator_log ADD COLUMN "port" INT;
ALTER TABLE t_operator_log ADD COLUMN "thread_name" varchar(100);
ALTER TABLE t_operator_log ADD COLUMN "status" varchar(10);

COMMENT ON COLUMN t_operator_log.style IS '请求方式';
COMMENT ON COLUMN t_operator_log.service_name IS '访问服务名';
COMMENT ON COLUMN t_operator_log.port IS '访问服务端口';
COMMENT ON COLUMN t_operator_log.thread_name IS '运行线程名';
COMMENT ON COLUMN t_operator_log.status IS '访问状态';
