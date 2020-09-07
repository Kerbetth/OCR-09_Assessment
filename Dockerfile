FROM openjdk:14
ADD target/mediscreen_assess-1.jar mediscreen_assess-1.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "mediscreen_assess-1.jar"]
