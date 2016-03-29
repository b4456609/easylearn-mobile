FROM java:8-jre
COPY dev.yml /opt/dropwizard/
COPY build/libs/user-1.0-SNAPSHOT-all.jar /opt/dropwizard/
EXPOSE 8080
EXPOSE 8081
WORKDIR /opt/dropwizard
CMD ["java", "-jar", "pack-1.0-SNAPSHOT-all.jar", "server", "dev.yml"]