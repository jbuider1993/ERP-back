-- 增加系统菜单
INSERT INTO "public"."t_display_menu"("id", "name", "key", "url", "icon", "show", "carry_token", "parent_id", "long_code", "create_time", "modified_time") VALUES ('cae9d45221914193b519bf98da756432', 'Hystrix监控', 'hystrix', 'http://localhost:8015/hystrix', 'ri-search-eye-line', 't', 'f', '8c11fde884374edd885fedb4cc2bd9fb', '8c11fde884374edd885fedb4cc2bd9fb_cae9d45221914193b519bf98da756432', '2019-11-29 17:35:23.598', '2019-11-29 17:35:23.598');

-- 修改在线用户表主键
ALTER TABLE "public"."t_online_user" DROP CONSTRAINT "t_online_user_pkey", ADD CONSTRAINT "t_online_user_pkey" PRIMARY KEY ("id");