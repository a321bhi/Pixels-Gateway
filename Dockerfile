FROM openjdk:17

COPY target/pixels-mediaservice-1.jar pixels-mediaservice.jar

EXPOSE 8101

ENTRYPOINT ["java","-jar","pixels-mediaservice.jar"]
