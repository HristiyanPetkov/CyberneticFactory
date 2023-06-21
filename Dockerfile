FROM openjdk:17

EXPOSE 8080
VOLUME /tmp
COPY target/CyberneticFactory-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#docker build -t factoryapi .
#docker tag factoryapi:latest sylse/dockerhub:factoryapi
#docker push sylse/dockerhub:factoryapi
