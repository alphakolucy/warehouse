FROM openjdk:8-jdk-alpine
# time zone
RUN echo "Asia/Chongqing" > /etc/timezone 
VOLUME /tmp
ADD warehouse-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Xms80m", "-Xmx512m","-jar","app.jar"]
