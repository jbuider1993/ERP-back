-- 系统事项日程表
CREATE TABLE IF NOT EXISTS t_schedule_plan (
	id varchar(50) primary key,
	theme varchar(50) default null,
	theme_color varchar(50) default null,
    start_time timestamp default null,
    end_time timestamp default null,
	participants varchar(1000) default null,
	content varchar(5000) default null,
	create_time timestamp default null,
	modified_time timestamp default null
);
COMMENT ON TABLE t_schedule_plan IS '系统事项日程表';
COMMENT ON COLUMN t_schedule_plan.id IS '日程id';
COMMENT ON COLUMN t_schedule_plan.theme IS '主题';
COMMENT ON COLUMN t_schedule_plan.theme_color IS '主题颜色';
COMMENT ON COLUMN t_schedule_plan.start_time IS '开始时间';
COMMENT ON COLUMN t_schedule_plan.end_time IS '结束时间';
COMMENT ON COLUMN t_schedule_plan.participants IS '参与人';
COMMENT ON COLUMN t_schedule_plan.content IS '日程内容';
COMMENT ON COLUMN t_schedule_plan.create_time IS '创建时间';
COMMENT ON COLUMN t_schedule_plan.modified_time IS '修改时间';
