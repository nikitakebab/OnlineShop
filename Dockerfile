FROM openjdk:20

VOLUME /tmp
ADD target/onlineshopdev.jar /opt/CRMTest/
EXPOSE 8080
WORKDIR /opt/CRMTest/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "onlineshopdev.jar"]