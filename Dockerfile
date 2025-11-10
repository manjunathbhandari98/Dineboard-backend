# Use Maven with Java 21 for building
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Use smaller JRE 21 image for runtime
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render uses PORT environment variable)
EXPOSE 8080

# Run the application
# Use PORT environment variable that Render provides
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.port=${PORT:-8080} app.jar"]