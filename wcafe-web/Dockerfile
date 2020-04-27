#docker run -e JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF8 -Dclient.encoding.override=UTF-8 -Duser.timezone=GMT+09:00 -Dsun.java2d.opengl=false -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dhostname=211.62.49.103 -Dspring.profiles.active=dev -Dconfig.server=http://211.62.49.103:8888 -Dapi.gateway.host=211.62.49.103" -p 8080:8080 -i -t -d to-web:latest
FROM openjdk:8-jdk

# Add Author info
LABEL maintainer="f.hahiho716@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/wcafe-web-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} to-web.jar

# Run the jar file
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-web.jar"]

CMD exec java $JAVA_OPTS -jar /to-web.jar