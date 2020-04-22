FROM openjdk:8-jdk

# Add Author info
LABEL maintainer="f.hahiho716@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8888

# The application's jar file
ARG JAR_FILE=build/libs/wcafe-config-server-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} to-config-server.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-config-server.jar"]