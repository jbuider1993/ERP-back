package com.kunlun.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类
 */
public class SchemaUtil {

    private static Logger logger = LogManager.getLogger();

    public static void checkAndInitSchema(String schemaName, String schemaDesc) {
        try {
            // 获取数据库连接
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection conn = java.sql.DriverManager.getConnection(url, "postgres", "java");

            // 检查kunlun_home数据库是否已创建
            String checkSQL = "SELECT u.datname  FROM pg_catalog.pg_database u where u.datname='" + schemaName + "';";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(checkSQL);
            if (resultSet.next()) {
                logger.info("Schema " + schemaName + " is already created.");
            } else {
                logger.info("Schema " + schemaName + " not created. Creating ......");
                String initSQL = "CREATE DATABASE " + schemaName + " ENCODING 'UTF8';COMMENT ON DATABASE " + schemaName + " IS '" + schemaDesc + "';";
                statement.execute(initSQL);
            }

            // 关闭资源
            statement.close();
            conn.close();
        } catch (SQLException throwables) {
            logger.error("Application checkAndInitSchema Error: ", throwables);
            throw new RuntimeException("Application checkAndInitSchema Exception");
        }
    }
}
