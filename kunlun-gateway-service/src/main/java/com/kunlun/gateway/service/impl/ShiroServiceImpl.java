package com.kunlun.gateway.service.impl;

import com.kunlun.common.annotation.OperatorLogger;
import com.kunlun.common.model.enums.OperatorLogType;
import com.kunlun.gateway.model.ShiroConfigModel;
import com.kunlun.gateway.model.TokenModel;
import com.kunlun.gateway.utils.JwtTokenUtil;
import com.kunlun.gateway.model.UserModel;
import com.kunlun.gateway.service.IBasedataService;
import com.kunlun.gateway.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@Service
@Transactional
public class ShiroServiceImpl implements IShiroService {

    private SimpleDateFormat dateCSTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ShiroConfigModel shiroConfig;

    @Autowired
    private IBasedataService basedataService;

    @OperatorLogger(type = OperatorLogType.LOGIN, description = "用户登录系统")
    @Override
    public TokenModel handleLogin(String userName, String password) throws Exception {
        // 处理在线用户
        Object onlineUserObj = basedataService.addOnlineUser(userName);

        // 登录用户基本信息
        Object userObj = basedataService.getUserByUserName(userName);
        Object obj = ((LinkedHashMap)userObj).get("data");
        UserModel userModel = new UserModel();
        userModel.setId(((LinkedHashMap)obj).get("id").toString());
        userModel.setUserName(((LinkedHashMap)obj).get("userName").toString());
        userModel.setPassword(((LinkedHashMap)obj).get("password").toString());
        userModel.setPhoneNumber(((LinkedHashMap)obj).get("phoneNumber").toString());
        userModel.setEmail(((LinkedHashMap)obj).get("email").toString());
        userModel.setCreateTime(dateFormat.parse(((LinkedHashMap)obj).get("createTime").toString()));
        userModel.setModifiedTime(dateFormat.parse(((LinkedHashMap)obj).get("modifiedTime").toString()));

        // 生成Token
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Object onlineObj = ((LinkedHashMap)onlineUserObj).get("data");
        Date loginTime = dateCSTFormat.parse(((LinkedHashMap)onlineObj).get("loginTime").toString());
        String loginTimeKey = format.format(loginTime);
        String shiroToken = JwtTokenUtil.sign(userName, password, loginTimeKey, shiroConfig.getSecret(), shiroConfig.getExpireTime());
        TokenModel tokenModel = new TokenModel(shiroToken, userModel);

        // 缓存token到redis
        String redisKey = userName + "_" + loginTimeKey;
        basedataService.set(redisKey, shiroToken, shiroConfig.getExpireTime(), 1);
        return tokenModel;
    }

    @OperatorLogger(type = OperatorLogType.LOGIN, description = "用户注销登录")
    @Override
    public void handleLogout(HttpServletRequest request, String userName) throws Exception {
        String token = request.getHeader(shiroConfig.getTokenHeader());
        String loginTime = JwtTokenUtil.getTokenInfo(token, "loginTime");

        // 处理在线用户
        basedataService.updateOnlineUser(userName, loginTime);

        // 注销成功删除缓存在redis的Token
        String redisKey = userName + "_" + loginTime;
        basedataService.del(redisKey, 1);
    }
}
