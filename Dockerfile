FROM openjdk:latest
EXPOSE 8099
ADD /target/tuto-gateway-1.0.0.jar /tuto-gateway.jar
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75.0","-XX:+CrashOnOutOfMemoryError","-jar","/tuto-gateway.jar"]
