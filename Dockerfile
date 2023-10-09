FROM openjdk:17
VOLUME /tmp
EXPOSE 5000
ARG JAR_FILE=target/cicd-products-api-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]