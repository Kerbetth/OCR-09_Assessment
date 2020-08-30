FROM openjdk:14
ADD build/libs/medi-assessment.jar medi-assessment.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "medi-assessment.jar"]
