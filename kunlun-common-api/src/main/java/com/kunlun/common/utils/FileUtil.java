package com.kunlun.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 文件操作工具类
 */
public class FileUtil {

    private static Logger logger = LogManager.getLogger();

    /**
     * 以字符串输出文件内容
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        String result = "";
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[10240];
            int flag = 0;
            while ((flag = bufferedInputStream.read(buffer)) != -1) {
                result += new String(buffer, 0, flag);
            }
            bufferedInputStream.close();
        } catch (Exception e) {
            logger.error("FileUtil readFile Error: ", e);
        }
        return result;
    }
}
