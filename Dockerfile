# 1. 베이스 이미지 선정 
FROM eclipse-temurin:17-jdk

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. Jar 파일을 컨테이너로 복사 
ARG JAR_FILE=target/hpm_spring.jar
COPY ${JAR_FILE} .

# 4. 환경 변수 개방 
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/hpmdb

# 5. 포트 노출
EXPOSE 8088

# 6. 컨테이너 실행될 때 JAR 실행
ENTRYPOINT ["java", "-jar", "/app/hpm_srping.jar"]