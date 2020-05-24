package com.scmp.base.service.impl;

import com.scmp.base.dao.IOnlineDao;
import com.scmp.base.model.OnlineUserModel;
import com.scmp.base.service.IOnlineUserService;
import com.scmp.base.utils.AddressUtil;
import com.scmp.base.utils.CommonUtil;
import com.scmp.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class OnlineUserService implements IOnlineUserService {

    @Value("${online.ip}")
    private String ip;

    @Value("${online.address}")
    private String address;

    @Autowired
    private IOnlineDao onlineDao;

    @Override
    public Page getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(onlineUserModel, startIndex, pageSize);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(onlineDao.getOnlinesCount(queryMap));
        page.setRecords(onlineDao.getAllOnlineUser(queryMap));
        return page;
    }

    @Override
    public void addOnlineUser(HttpServletRequest request, String userName) throws Exception {
        // 获取登录IP地址、登录所在城市及经纬度坐标
        String ipAddr = AddressUtil.getIpAddr(request);
        if (!AddressUtil.verifyIP(ipAddr)) {
            ipAddr = ip;
        }

        // 校验IP及地址
        String addr = AddressUtil.getRealAddressByIP(ipAddr);
        if (!AddressUtil.verifyAddress(addr)) {
            addr = address;
        }
        Map<String, String> addressMap = AddressUtil.getAddressInfoByAmap(addr);

        // 处理在线用户
        OnlineUserModel model = new OnlineUserModel();
        model.setId(CommonUtil.generateUUID());
        model.setLoginName(userName);
        model.setLoginIp(ip);
        model.setLoginAddress(address);
        model.setLocation(addressMap.get("address"));
        model.setUsedBrowser(CommonUtil.getRequestBrowserInfo(request));
        model.setUsedWindow(CommonUtil.getRequestSystemInfo(request));
        model.setOnline(true);
        model.setLoginTime(new Date());
        model.setLastTime(new Date());
        onlineDao.addOnlineUser(model);
    }

    @Override
    public void updateOnlineUser(String userName) throws Exception {
        // 处理在线用户
        OnlineUserModel model = new OnlineUserModel();
        model.setLoginName(userName);
        model.setOnline(false);
        model.setLastTime(new Date());
        onlineDao.updateOnlineUser(model);
    }
}