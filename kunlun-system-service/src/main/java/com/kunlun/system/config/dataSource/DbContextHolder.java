package com.kunlun.system.config.dataSource;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 动态数据源上下文管理
 */
public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDbType(String dbType) {
        String dataBaseType = contextHolder.get() == null ? DataSourceType.MASTER.getKey() : contextHolder.get();
        System.out.println(String.format("========== 当前数据源： %s", dataBaseType) + " ==========");
        if (dbType == null) {
            throw new NullPointerException();
        } else if (!isContainsDataSource(dbType)) {
            System.out.println("========== 数据库：" + dbType + " 不存在，请检查 ==========");
            throw new NoSuchElementException("不存在要求的数据库");
        } else if (dbType.equals(dataBaseType)) {
            return;
        }
        contextHolder.set(dbType);
        System.out.println(String.format("========== 切换至[ %s ]数据源", dbType) + " ==========");
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
