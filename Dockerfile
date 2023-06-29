# Use a base image with JDK 17
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file to the container
COPY target/Concesionaria-0.0.1-SNAPSHOT.jar app.jar

# Copy the .env file to the container
COPY .env .env

# Expose the port on which your Spring Boot application listens
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
