# 该镜像需要依赖的基础镜像
FROM java:8

# 将当前目录下的jar包复制到docker容器的/目录下
ADD kunlun-register-service-1.0.0-SNAPSHOT.jar kunlun-register-service-1.0.0.jar

# 运行过程中创建一个kunlun-register-service-1.0.0.jar文件
RUN sh -c 'touch /kunlun-register-service-1.0.0.jar'

# 声明服务运行在8080端口
EXPOSE 8085

# 指定docker容器启动时运行jar包
ENTRYPOINT [ "java", "-jar", "/kunlun-register-service-1.0.0.jar" ]