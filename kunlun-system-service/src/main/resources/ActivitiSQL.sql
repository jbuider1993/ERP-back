-- 此SQL脚本是Activiti5.22.0的数据表,无需手动执行

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ge_property";
CREATE TABLE "public"."act_ge_property" (
  "name_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "value_" varchar(300) COLLATE "pg_catalog"."default",
  "rev_" int4
);

-- ----------------------------
-- Records of act_ge_property
-- ----------------------------
INSERT INTO "public"."act_ge_property" VALUES ('schema.version', '5.22.0.0', 1);
INSERT INTO "public"."act_ge_property" VALUES ('schema.history', 'create(5.22.0.0)', 1);
INSERT INTO "public"."act_ge_property" VALUES ('next.dbid', '27501', 12);

-- ----------------------------
-- Primary Key structure for table act_ge_property
-- ----------------------------
ALTER TABLE "public"."act_ge_property" ADD CONSTRAINT "act_ge_property_pkey" PRIMARY KEY ("name_");

-- ----------------------------
-- act_evt_log_log_nr__seq
-- ----------------------------
CREATE SEQUENCE act_evt_log_log_nr__seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99999999 CACHE 1;

-- ----------------------------
-- Table structure for act_evt_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_evt_log";
CREATE TABLE "public"."act_evt_log" (
  "log_nr_" int4 NOT NULL DEFAULT nextval('act_evt_log_log_nr__seq'::regclass),
  "type_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "time_stamp_" timestamp(6) NOT NULL,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "data_" bytea,
  "lock_owner_" varchar(255) COLLATE "pg_catalog"."default",
  "lock_time_" timestamp(6),
  "is_processed_" int2 DEFAULT 0
)
;

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ge_bytearray";
CREATE TABLE "public"."act_ge_bytearray" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "bytes_" bytea,
  "generated_" bool
)
;

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_actinst";
CREATE TABLE "public"."act_hi_actinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "act_id_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "call_proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "act_name_" varchar(255) COLLATE "pg_catalog"."default",
  "act_type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "start_time_" timestamp(6) NOT NULL,
  "end_time_" timestamp(6),
  "duration_" int8,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_attachment";
CREATE TABLE "public"."act_hi_attachment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "url_" varchar(4000) COLLATE "pg_catalog"."default",
  "content_id_" varchar(64) COLLATE "pg_catalog"."default",
  "time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_comment";
CREATE TABLE "public"."act_hi_comment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "time_" timestamp(6) NOT NULL,
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "action_" varchar(255) COLLATE "pg_catalog"."default",
  "message_" varchar(4000) COLLATE "pg_catalog"."default",
  "full_msg_" bytea
)
;

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_detail";
CREATE TABLE "public"."act_hi_detail" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "act_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "var_type_" varchar(64) COLLATE "pg_catalog"."default",
  "rev_" int4,
  "time_" timestamp(6) NOT NULL,
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_identitylink";
CREATE TABLE "public"."act_hi_identitylink" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_procinst";
CREATE TABLE "public"."act_hi_procinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "business_key_" varchar(255) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "start_time_" timestamp(6) NOT NULL,
  "end_time_" timestamp(6),
  "duration_" int8,
  "start_user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "start_act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "end_act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "super_process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "delete_reason_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "name_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_taskinst";
CREATE TABLE "public"."act_hi_taskinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_def_key_" varchar(255) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "owner_" varchar(255) COLLATE "pg_catalog"."default",
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "start_time_" timestamp(6) NOT NULL,
  "claim_time_" timestamp(6),
  "end_time_" timestamp(6),
  "duration_" int8,
  "delete_reason_" varchar(4000) COLLATE "pg_catalog"."default",
  "priority_" int4,
  "due_date_" timestamp(6),
  "form_key_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_hi_varinst";
CREATE TABLE "public"."act_hi_varinst" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "var_type_" varchar(100) COLLATE "pg_catalog"."default",
  "rev_" int4,
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default",
  "create_time_" timestamp(6),
  "last_updated_time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_id_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_group";
CREATE TABLE "public"."act_id_group" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_id_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_info";
CREATE TABLE "public"."act_id_info" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "user_id_" varchar(64) COLLATE "pg_catalog"."default",
  "type_" varchar(64) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default",
  "value_" varchar(255) COLLATE "pg_catalog"."default",
  "password_" bytea,
  "parent_id_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_id_membership
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_membership";
CREATE TABLE "public"."act_id_membership" (
  "user_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for act_id_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_id_user";
CREATE TABLE "public"."act_id_user" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "first_" varchar(255) COLLATE "pg_catalog"."default",
  "last_" varchar(255) COLLATE "pg_catalog"."default",
  "email_" varchar(255) COLLATE "pg_catalog"."default",
  "pwd_" varchar(255) COLLATE "pg_catalog"."default",
  "picture_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_procdef_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_procdef_info";
CREATE TABLE "public"."act_procdef_info" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "info_json_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_deployment";
CREATE TABLE "public"."act_re_deployment" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "deploy_time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_model";
CREATE TABLE "public"."act_re_model" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default",
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "create_time_" timestamp(6),
  "last_update_time_" timestamp(6),
  "version_" int4,
  "meta_info_" varchar(4000) COLLATE "pg_catalog"."default",
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "editor_source_value_id_" varchar(64) COLLATE "pg_catalog"."default",
  "editor_source_extra_value_id_" varchar(64) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_re_procdef";
CREATE TABLE "public"."act_re_procdef" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "key_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "version_" int4 NOT NULL,
  "deployment_id_" varchar(64) COLLATE "pg_catalog"."default",
  "resource_name_" varchar(4000) COLLATE "pg_catalog"."default",
  "dgrm_resource_name_" varchar(4000) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "has_start_form_key_" bool,
  "has_graphical_notation_" bool,
  "suspension_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_event_subscr";
CREATE TABLE "public"."act_ru_event_subscr" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "event_type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "event_name_" varchar(255) COLLATE "pg_catalog"."default",
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "activity_id_" varchar(64) COLLATE "pg_catalog"."default",
  "configuration_" varchar(255) COLLATE "pg_catalog"."default",
  "created_" timestamp(6) NOT NULL,
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_execution";
CREATE TABLE "public"."act_ru_execution" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "business_key_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "super_exec_" varchar(64) COLLATE "pg_catalog"."default",
  "act_id_" varchar(255) COLLATE "pg_catalog"."default",
  "is_active_" bool,
  "is_concurrent_" bool,
  "is_scope_" bool,
  "is_event_scope_" bool,
  "suspension_state_" int4,
  "cached_ent_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "lock_time_" timestamp(6)
)
;

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_identitylink";
CREATE TABLE "public"."act_ru_identitylink" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "group_id_" varchar(255) COLLATE "pg_catalog"."default",
  "type_" varchar(255) COLLATE "pg_catalog"."default",
  "user_id_" varchar(255) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_job";
CREATE TABLE "public"."act_ru_job" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "lock_exp_time_" timestamp(6),
  "lock_owner_" varchar(255) COLLATE "pg_catalog"."default",
  "exclusive_" bool,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "process_instance_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "retries_" int4,
  "exception_stack_id_" varchar(64) COLLATE "pg_catalog"."default",
  "exception_msg_" varchar(4000) COLLATE "pg_catalog"."default",
  "duedate_" timestamp(6),
  "repeat_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_type_" varchar(255) COLLATE "pg_catalog"."default",
  "handler_cfg_" varchar(4000) COLLATE "pg_catalog"."default",
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying
)
;

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_task";
CREATE TABLE "public"."act_ru_task" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_def_id_" varchar(64) COLLATE "pg_catalog"."default",
  "name_" varchar(255) COLLATE "pg_catalog"."default",
  "parent_task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "description_" varchar(4000) COLLATE "pg_catalog"."default",
  "task_def_key_" varchar(255) COLLATE "pg_catalog"."default",
  "owner_" varchar(255) COLLATE "pg_catalog"."default",
  "assignee_" varchar(255) COLLATE "pg_catalog"."default",
  "delegation_" varchar(64) COLLATE "pg_catalog"."default",
  "priority_" int4,
  "create_time_" timestamp(6),
  "due_date_" timestamp(6),
  "category_" varchar(255) COLLATE "pg_catalog"."default",
  "suspension_state_" int4,
  "tenant_id_" varchar(255) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "form_key_" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS "public"."act_ru_variable";
CREATE TABLE "public"."act_ru_variable" (
  "id_" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "rev_" int4,
  "type_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name_" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "execution_id_" varchar(64) COLLATE "pg_catalog"."default",
  "proc_inst_id_" varchar(64) COLLATE "pg_catalog"."default",
  "task_id_" varchar(64) COLLATE "pg_catalog"."default",
  "bytearray_id_" varchar(64) COLLATE "pg_catalog"."default",
  "double_" float8,
  "long_" int8,
  "text_" varchar(4000) COLLATE "pg_catalog"."default",
  "text2_" varchar(4000) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table act_evt_log
-- ----------------------------
ALTER TABLE "public"."act_evt_log" ADD CONSTRAINT "act_evt_log_pkey" PRIMARY KEY ("log_nr_");

-- ----------------------------
-- Indexes structure for table act_ge_bytearray
-- ----------------------------
CREATE INDEX "act_idx_bytear_depl" ON "public"."act_ge_bytearray" USING btree (
  "deployment_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ge_bytearray
-- ----------------------------
ALTER TABLE "public"."act_ge_bytearray" ADD CONSTRAINT "act_ge_bytearray_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ge_bytearray
-- ----------------------------
ALTER TABLE "public"."act_ge_bytearray" ADD CONSTRAINT "act_fk_bytearr_depl" FOREIGN KEY ("deployment_id_") REFERENCES "public"."act_re_deployment" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_hi_actinst
-- ----------------------------
CREATE INDEX "act_idx_hi_act_inst_end" ON "public"."act_hi_actinst" USING btree (
  "end_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_exec" ON "public"."act_hi_actinst" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "act_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_procinst" ON "public"."act_hi_actinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "act_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_act_inst_start" ON "public"."act_hi_actinst" USING btree (
  "start_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_actinst
-- ----------------------------
ALTER TABLE "public"."act_hi_actinst" ADD CONSTRAINT "act_hi_actinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_hi_attachment
-- ----------------------------
ALTER TABLE "public"."act_hi_attachment" ADD CONSTRAINT "act_hi_attachment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_hi_comment
-- ----------------------------
ALTER TABLE "public"."act_hi_comment" ADD CONSTRAINT "act_hi_comment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_detail
-- ----------------------------
CREATE INDEX "act_idx_hi_detail_act_inst" ON "public"."act_hi_detail" USING btree (
  "act_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_name" ON "public"."act_hi_detail" USING btree (
  "name_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_proc_inst" ON "public"."act_hi_detail" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_task_id" ON "public"."act_hi_detail" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_detail_time" ON "public"."act_hi_detail" USING btree (
  "time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_detail
-- ----------------------------
ALTER TABLE "public"."act_hi_detail" ADD CONSTRAINT "act_hi_detail_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_identitylink
-- ----------------------------
CREATE INDEX "act_idx_hi_ident_lnk_procinst" ON "public"."act_hi_identitylink" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_ident_lnk_task" ON "public"."act_hi_identitylink" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_ident_lnk_user" ON "public"."act_hi_identitylink" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_identitylink
-- ----------------------------
ALTER TABLE "public"."act_hi_identitylink" ADD CONSTRAINT "act_hi_identitylink_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_procinst
-- ----------------------------
CREATE INDEX "act_idx_hi_pro_i_buskey" ON "public"."act_hi_procinst" USING btree (
  "business_key_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_pro_inst_end" ON "public"."act_hi_procinst" USING btree (
  "end_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table act_hi_procinst
-- ----------------------------
ALTER TABLE "public"."act_hi_procinst" ADD CONSTRAINT "act_hi_procinst_proc_inst_id__key" UNIQUE ("proc_inst_id_");

-- ----------------------------
-- Primary Key structure for table act_hi_procinst
-- ----------------------------
ALTER TABLE "public"."act_hi_procinst" ADD CONSTRAINT "act_hi_procinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_taskinst
-- ----------------------------
CREATE INDEX "act_idx_hi_task_inst_procinst" ON "public"."act_hi_taskinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_taskinst
-- ----------------------------
ALTER TABLE "public"."act_hi_taskinst" ADD CONSTRAINT "act_hi_taskinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_hi_varinst
-- ----------------------------
CREATE INDEX "act_idx_hi_procvar_name_type" ON "public"."act_hi_varinst" USING btree (
  "name_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "var_type_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_procvar_proc_inst" ON "public"."act_hi_varinst" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_hi_procvar_task_id" ON "public"."act_hi_varinst" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_hi_varinst
-- ----------------------------
ALTER TABLE "public"."act_hi_varinst" ADD CONSTRAINT "act_hi_varinst_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_id_group
-- ----------------------------
ALTER TABLE "public"."act_id_group" ADD CONSTRAINT "act_id_group_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Primary Key structure for table act_id_info
-- ----------------------------
ALTER TABLE "public"."act_id_info" ADD CONSTRAINT "act_id_info_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_id_membership
-- ----------------------------
CREATE INDEX "act_idx_memb_group" ON "public"."act_id_membership" USING btree (
  "group_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_memb_user" ON "public"."act_id_membership" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_id_membership
-- ----------------------------
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_id_membership_pkey" PRIMARY KEY ("user_id_", "group_id_");

-- ----------------------------
-- Foreign Keys structure for table act_id_membership
-- ----------------------------
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_fk_memb_group" FOREIGN KEY ("group_id_") REFERENCES "public"."act_id_group" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_id_membership" ADD CONSTRAINT "act_fk_memb_user" FOREIGN KEY ("user_id_") REFERENCES "public"."act_id_user" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Primary Key structure for table act_id_user
-- ----------------------------
ALTER TABLE "public"."act_id_user" ADD CONSTRAINT "act_id_user_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_procdef_info
-- ----------------------------
CREATE INDEX "act_idx_procdef_info_json" ON "public"."act_procdef_info" USING btree (
  "info_json_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_procdef_info_proc" ON "public"."act_procdef_info" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_uniq_info_procdef" UNIQUE ("proc_def_id_");

-- ----------------------------
-- Primary Key structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_procdef_info_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_procdef_info
-- ----------------------------
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_fk_info_json_ba" FOREIGN KEY ("info_json_id_") REFERENCES "public"."act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_procdef_info" ADD CONSTRAINT "act_fk_info_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "public"."act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Primary Key structure for table act_re_deployment
-- ----------------------------
ALTER TABLE "public"."act_re_deployment" ADD CONSTRAINT "act_re_deployment_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_re_model
-- ----------------------------
CREATE INDEX "act_idx_model_deployment" ON "public"."act_re_model" USING btree (
  "deployment_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_model_source" ON "public"."act_re_model" USING btree (
  "editor_source_value_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_model_source_extra" ON "public"."act_re_model" USING btree (
  "editor_source_extra_value_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_re_model
-- ----------------------------
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_re_model_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_re_model
-- ----------------------------
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_deployment" FOREIGN KEY ("deployment_id_") REFERENCES "public"."act_re_deployment" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_source" FOREIGN KEY ("editor_source_value_id_") REFERENCES "public"."act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_re_model" ADD CONSTRAINT "act_fk_model_source_extra" FOREIGN KEY ("editor_source_extra_value_id_") REFERENCES "public"."act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Uniques structure for table act_re_procdef
-- ----------------------------
ALTER TABLE "public"."act_re_procdef" ADD CONSTRAINT "act_uniq_procdef" UNIQUE ("key_", "version_", "tenant_id_");

-- ----------------------------
-- Primary Key structure for table act_re_procdef
-- ----------------------------
ALTER TABLE "public"."act_re_procdef" ADD CONSTRAINT "act_re_procdef_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Indexes structure for table act_ru_event_subscr
-- ----------------------------
CREATE INDEX "act_idx_event_subscr" ON "public"."act_ru_event_subscr" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_event_subscr_config_" ON "public"."act_ru_event_subscr" USING btree (
  "configuration_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_event_subscr
-- ----------------------------
ALTER TABLE "public"."act_ru_event_subscr" ADD CONSTRAINT "act_ru_event_subscr_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_event_subscr
-- ----------------------------
ALTER TABLE "public"."act_ru_event_subscr" ADD CONSTRAINT "act_fk_event_exec" FOREIGN KEY ("execution_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_ru_execution
-- ----------------------------
CREATE INDEX "act_idx_exe_parent" ON "public"."act_ru_execution" USING btree (
  "parent_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_procdef" ON "public"."act_ru_execution" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_procinst" ON "public"."act_ru_execution" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exe_super" ON "public"."act_ru_execution" USING btree (
  "super_exec_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_exec_buskey" ON "public"."act_ru_execution" USING btree (
  "business_key_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_execution
-- ----------------------------
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_ru_execution_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_execution
-- ----------------------------
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_parent" FOREIGN KEY ("parent_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "public"."act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_execution" ADD CONSTRAINT "act_fk_exe_super" FOREIGN KEY ("super_exec_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_ru_identitylink
-- ----------------------------
CREATE INDEX "act_idx_athrz_procedef" ON "public"."act_ru_identitylink" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_ident_lnk_group" ON "public"."act_ru_identitylink" USING btree (
  "group_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_ident_lnk_user" ON "public"."act_ru_identitylink" USING btree (
  "user_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_idl_procinst" ON "public"."act_ru_identitylink" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_tskass_task" ON "public"."act_ru_identitylink" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_identitylink
-- ----------------------------
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_ru_identitylink_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_identitylink
-- ----------------------------
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_athrz_procedef" FOREIGN KEY ("proc_def_id_") REFERENCES "public"."act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_idl_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_identitylink" ADD CONSTRAINT "act_fk_tskass_task" FOREIGN KEY ("task_id_") REFERENCES "public"."act_ru_task" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_ru_job
-- ----------------------------
CREATE INDEX "act_idx_job_exception" ON "public"."act_ru_job" USING btree (
  "exception_stack_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_job
-- ----------------------------
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_ru_job_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_job
-- ----------------------------
ALTER TABLE "public"."act_ru_job" ADD CONSTRAINT "act_fk_job_exception" FOREIGN KEY ("exception_stack_id_") REFERENCES "public"."act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_ru_task
-- ----------------------------
CREATE INDEX "act_idx_task_create" ON "public"."act_ru_task" USING btree (
  "create_time_" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_exec" ON "public"."act_ru_task" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_procdef" ON "public"."act_ru_task" USING btree (
  "proc_def_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_task_procinst" ON "public"."act_ru_task" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_task
-- ----------------------------
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_ru_task_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_task
-- ----------------------------
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_exe" FOREIGN KEY ("execution_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_procdef" FOREIGN KEY ("proc_def_id_") REFERENCES "public"."act_re_procdef" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_task" ADD CONSTRAINT "act_fk_task_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Indexes structure for table act_ru_variable
-- ----------------------------
CREATE INDEX "act_idx_var_bytearray" ON "public"."act_ru_variable" USING btree (
  "bytearray_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_var_exe" ON "public"."act_ru_variable" USING btree (
  "execution_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_var_procinst" ON "public"."act_ru_variable" USING btree (
  "proc_inst_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "act_idx_variable_task_id" ON "public"."act_ru_variable" USING btree (
  "task_id_" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table act_ru_variable
-- ----------------------------
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_ru_variable_pkey" PRIMARY KEY ("id_");

-- ----------------------------
-- Foreign Keys structure for table act_ru_variable
-- ----------------------------
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_bytearray" FOREIGN KEY ("bytearray_id_") REFERENCES "public"."act_ge_bytearray" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_exe" FOREIGN KEY ("execution_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."act_ru_variable" ADD CONSTRAINT "act_fk_var_procinst" FOREIGN KEY ("proc_inst_id_") REFERENCES "public"."act_ru_execution" ("id_") ON DELETE NO ACTION ON UPDATE NO ACTION;
