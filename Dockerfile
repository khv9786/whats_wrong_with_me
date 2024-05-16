# openjdk:17-slim을 기반 이미지로 사용하는 빌드 스테이지
FROM openjdk:17-slim as build

# 작업 디렉토리를 /app으로 설정
WORKDIR /app

# 현재 디렉토리의 모든 파일을 컨테이너의 /app에 복사
COPY . .

# /root/.gradle 디렉토리 생성 및 프록시 설정 파일 작성
RUN mkdir -p /root/.gradle && \
    echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

# 빌드 스테이지에서 생성된 JAR 파일을 가져옴
ARG JAR_FILE=build/libs/webIde-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} webIde.jar

# Java 애플리케이션 실행을 위한 ENTRYPOINT 설정
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/webIde.jar"]

# 애플리케이션 접근을 위한 포트 8080 개방
EXPOSE 8080

 CMD ["아 드디어 됐나요?"]