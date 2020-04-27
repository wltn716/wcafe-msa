#docker run -e JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF8 -Dclient.encoding.override=UTF-8 -Duser.timezone=GMT+09:00 -Dsun.java2d.opengl=false -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dhostname=211.62.49.103 -Dmysql.connection.uri=jdbc:mysql://211.62.49.103:3306/user?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true -Dspring.profiles.active=dev -Dconfig.server=http://211.62.49.103:8888" -p 8090:8090 -i -t -d to-api-gateway:latest
FROM openjdk:8-jdk

# Add Author info
LABEL maintainer="f.hahiho716@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8090

# The application's jar file
ARG JAR_FILE=build/libs/wcafe-api-gateway-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} to-api-gateway.jar

# Run the jar file
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-api-gateway.jar"]
CMD exec java $JAVA_OPTS -jar /to-api-gateway.jar