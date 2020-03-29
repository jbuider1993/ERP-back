package com.scmp.base.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;

import javax.servlet.annotation.WebServlet;

/**
 * 配置Druid的sql监控servlet
 *
 * 启动后直接访问：http://localhost:8080/druid
 */
@WebServlet(name = "monitorServlet", urlPatterns = {"/druid/*"})
public class MonitorDruidServlet extends DruidStatProperties.StatViewServlet {
    private static final long serialVersionUID = -2211104135110049275L;
}
