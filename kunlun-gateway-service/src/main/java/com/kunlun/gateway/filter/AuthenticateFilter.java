package com.kunlun.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.kunlun.gateway.model.ShiroConfigModel;
import com.kunlun.gateway.service.IBasedataService;
import com.kunlun.gateway.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AuthenticateFilter extends ZuulFilter {
    private Logger log = LogManager.getLogger();

    @Autowired
    private ShiroConfigModel shiroConfig;

    @Autowired
    private IBasedataService basedataService;

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
        log.info("Start AuthenticateFilter run!");

        try {
            // 获取请求的上下文类
            RequestContext context = RequestContext.getCurrentContext();

            // 避免中文乱码
            context.addZuulResponseHeader("Content-type", "text/json;charset=UTF-8");
            context.getResponse().setCharacterEncoding("UTF-8");

            // 打印请求日志
            HttpServletRequest request = context.getRequest();
            log.info(String.format("Request protocol: %s, source: %s, gateway: %s, method：%s, address：%s", request.getProtocol(), request.getHeader("origin").split("//")[1], request.getHeader("host"), request.getMethod(), request.getRequestURI()));

            // Token校验
            String token = request.getHeader(shiroConfig.getTokenHeader());
            String userName = JwtTokenUtil.getTokenInfo(token, "userName");
            String password = JwtTokenUtil.getTokenInfo(token, "password");
            String loginTime = JwtTokenUtil.getTokenInfo(token, "loginTime");
            String redisKey = userName + "_" + loginTime;
            Map<String, Object> map = (Map<String, Object>) basedataService.get(redisKey, 1);
            String refreshedToken = (String) map.get("data");
            boolean jwt = JwtTokenUtil.verify(refreshedToken, userName, password, shiroConfig.getSecret());
            if (jwt) {
                // 用户在线操作，Token续期
                // 此处不能刷新即续期token，否则新的token无法传递到前台，无法保持全局一致；此处不能校验token是否有效，因刷新或续期token无法处理
                log.info("token ===>>> " + token);
                String shiroToken = JwtTokenUtil.sign(userName, password, loginTime, shiroConfig.getSecret(), shiroConfig.getExpireTime());
                log.info("update token ===>>> " + shiroToken);
                System.out.println(String.format("===== AuthenticateFilter redisKey %s =====", redisKey));
                basedataService.set(redisKey, shiroToken, shiroConfig.getExpireTime(), 1);
            } else {
                // Token过期后，前台提示用户，并阻止向下游服务继续调用
                HttpServletResponse response = context.getResponse();
                response.sendRedirect("/timeout");
                context.setSendZuulResponse(false);
                log.info("离开时间太长，请重新登录！");
            }
        } catch (Exception e) {
            log.error("AuthenticateFilter run Error: ", e);
        }
        return null;
    }
}
