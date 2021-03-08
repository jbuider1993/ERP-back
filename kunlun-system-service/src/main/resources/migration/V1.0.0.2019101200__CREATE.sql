-- 系统操作日志记录表
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
COMMENT ON TABLE t_operator_log IS '系统操作日志记录表';
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

-- 系统虚拟机表
CREATE TABLE IF NOT EXISTS "t_virtual_machine" (
	id varchar(50) primary key,
    machine_name varchar(50) default null,
    machine_ip varchar(50) default null,
    virtual_ip varchar(50) default null,
    total_cpu float4 default null,
    total_memory float4 default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_virtual_machine IS '系统虚拟机表';
COMMENT ON COLUMN t_virtual_machine.id IS '虚拟容器id';
COMMENT ON COLUMN t_virtual_machine.machine_name IS '容器名称';
COMMENT ON COLUMN t_virtual_machine.machine_ip IS '容器IP';
COMMENT ON COLUMN t_virtual_machine.virtual_ip IS '虚拟IP';
COMMENT ON COLUMN t_virtual_machine.total_cpu IS '总CPU';
COMMENT ON COLUMN t_virtual_machine.total_memory IS '总内存';
COMMENT ON COLUMN t_virtual_machine.create_time IS '创建时间';
COMMENT ON COLUMN t_virtual_machine.modified_time IS '修改时间';

-- 系统虚拟机信息采集表
CREATE TABLE IF NOT EXISTS "t_collect_machine" (
	id varchar(50) primary key,
    machine_ip varchar(50) default null,
    total_cpu float4 default null,
    total_memory float4 default null,
    used_cpu float4 default null,
    used_memory float4 default null,
    machine_id varchar(50) default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_collect_machine IS '系统虚拟机信息采集表';
COMMENT ON COLUMN t_collect_machine.id IS '虚拟容器id';
COMMENT ON COLUMN t_collect_machine.machine_ip IS '容器IP';
COMMENT ON COLUMN t_collect_machine.total_cpu IS '总CPU';
COMMENT ON COLUMN t_collect_machine.total_memory IS '总内存';
COMMENT ON COLUMN t_collect_machine.used_cpu IS '已使用CPU';
COMMENT ON COLUMN t_collect_machine.used_memory IS '已使用内存';
COMMENT ON COLUMN t_collect_machine.machine_id IS '虚拟机容器表id';
COMMENT ON COLUMN t_collect_machine.create_time IS '创建时间';
COMMENT ON COLUMN t_collect_machine.modified_time IS '修改时间';