package com.scmp.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private Logger log;

    public LogUtils(Class<?> clz) {
        this.log = LoggerFactory.getLogger(clz.getName());
    }

    public void info(String message) {
        log.info(message);
    }

    public void error(String message, Throwable e) {
        log.error(message, e);
    }
}
