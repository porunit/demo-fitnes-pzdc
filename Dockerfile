# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /home/app
COPY --chown=gradle:gradle . .
ENV TZ="Europe/Moscow"
RUN date
RUN mvn clean package -DskipTests

# ---- Production Stage ----
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /home/app/target/*.jar app.jar
EXPOSE 8080

# ---- Install yandex certicates ----
RUN apt-get update -y && apt-get install wget -y
RUN mkdir -p /.mongodb && \
wget "https://storage.yandexcloud.net/cloud-certs/CA.pem" \
     --output-document /.mongodb/root.crt && \
chmod 0644 /.mongodb/root.crt

ENV TZ="Europe/Moscow"
RUN date
ENTRYPOINT ["java", "-jar", "app.jar"]