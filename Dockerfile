FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/webIde-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

## 공식 MySQL 도커 이미지 사용
#FROM mysql:latest
#
## MySQL 루트 비밀번호 설정 (원하는 비밀번호로 대체)
#ENV MYSQL_ROOT_PASSWORD: "!db1234"
#
## 비루트 사용자 및 비밀번호 설정 (원하는 사용자명과 비밀번호로 대체)
#ENV MYSQL_USER: user
#ENV MYSQL_PASSWORD:
#
## (선택사항) 새로운 데이터베이스 생성 (원하는 데이터베이스 이름으로 대체)
#ENV MYSQL_DATABASE=store_database
#
## MySQL 포트 노출
#EXPOSE 3306

#FROM openjdk:17-slim as build
#
#WORKDIR /app
#
#COPY . .
#
#
#
#RUN mkdir -p /root/.gradle
#RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties
#RUN chmod +x gradlew
#
## List output to verify
#RUN ls /app/build/libs/
#
#FROM openjdk:17-slim
#ARG JAR_FILE=build/libs/webIde-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} webIde.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/webIde.jar"]
#EXPOSE 8080/tcp