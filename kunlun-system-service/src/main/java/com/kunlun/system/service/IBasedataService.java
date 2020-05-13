package com.kunlun.system.service;

import io.swagger.annotations.Scope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "kunlun-basedata-service", fallback = DefaultFallback.class)
@FeignClient(name = "kunlun-basedata-service")
public interface IBasedataService {
    @RequestMapping(value = "/redis/set", method = RequestMethod.POST)
    public Object set(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "expire") int expire, @RequestParam(value = "dataBase") int dataBase);

    @RequestMapping(value = "/redis/get", method = RequestMethod.GET)
    public Object get(@RequestParam(value = "key") String key, @RequestParam(value = "dataBase") int dataBase);

    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
    public Object getUserByUserName(@RequestParam(value = "userName") String userName);

    @RequestMapping(value = "/onlineUser/addOnlineUser", method = RequestMethod.POST)
    public Object addOnlineUser(@RequestParam(value = "userName") String userName);

    @RequestMapping(value = "/onlineUser/updateOnlineUser", method = RequestMethod.POST)
    public Object updateOnlineUser(@RequestParam(value = "userName") String userName);
}
