FROM openjdk:17

COPY target/pixels-gateway-1.jar pixels-gateway.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","pixels-gateway.jar"]
