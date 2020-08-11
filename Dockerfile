# 暂时使用java:8镜像，解决openjdk:8-jdk-alpine镜像缺少字体的问题
# FROM openjdk:8-jdk-alpine
FROM java:8

# time zone
RUN echo "Asia/Chongqing" > /etc/timezone

# 此配置一般用于文件存储时的临时路径配置
# VOLUME /tmp

ARG JAR_FILE
ADD ${JAR_FILE} app.jar

ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms80m", "-Xmx120m", "-jar", "app.jar"]