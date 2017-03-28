FROM java:8
volume /tmp 

ADD tercerizzario-service-server.jar /app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8761

# Assim que iniciar o container o comando abaixo eh executado como ponto de entrada
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
