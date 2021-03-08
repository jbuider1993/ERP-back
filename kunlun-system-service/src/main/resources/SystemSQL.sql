-- 系统Activiti流程数据库
DROP DATABASE IF EXISTS kunlun_activiti;
CREATE DATABASE kunlun_activiti ENCODING 'UTF8';
COMMENT ON DATABASE kunlun_activiti IS '系统Activiti流程数据库';

-- 系统业务数据数据库
DROP DATABASE IF EXISTS kunlun_system;
CREATE DATABASE kunlun_system ENCODING 'UTF8';
COMMENT ON DATABASE kunlun_system IS '系统业务数据数据库';
