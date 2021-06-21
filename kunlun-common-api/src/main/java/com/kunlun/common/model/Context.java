package com.kunlun.common.model;

/**
 * 上下文缓存
 */
public class Context {

    /**
     * 当前登录账号
     */
    private static ThreadLocal<CurrentAccount> threadLocal = new ThreadLocal<>();

    public static CurrentAccount getCurrentAccount() {
        return threadLocal.get();
    }

    public static void setCurrentAccount(CurrentAccount currentAccount) {
        threadLocal.set(currentAccount);
    }
}
