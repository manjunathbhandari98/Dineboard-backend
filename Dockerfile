# Stage 1: Build the application using Maven
FROM maven:3.8.6-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source
COPY . .

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/Dineboard-0.0.1-SNAPSHOT.jar .

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/Dineboard-0.0.1-SNAPSHOT.jar"]
