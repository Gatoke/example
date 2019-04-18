FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY build/libs/*.jar example.jar
ENTRYPOINT ["java","-jar","/example.jar"]
CMD java - jar example.jar
