FROM openjdk:15
ADD build/libs/characterapi-0.0.1-SNAPSHOT.jar characterapi.jar
ENTRYPOINT ["java", "-jar", "characterapi.jar"]