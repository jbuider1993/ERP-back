# 该镜像需要依赖的基础镜像
FROM java:8

# 将当前目录下的jar包复制到docker容器的/目录下
ADD kunlun-gateway-service-1.0.1-SNAPSHOT.jar kunlun-gateway-service-1.0.1.jar

# 运行过程中创建一个kunlun-gateway-service.jar文件
RUN sh -c 'touch /kunlun-gateway-service-1.0.1.jar'

# 声明服务运行端口
EXPOSE 8015

# 指定docker容器启动时运行jar包
ENTRYPOINT [ "java", "-jar", "/kunlun-gateway-service-1.0.1.jar" ]