package com.kunlun.system.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictionaryController {

    private Logger log = LogManager.getLogger();

    @RequestMapping(value = "/getAllDict", method = RequestMethod.GET)
    public Object getAllDict() {
        return null;
    }
}
