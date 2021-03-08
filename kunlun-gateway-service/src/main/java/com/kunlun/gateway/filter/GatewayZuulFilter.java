package com.kunlun.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.kunlun.gateway.model.ShiroConfigModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器
 */
public class GatewayZuulFilter extends ZuulFilter {

    private Logger log = LogManager.getLogger();

    @Autowired
    private ShiroConfigModel shiroConfig;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (shiroConfig.getIgnoreUrls().contains(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("Start GateWayFilter run!");
        try {
            // 获取请求的上下文类
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();

            // 避免中文乱码
            context.addZuulResponseHeader("Content-type", "text/json;charset=UTF-8");
            context.getResponse().setCharacterEncoding("UTF-8");
            log.info(String.format("Request protocol: %s, source: %s, gateway: %s, method：%s, address：%s", request.getProtocol(), request.getHeader("origin").split("//")[1], request.getHeader("host"), request.getMethod(), request.getRequestURI()));
        } catch (Exception e) {
            log.error("GateWayFilter run Error: ", e);
        }
        return null;
    }
}
