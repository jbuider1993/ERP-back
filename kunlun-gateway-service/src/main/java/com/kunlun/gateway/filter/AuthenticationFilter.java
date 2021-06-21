package com.kunlun.gateway.filter;

import com.kunlun.common.model.ClientToken;
import com.kunlun.common.model.SignTokenModel;
import com.kunlun.common.utils.JwtTokenUtil;
import com.kunlun.gateway.config.BeanUtil;
import com.kunlun.gateway.model.ShiroConfigModel;
import com.kunlun.gateway.service.IBasedataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * shiro过滤器，认证token
 */
public class AuthenticationFilter extends BasicHttpAuthenticationFilter {

    private Logger log = LogManager.getLogger();

    /**
     * 执行登录认证
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("Start ShiroFilter isAccessAllowed");
        try {
            // ignoreUrls的请求无须Filter处理
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            ShiroConfigModel shiroConfigModel = BeanUtil.getBean(ShiroConfigModel.class);
            String requestUrl = httpServletRequest.getRequestURI();
            if (shiroConfigModel.getIgnoreUrls().contains(requestUrl)) {
                return true;
            }
            return executeLogin(request, response);
        } catch (Exception e) {
            log.error("ShiroFilter isAccessAllowed Error: ", e);
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("Start ShiroFilter executeLogin");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ShiroConfigModel shiroConfigModel = BeanUtil.getBean(ShiroConfigModel.class);
        IBasedataService basedataService = BeanUtil.getBean(IBasedataService.class);

        // Token校验
        String token = httpServletRequest.getHeader(shiroConfigModel.getTokenHeader());
        ClientToken clientToken = JwtTokenUtil.getClientToken(token);
        String redisKey = clientToken.getUserName() + "_" + clientToken.getLoginTime();
        Map<String, Object> map = (Map<String, Object>) basedataService.get(redisKey, 1);
        String refreshedToken = (String) map.get("data");
        boolean jwt = JwtTokenUtil.verify(refreshedToken, clientToken.getUserName(), clientToken.getPassword(), shiroConfigModel.getSecret());
        if (jwt) {
            // 用户在线操作，Token续期
            // 此处不能刷新即续期token，否则新的token无法传递到前台，无法保持全局一致；此处不能校验token是否有效，因刷新或续期token无法处理
            log.info("token ===>>> " + token);
            SignTokenModel signToken = new SignTokenModel(clientToken, shiroConfigModel.getSecret(), shiroConfigModel.getExpireTime());
            String shiroToken = JwtTokenUtil.sign(signToken);
            log.info("update token ===>>> " + shiroToken);
            basedataService.set(redisKey, shiroToken, shiroConfigModel.getExpireTime(), 1);
        } else {
            // Token过期后，前台提示用户，并阻止向下游服务继续调用
//            httpServletResponse.sendRedirect("/timeout");
            httpServletResponse.setStatus(888);
            httpServletResponse.sendError(999);
            log.info("离开时间太长，请重新登录！");
        }
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("Start ShiroFilter preHandle, config Header!");

        // 请求跨域设置
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        // 跨域时会首先发送一个option请求，给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
