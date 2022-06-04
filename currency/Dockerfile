FROM openjdk:8-jdk-oraclelinux7
ADD build/libs/currency-0.0.1-SNAPSHOT.jar currency.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","currency.jar"]
