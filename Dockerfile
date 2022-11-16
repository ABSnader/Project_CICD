FROM openjdk:11
EXPOSE 8089
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} achat.jar
ENTRYPOINT ["java","-jar","/achat.jar"]
