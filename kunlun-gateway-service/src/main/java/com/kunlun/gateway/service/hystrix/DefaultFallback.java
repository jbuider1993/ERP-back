//package com.kunlun.gateway.service.hystrix;
//
//import com.kunlun.gateway.service.IBasedataService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DefaultFallback implements IBasedataService {
//
//    private Logger log = LogManager.getLogger();
//
//    @Override
//    public Object set(String key, String value, int expire, int dataBase) {
//        log.info("IBasedataService interface open Hystrix");
//        log.info("key ===>>> " + key + ", value ===>>> " + value + ", expire ===>>> " + expire + ", dataBase ===>>> " + dataBase);
//        return null;
//    }
//
//    @Override
//    public Object get(String key, int dataBase) {
//        log.info("IBasedataService interface open Hystrix");
//        log.info("key ===>>> " + key + ", dataBase ===>>> " + dataBase);
//        return null;
//    }
//
//    @Override
//    public Object getUserByUserName(String userName) {
//        log.info("IBasedataService interface open Hystrix");
//        log.info("userName ===>>> " + userName);
//        return null;
//    }
//
//    @Override
//    public Object addOnlineUser(String userName) {
//        log.info("IBasedataService interface open Hystrix");
//        log.info("userName ===>>> " + userName);
//        return null;
//    }
//
//    @Override
//    public Object updateOnlineUser(String userName) {
//        log.info("IBasedataService interface open Hystrix");
//        log.info("userName ===>>> " + userName);
//        return null;
//    }
//}
