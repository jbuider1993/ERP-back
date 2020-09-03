package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.vo.StatisticUserVo;
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
import java.time.LocalDate;
import java.util.*;

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
    public void addOnlineUser(HttpServletRequest request, String id, String userName, Date loginDate) throws Exception {
        // 获取登录IP地址、登录所在城市及经纬度坐标
        String ipAddr = AddressUtil.getIpAddr(request);
        String addr = AddressUtil.getRealAddressByIP(ipAddr);

        Map<String, String> addressMap = AddressUtil.getAddressInfoByAmap(addr);
        String browserInfo = CommonUtil.getRequestBrowserInfo(request);
        String systemInfo = CommonUtil.getRequestSystemInfo(request);

        // 处理在线用户
        OnlineUserModel model = new OnlineUserModel();
        model.setId(id);
        model.setLoginName(userName);
        model.setLoginIp(ipAddr);
        model.setLoginAddress(addr);
        model.setLocation(addressMap.get("address"));
        model.setUsedBrowser(browserInfo);
        model.setUsedWindow(systemInfo);
        model.setOnline(true);
        model.setLoginTime(loginDate);
        model.setLastTime(loginDate);
        onlineDao.addOnlineUser(model);
    }

    @Override
    public void updateOnlineUser(String id) throws Exception {
        String time = dateFormat.format(new Date());
        Date date = dateFormat.parse(time);

        OnlineUserModel model = new OnlineUserModel();
        model.setId(id);
        model.setOnline(false);
        model.setLastTime(date);
        onlineDao.updateOnlineUser(model);
    }

    @Override
    public List<OnlineUserModel> queryAllOnlineUser() throws Exception {
        return onlineDao.queryAllOnlineUser();
    }

    @Override
    public void updateOnlineStatus(List<String> onlineUserIds) throws Exception {
        onlineDao.updateOnlineStatus(onlineUserIds);
    }

    @Override
    public List<StatisticUserVo> statisticOnlineByYear(String year) throws Exception {
        String[] monthArrays = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        List<StatisticUserVo> statisticList = onlineDao.statisticOnlineByYear(year + "-01", year + "-12");
        List<StatisticUserVo> results = new ArrayList<>();
        for (String month : monthArrays) {
            Optional<StatisticUserVo> optional = statisticList.stream().filter(obj -> ("2020-" + month).equals(obj.getMonth())).findAny();
            StatisticUserVo statisticUserVo = null;
            if (!optional.isPresent()) {
                statisticUserVo = new StatisticUserVo();
                statisticUserVo.setMonth(month);
                statisticUserVo.setValue(0);
                results.add(statisticUserVo);
            } else {
                statisticUserVo = optional.get();
                statisticUserVo.setMonth(statisticUserVo.getMonth().substring(statisticUserVo.getMonth().indexOf("-") + 1));
                results.add(optional.get());
            }
        }
        return results;
    }
}
