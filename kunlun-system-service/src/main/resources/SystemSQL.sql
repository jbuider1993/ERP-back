-- 创建Activiti流程数据库：scmp_activiti
DROP DATABASE IF EXISTS scmp_activiti;
CREATE DATABASE scmp_activiti ENCODING 'UTF8';

-- 创建平台数据库：scmp_system
DROP DATABASE IF EXISTS scmp_system;
CREATE DATABASE scmp_system ENCODING 'UTF8';

-- 虚拟机容器表：t_machine
DROP TABLE IF EXISTS "t_machine";
CREATE TABLE IF NOT EXISTS "t_machine" (
	id varchar(50) primary key,
  machine_name varchar(50) default null,
  machine_ip varchar(50) default null,
  virtual_ip varchar(50) default null,
  total_cpu float4 default null,
  total_memory float4 default null,
  create_time timestamp default null,
  modified_time timestamp default null
);
COMMENT ON TABLE t_machine IS '虚拟机容器表t_machine';
COMMENT ON COLUMN t_machine.id IS '虚拟容器id';
COMMENT ON COLUMN t_machine.machine_name IS '容器名称';
COMMENT ON COLUMN t_machine.machine_ip IS '容器IP';
COMMENT ON COLUMN t_machine.virtual_ip IS '虚拟IP';
COMMENT ON COLUMN t_machine.total_cpu IS '总CPU';
COMMENT ON COLUMN t_machine.total_memory IS '总内存';
COMMENT ON COLUMN t_machine.create_time IS '创建时间';
COMMENT ON COLUMN t_machine.modified_time IS '修改时间';

-- 虚拟机容器信息采集表：t_collect_machine
DROP TABLE IF EXISTS "t_collect_machine";
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
COMMENT ON TABLE t_collect_machine IS '虚拟机容器信息采集表t_collect_machine';
COMMENT ON COLUMN t_collect_machine.id IS '虚拟容器id';
COMMENT ON COLUMN t_collect_machine.machine_ip IS '容器IP';
COMMENT ON COLUMN t_collect_machine.total_cpu IS '总CPU';
COMMENT ON COLUMN t_collect_machine.total_memory IS '总内存';
COMMENT ON COLUMN t_collect_machine.used_cpu IS '已使用CPU';
COMMENT ON COLUMN t_collect_machine.used_memory IS '已使用内存';
COMMENT ON COLUMN t_collect_machine.machine_id IS '虚拟机容器表id';
COMMENT ON COLUMN t_collect_machine.create_time IS '创建时间';
COMMENT ON COLUMN t_collect_machine.modified_time IS '修改时间';