FROM openjdk:11
EXPOSE 8082
ADD target/tpmagasin.jar tpmagasin.jar
ENTRYPOINT ["java","-jar","/tpmagasin.jar"]
