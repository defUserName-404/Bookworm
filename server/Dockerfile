# Stage 1: Build with Maven
FROM maven:3.8.4 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the POM file to cache dependencies
COPY pom.xml .

# Download dependencies and plugins specified in the POM file
RUN mvn dependency:go-offline

# Copy the entire project to the container
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Stage 2: Run with OpenJDK
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy only the necessary artifacts from the builder stage
COPY --from=builder /app/target/bookworm-1.0.0-SNAPSHOT.jar .

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "bookworm-1.0.0-SNAPSHOT.jar"]
