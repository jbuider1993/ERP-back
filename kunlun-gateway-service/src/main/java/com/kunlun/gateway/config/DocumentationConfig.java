package com.kunlun.gateway.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    private Logger log = LogManager.getLogger();

    @Autowired
    private DiscoveryClientRouteLocator discoveryClientRouteLocator;

    @Override
    public List<SwaggerResource> get() {
        log.info("===== Swagger2 Documentation Config =====");
        List<SwaggerResource> resources = new ArrayList<>();

        resources.add(createResource("基础数据服务", "/kunlun-basedata-service/basedata/v2/api-docs", "2.0"));
        resources.add(createResource("系统业务服务", "/kunlun-system-service/system/v2/api-docs", "2.0"));

//        for (Route route : discoveryClientRouteLocator.getRoutes()) {
//            resources.add(createResource(route.getLocation(),route.getId(), "2.0"));
//        }

        return resources;
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
