package com.kunlun.gateway.config;

import com.kunlun.gateway.service.IBasedataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroRealm extends AuthorizingRealm {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IBasedataService cacheTraceService;

    /**
     * 用户授权，验证某个已认证的用户或其角色是否拥有某个操作权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("Start Config Principle!");
        SimpleAuthorizationInfo authorizationInfo = null;
        try {
            authorizationInfo = new SimpleAuthorizationInfo();
            log.info("Primary Principal: " + principals.getPrimaryPrincipal());
        } catch (Exception e) {
            log.error("ShiroRealm doGetAuthorizationInfo Error: ", e);
        }
        return authorizationInfo;
    }

    /**
     * 用户身份认证/登录，验证用户是不是拥有相应的身份，即是否在本系统中注册
     * 1、用户登陆之后，使用密码对账号进行签名生成并返回token并设置过期时间；
     * 2、将token保存到本地，并且每次发送请求时都在header上携带token。
     * 3、shiro过滤器拦截到请求并获取header中的token，并提交到自定义realm的doGetAuthenticationInfo方法。
     * 4、通过jwt解码获取token中的用户名，从数据库中查询到密码之后根据密码生成jwt效验器并对token进行验证。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        log.info("Start Authentication User Information!");
        try {
            // 获取用户、密码
            String userName = authToken.getPrincipal().toString();
            String password = new String((char[])authToken.getCredentials());
            Object obj = cacheTraceService.getUserByUserName(userName);
            Map map = (Map) ((LinkedHashMap)obj).get("data");
            if (ObjectUtils.isEmpty(map)) {
                throw new AuthenticationException("用户不存在！");
            } else {
                String userPassword = (String) map.get("password");
                if (!userPassword.equals(password)) {
                    throw new AuthenticationException("用户密码错误！");
                }

                // 返回认证
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
                return authenticationInfo;
            }
        } catch (Exception e) {
            log.error("ShiroRealm Authentication error: ", e);
        }
        return null;
    }
}
