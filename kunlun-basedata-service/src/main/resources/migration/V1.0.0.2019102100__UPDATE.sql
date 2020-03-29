-- 在线用户表：t_online_user
ALTER TABLE t_online_user ADD COLUMN "access_count" INT default 0;

COMMENT ON COLUMN t_online_user.access_count IS '访问量';