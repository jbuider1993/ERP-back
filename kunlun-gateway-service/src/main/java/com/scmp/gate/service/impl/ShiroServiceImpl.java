package com.scmp.gate.service.impl;

import com.scmp.common.annotation.OperatorLogger;
import com.scmp.common.model.enums.OperatorLogType;
import com.scmp.gate.model.ShiroConfigModel;
import com.scmp.gate.model.TokenModel;
import com.scmp.gate.model.UserModel;
import com.scmp.gate.service.ICacheTraceService;
import com.scmp.gate.service.IShiroService;
import com.scmp.gate.utils.CommonUtil;
import com.scmp.gate.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

@Service
@Transactional
public class ShiroServiceImpl implements IShiroService {

    private static final String logId = CommonUtil.generateUUID();

    @Autowired
    private ShiroConfigModel shiroConfig;

    @Autowired
    private ICacheTraceService cacheTraceService;

    @OperatorLogger(type = OperatorLogType.LOGIN, description = "用户登录系统")
    @Override
    public TokenModel handleLogin(String userName, String password) throws Exception {
        // 处理在线用户
        cacheTraceService.addOnlineUser(userName);

        // 登录用户基本信息
        Object userObj = cacheTraceService.getUserByUserName(userName);
        Object obj = ((LinkedHashMap)userObj).get("data");
        UserModel userModel = new UserModel();
        userModel.setId(((LinkedHashMap)obj).get("id").toString());
        userModel.setUserName(((LinkedHashMap)obj).get("userName").toString());
        userModel.setPassword(((LinkedHashMap)obj).get("password").toString());
        userModel.setPhoneNumber(((LinkedHashMap)obj).get("phoneNumber").toString());
        userModel.setEmail(((LinkedHashMap)obj).get("email").toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userModel.setCreateTime(dateFormat.parse(((LinkedHashMap)obj).get("createTime").toString().replace("T", " ")));
        userModel.setModifiedTime(dateFormat.parse(((LinkedHashMap)obj).get("modifiedTime").toString().replace("T", " ")));

        // 生成Token
        String shiroToken = JwtTokenUtil.sign(userName, password, shiroConfig.getSecret(), shiroConfig.getExpireTime());
        TokenModel tokenModel = new TokenModel(shiroToken, userModel);

        // 缓存token到redis
        cacheTraceService.set(shiroToken, shiroToken, shiroConfig.getExpireTime(), 1);
        return tokenModel;
    }

    @OperatorLogger(type = OperatorLogType.LOGIN, description = "用户注销登录")
    @Override
    public void handleLogout(HttpServletRequest request, String userName) throws Exception {
        // 处理在线用户
        cacheTraceService.updateOnlineUser(userName);

        // 注销成功删除缓存在redis的Token
        String token = request.getHeader(shiroConfig.getTokenHeader());
        cacheTraceService.set(token, null, 0, 1);
    }
}
