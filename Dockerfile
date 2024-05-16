# openjdk:17-slim을 기반 이미지로 사용하는 빌드 스테이지
FROM openjdk:17

# 작업 디렉토리를 /app으로 설정
WORKDIR /app

# 현재 디렉토리의 모든 파일을 컨테이너의 /app에 복사
COPY . .

# /root/.gradle 디렉토리 생성 및 프록시 설정 파일 작성
RUN mkdir -p /root/.gradle
RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties
RUN chmod +x gradlew
RUN ./gradlew clean build

# 애플리케이션 접근을 위한 포트 8080 개방
EXPOSE 8080
 CMD ["아 드디어 됐나요?"]