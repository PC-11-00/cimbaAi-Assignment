# Use the official OpenJDK image as base
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . .

# Run the Gradle build without using the daemon
RUN ./gradlew --no-daemon build

# Set the entry point to run the Scala application
ENTRYPOINT ["java", "-cp", "lib/build/libs/scala-library-1.0-SNAPSHOT.jar"]
