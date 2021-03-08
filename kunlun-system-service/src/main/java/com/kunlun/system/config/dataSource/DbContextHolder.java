package com.kunlun.system.config.dataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 动态数据源上下文管理
 */
public class DbContextHolder {

    private static Logger log = LogManager.getLogger();

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDbType(String dbType) {
        String dataBaseType = contextHolder.get() == null ? DataSourceType.MASTER.getKey() : contextHolder.get();
        log.info(String.format("========== 当前数据源： %s", dataBaseType) + " ==========");
        if (dbType == null) {
            throw new NullPointerException();
        } else if (!isContainsDataSource(dbType)) {
            log.info("========== 数据库：" + dbType + " 不存在，请检查 ==========");
            throw new NoSuchElementException("不存在要求的数据库");
        } else if (dbType.equals(dataBaseType)) {
            return;
        }
        contextHolder.set(dbType);
        log.info(String.format("========== 切换至[ %s ]数据源", dbType) + " ==========");
    }

    public static String getDbType() {
        return contextHolder.get() == null ? DataSourceType.MASTER.getKey() : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

    public static boolean isContainsDataSource(String dataSourceId) {
        DataSourceType[] arrays = DataSourceType.values();
        List<String> dbTypes = Arrays.stream(arrays).map(obj -> obj.getKey()).collect(Collectors.toList());
        return dbTypes.contains(dataSourceId);
    }
}
