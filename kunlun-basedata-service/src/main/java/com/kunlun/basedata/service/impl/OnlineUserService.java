package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.utils.AddressUtil;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IOnlineDao;
import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.service.IOnlineUserService;
import com.kunlun.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class OnlineUserService implements IOnlineUserService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

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
    public OnlineUserModel addOnlineUser(HttpServletRequest request, String userName) throws Exception {
        // 获取登录IP地址、登录所在城市及经纬度坐标
        String ipAddr = AddressUtil.getIpAddr(request);
        String addr = AddressUtil.getRealAddressByIP(ipAddr);

        Map<String, String> addressMap = AddressUtil.getAddressInfoByAmap(addr);
        String browserInfo = CommonUtil.getRequestBrowserInfo(request);
        String systemInfo = CommonUtil.getRequestSystemInfo(request);

        // 处理在线用户
        OnlineUserModel model = new OnlineUserModel();
        model.setId(CommonUtil.generateUUID());
        model.setLoginName(userName);
        model.setLoginIp(ipAddr);
        model.setLoginAddress(addr);
        model.setLocation(addressMap.get("address"));
        model.setUsedBrowser(browserInfo);
        model.setUsedWindow(systemInfo);
        model.setOnline(true);
        model.setLoginTime(new Date());
        model.setLastTime(new Date());
        onlineDao.addOnlineUser(model);
        return model;
    }

    @Override
    public void updateOnlineUser(String userName, String loginTime) throws Exception {
        String time = dateFormat.format(new Date());
        Date date = dateFormat.parse(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        OnlineUserModel model = new OnlineUserModel();
        model.setLoginName(userName);
        model.setOnline(false);
        model.setLastTime(date);
        model.setLoginTime(format.parse(loginTime));
        onlineDao.updateOnlineUser(model);
    }
}
