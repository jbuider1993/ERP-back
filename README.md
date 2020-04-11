# 昆仑管理系统

<p align="center"> 
    <img src="https://img.shields.io/circleci/project/vuejs/vue/dev.svg" alt="Build Status">
    <img src="https://img.shields.io/badge/Spring%20Cloud-Greenwich.RELEASE-blue.svg" alt="Coverage Status">
    <img src="https://img.shields.io/badge/Spring%20Boot-2.1.0.RELEASE-blue.svg" alt="Downloads">
</p>

#### 系统介绍
昆仑管理系统基于前后端分离架构，通过Apache Shiro与Jwt组件，用token进行数据交互认证，可快速开发并独立进行Docker容器化部署。

kunlun-web 基于 umi(乌米) 与蚂蚁金服 Ant Design 构建开发，提供前端解决方案，其中kunlun-home-web为前端框架，主要负责登录页面、业务菜单、消息待办、主题皮肤等信息的展示；kunlun-system-web为前端业务服务，主要用于展示各个菜单的业务功能页面，如首页信息、人员管理、用户地图、流程管理、操作日志、事项日程、服务资源管理、菜单管理等。

kunlun-service 基于 SpringBoot 与 Spring Cloud 构建开发，提供后端基于微服务架构的解决方案，其中kunlun-common-api为公共模块，提供公共基础模型、工具、自动配置等，kunlun-register-service为服务治理模块，提供服务注册、服务发现、服务心跳检测及高级消息队列等，kunlun-gateway-service为服务网关模块，提供服务路由及分布式服务配置中心等，kunlun-basedata-service为基础数据模块，提供基础数据支持，如菜单、角色、权限等，并提供分布式缓存功能等，kunlun-system-service为业务服务模块，提供业务功能支持服务。


#### 软件架构
<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/165451_44bb82b5_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/165543_a617076b_1894302.png"/></td>
    </tr>
</table>


#### 使用说明

1.  npm安装前端依赖库，并启动kunlun-home-web与kunlun-system-web服务；
2.  启动PostgreSQL，执行kunlun-basedata-service与kunlun-system-service服务resources下的Sql文件；
3.  启动RabbitMQ和Redis；
4.  启动注册中心kunlun-register-service；
5.  依次启动kunlun-gateway-service、kunlun-basedata-service与kunlun-system-service服务；
6.  访问URL：http://localhost:8000；
7.  输入账号：admin，密码：admin及验证码。


#### 功能说明

1.  统一安全认证中心，支持用户名、密码加图形验证码登录
2.  微服务架构基础支撑，支持服务注册发现、路由与负载均衡，服务熔断与限流，统一配置中心
3.  系统服务监控中心，支持服务调用链监控，微服务监控
4.  系统业务支撑，支持统一跨域处理，统一异常处理，统一处理操作日志，服务内部Swagger接口文档
5.  基于docker容器化部署


#### 工具插件

<table>
    <tr>
        <td>后端框架</td>
        <td>前端框架</td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/152235_4f7146fd_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/152350_b3f7e035_1894302.png"/></td>
    </tr>
</table>


#### 页面截图

<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/150813_6b96784d_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151157_074c7bd6_1894302.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151338_d5682439_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151432_50f2e90b_1894302.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151503_a77038fb_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151532_e7c8d194_1894302.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/151708_13470811_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/154927_42427ebc_1894302.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/155238_ee1f9e60_1894302.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2020/0411/155119_1fdc1d25_1894302.png"/></td>
    </tr>
</table>
