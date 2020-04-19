package com.kunlun.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

/**
 * RestControllerAdviceI自动配置类
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "kunlun.controllerAdvice.enable", havingValue = "true")
public class ControllerAdviceAutoConfiguration implements ResponseBodyAdvice {
    private Logger log = LogManager.getLogger();

    private static final String EXCEPTION_PATTERN = "抱歉，异常了：{0}";

    private static final String REQUEST_PATTERN = "\n---BEGIN---\nReqURI: {0}\nParams: {1}\nMethod: {2}\nReturn: {3}\n---END---";

    @ExceptionHandler(value = {Exception.class})
    public String handleAllException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.info("接口调用异常", e);
        if (e instanceof NoHandlerFoundException) {
            String reqURI = request.getRequestURI().toString();
            return MessageFormat.format(EXCEPTION_PATTERN, "资源" + reqURI + "不存在！！！");
        }
        return MessageFormat.format(EXCEPTION_PATTERN, e.getMessage());
    }

    @Override
    public Object beforeBodyWrite(Object rtnMsg, MethodParameter param, MediaType mediaType, Class aClass, ServerHttpRequest req, ServerHttpResponse serverHttpResponse) {
        String clazName = param.getDeclaringClass().getName();
        String mtdName = param.getMethod().getName();
        String reqURI = req.getURI().toString();
        String reqParams = getReqParams(req);

        String logMsg = getLog(reqURI, reqParams, clazName + "." + mtdName, rtnMsg.toString());
        log.info(logMsg);
        return rtnMsg;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    private String getLog(String reqURI, String reqParams, String proMethod, String rtnMsg) {
        return MessageFormat.format(REQUEST_PATTERN, reqURI, reqParams, proMethod, rtnMsg);
    }

    private String getReqParams(ServerHttpRequest req) {
        StringBuilder body = new StringBuilder();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(req.getBody(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        } catch (IOException e) {
            log.error("I/O error ", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("I/O error ", e);
            }
        }
        return body.toString();
    }
}
