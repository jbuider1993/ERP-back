-- 部门表
CREATE TABLE IF NOT EXISTS t_department (
    id varchar(50) primary key,
    department_name varchar(100) default null,
    department_code varchar(100) default null,
    type int default 0,
    status int default 0,
    duty_desc varchar(500) default null,
    parent_id varchar(500) default null,
    long_code varchar(500) default null,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_department IS '部门表';
COMMENT ON COLUMN t_department.id IS '部门id';
COMMENT ON COLUMN t_department.department_name IS '部门名称';
COMMENT ON COLUMN t_department.department_code IS '部门编号';
COMMENT ON COLUMN t_department.type IS '部门类型(1——集团或公司；2-子公司；3-部门)';
COMMENT ON COLUMN t_department.status IS '状态(0——正常；1-停用)';
COMMENT ON COLUMN t_department.duty_desc IS '职责描述';
COMMENT ON COLUMN t_department.parent_id IS '父节点Id';
COMMENT ON COLUMN t_department.long_code IS '长Id';
COMMENT ON COLUMN t_department.create_time IS '创建时间';
COMMENT ON COLUMN t_department.modified_time IS '修改时间';

-- 岗位表
CREATE TABLE IF NOT EXISTS t_work_post (
    id varchar(50) primary key,
    post_name varchar(50) default null,
    post_code varchar(50) default null,
    duty_desc varchar(500) default null,
    status int default 0,
    create_time timestamp default null,
    modified_time timestamp default null
);
COMMENT ON TABLE t_work_post IS '岗位表';
COMMENT ON COLUMN t_work_post.id IS '岗位id';
COMMENT ON COLUMN t_work_post.post_name IS '岗位名称';
COMMENT ON COLUMN t_work_post.post_code IS '岗位编号';
COMMENT ON COLUMN t_work_post.duty_desc IS '职责描述';
COMMENT ON COLUMN t_work_post.status IS '状态(0——正常；1-停用)';
COMMENT ON COLUMN t_work_post.create_time IS '创建时间';
COMMENT ON COLUMN t_work_post.modified_time IS '修改时间';