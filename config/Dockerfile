FROM openjdk:11
ARG JAR_FILE=config-0.0.1.jar
ADD build/libs/${JAR_FILE} /usr/local/config-service/
ADD run.sh /run.sh
RUN chmod +x /run.sh
CMD /run.sh

