# kunlun-register-service

昆仑管理系统——服务治理、配置中心及AMQP中心

# 启动JVM参数
-Xms50m -Xmx50m -Xss512k -XX:+UseConcMarkSweepGC

#### 启动JVM参数
-Xmx32M -Xms32M -XX:+PrintGCDetails -verbose:gc -Xloggc:logs/gc.log -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCApplicationStoppedTime