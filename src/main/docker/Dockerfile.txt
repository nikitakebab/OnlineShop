FROM openjdk:20

VOLUME /tmp
ADD CRMTest.jar /opt/CRMTest/
EXPOSE 8080
WORKDIR /opt/CRMTest/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "CRMTest.jar"]
