-- 虚拟环境监控表
CREATE TABLE IF NOT EXISTS "t_virtual_machine" (
    id varchar(50) primary key,
    virtual_name varchar(50) default null,
    machine_ip varchar(50) default null,
    system_type varchar(10) default null,
    total_cpu varchar(20) default null,
    total_memory varchar(20) default null,
    total_disk varchar(20) default null,
    create_time timestamp default null,
    modified_time timestamp default null
    );
COMMENT ON TABLE t_virtual_machine IS '虚拟环境监控表';
COMMENT ON COLUMN t_virtual_machine.id IS '虚拟容器id';
COMMENT ON COLUMN t_virtual_machine.virtual_name IS '容器名称';
COMMENT ON COLUMN t_virtual_machine.system_type IS '操作系统';
COMMENT ON COLUMN t_virtual_machine.machine_ip IS '容器IP';
COMMENT ON COLUMN t_virtual_machine.total_cpu IS '总CPU';
COMMENT ON COLUMN t_virtual_machine.total_memory IS '总内存';
COMMENT ON COLUMN t_virtual_machine.total_disk IS '总磁盘';
COMMENT ON COLUMN t_virtual_machine.create_time IS '创建时间';
COMMENT ON COLUMN t_virtual_machine.modified_time IS '修改时间';

-- 服务实例监控表
CREATE TABLE IF NOT EXISTS "t_service_instance" (
    id varchar(50) primary key,
    service_name varchar(100) default null,
    machine_ip varchar(50) default null,
    port varchar(5) default null,
    vm_option varchar(500) default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_service_instance IS '服务实例监控表';
COMMENT ON COLUMN t_service_instance.id IS '虚拟容器id';
COMMENT ON COLUMN t_service_instance.service_name IS '服务实例名';
COMMENT ON COLUMN t_service_instance.machine_ip IS '主机ip';
COMMENT ON COLUMN t_service_instance.port IS '端口';
COMMENT ON COLUMN t_service_instance.vm_option IS '启动参数';
COMMENT ON COLUMN t_service_instance.create_time IS '创建时间';
COMMENT ON COLUMN t_service_instance.modified_time IS '修改时间';

-- 虚拟环境监控记录表
CREATE TABLE IF NOT EXISTS "t_machine_record" (
    id varchar(50) primary key,
    create_time timestamp default null,
    machine_ip varchar(50) default null,
    total_cpu varchar(20) default null,
    used_cpu varchar(20) default null,
    total_memory varchar(20) default null,
    used_memory varchar(20) default null,
    total_disk varchar(20) default null,
    used_disk varchar(20) default null
);
COMMENT ON TABLE t_machine_record IS '虚拟环境监控表';
COMMENT ON COLUMN t_machine_record.id IS '虚拟容器id';
COMMENT ON COLUMN t_machine_record.create_time IS '创建时间';
COMMENT ON COLUMN t_machine_record.machine_ip IS '容器IP';
COMMENT ON COLUMN t_machine_record.total_cpu IS '总CPU';
COMMENT ON COLUMN t_machine_record.used_cpu IS '已使用CPU';
COMMENT ON COLUMN t_machine_record.total_memory IS '总内存';
COMMENT ON COLUMN t_machine_record.used_memory IS '已使用内存';
COMMENT ON COLUMN t_machine_record.total_disk IS '总磁盘';
COMMENT ON COLUMN t_machine_record.used_disk IS '已使用磁盘';

-- 服务实例监控记录表
CREATE TABLE IF NOT EXISTS "t_service_record" (
    id varchar(50) primary key,
    create_time timestamp default null,
    service_name varchar(100) default null,
    machine_ip varchar(50) default null,
    status varchar(10) default null,
    vm_option varchar(500) default null
);
COMMENT ON TABLE t_service_record IS '服务实例监控表';
COMMENT ON COLUMN t_service_record.id IS '虚拟容器id';
COMMENT ON COLUMN t_service_record.create_time IS '创建时间';
COMMENT ON COLUMN t_service_record.service_name IS '服务实例名';
COMMENT ON COLUMN t_service_record.machine_ip IS '主机ip';
COMMENT ON COLUMN t_service_record.status IS '服务状态';
COMMENT ON COLUMN t_service_record.vm_option IS '启动参数';
