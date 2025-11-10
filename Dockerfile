# ==========================================
# 🏗️ Stage 1: Build the Spring Boot JAR
# ==========================================
FROM eclipse-temurin:25-jdk AS build

# Install Maven manually (since no Maven+Java25 image exists yet)
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of your source code
COPY . .

# Build the Spring Boot JAR (skip tests for faster build)
RUN mvn clean package -DskipTests


# ==========================================
# 🚀 Stage 2: Run the app with minimal runtime
# ==========================================
FROM eclipse-temurin:25-jre-slim

# Set work directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
