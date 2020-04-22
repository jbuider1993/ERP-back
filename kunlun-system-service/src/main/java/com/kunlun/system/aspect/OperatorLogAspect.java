package com.kunlun.system.aspect;

import com.kunlun.common.annotation.OperatorLogger;
import com.kunlun.common.model.OperatorLogModel;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.service.IOperateLogService;
import com.kunlun.system.utils.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志记录AOP类
 */
@Component
@Aspect
public class OperatorLogAspect {

    /**
     * 操作日志业务Id
     */
    private String logId = "";

    private Logger log = LogManager.getLogger();

    @Autowired
    private IOperateLogService operateLogService;

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int port;

    @Pointcut("@annotation(com.kunlun.common.annotation.OperatorLogger)")
    public void pointCut() {}

    @Before("pointCut()")
//    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        // 获取http请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 请求信息
        StringBuffer requestUrl = request.getRequestURL();
        String style = request.getMethod();
        String protocal = request.getProtocol();
        String params = request.getQueryString();
        String ip = CommonUtil.getIpAddr(request);

        // 获取执行的类及方法
        String clzType = joinPoint.getTarget().getClass().getName();
        Class<?> clz = Class.forName(clzType);
        String clzName = clz.getName();
        String methodName = joinPoint.getSignature().getName();

        // 获取注解及其内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperatorLogger annotation = method.getAnnotation(OperatorLogger.class);
        int operatorType = annotation.type().getKey();
        String description = annotation.description();

        // 打印日志
        log.info("requestUrl ===>>> " + requestUrl + "， style ===>>> " + style + "， protocal ===>>> " + protocal + "， params ===>>> " + params + "， ip ===>>> " + ip);
        log.info("clzName ===>>> " + clzName + "， methodName ===>>> " + methodName + "， operatorType ===>>> " + operatorType + "， description ===>>> " + description);

        // 生成操作日志业务Id，保证每个OperatorLogger注解的logId唯一，即一个OperatorLogger对应一个logId
        logId = CommonUtil.generateUUID();

        // 创建并保存操作日志模型
        OperatorLogModel logModel = new OperatorLogModel();
        logModel.setId(logId);
        logModel.setIp(ip);
        logModel.setUserName("");
        logModel.setOperatorType(operatorType);
        logModel.setOperateDescription(description);
        logModel.setRequestUrl(requestUrl.toString());
        logModel.setProtocal(protocal);
        logModel.setParams(params);
        logModel.setClzName(clzName);
        logModel.setMethodName(methodName);
        logModel.setOperateTime(new Date());
        logModel.setStyle(style);
        logModel.setServiceName(serviceName);
        logModel.setPort(port);
        logModel.setThreadName(Thread.currentThread().getName());
        logModel.setStatus("正常");

        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());
        operateLogService.insertOperateLog(logModel);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) throws Throwable {
        // 获取执行的类及方法
        String clzType = joinPoint.getTarget().getClass().getName();
        Class<?> clz = Class.forName(clzType);
        String clzName = clz.getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("clzName ===>>> " + clzName + ", methodName ===>>> " + methodName + ", exception ===>>> " + e.getMessage());

        // 创建并保存操作日志模型
        OperatorLogModel logModel = new OperatorLogModel();
        logModel.setId(logId);
        logModel.setExceptionInfo(e.getMessage());
        logModel.setStatus("异常");

        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());
        operateLogService.updateOperateLog(logModel);
    }
}
