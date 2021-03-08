package com.kunlun.system.config.dataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源：每执行一次数据库，动态获取DataSource
 *
 * 动态数据源实现切换方式有三种：
 * 1、注解方式——通过在Dao类加注解形式，如MultiDBDao
 * 2、动态切换——运用DbContextHolder的setDbType方法实现，如MultiDBService
 * 3、链接模板——通过给多数据源配置各自的JdbcTemplate(master和slave数据源各一个)，然后用JdbcTemplate访问数据库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger log = LogManager.getLogger();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataBaseType = DbContextHolder.getDbType();
        log.info(String.format("========== 当前数据源： %s", dataBaseType) + " ==========");
        return dataBaseType;
    }
}