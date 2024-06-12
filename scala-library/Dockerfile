FROM openjdk:17
VOLUME /tmp
COPY lib/build/libs/scala-library-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]