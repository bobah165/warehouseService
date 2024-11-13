FROM openjdk:17.0.1-jdk-slim
COPY build/libs/warehouseService-1.0-SNAPSHOT.jar /app/warehouseService.jar
EXPOSE 8001
CMD ["java", "-jar", "/app/warehouseService.jar"]