FROM fabric8/java-jboss-openjdk8-jdk:1.0.10
# Configurando a variavel responsavel versao do java para download
ENV JAVA_VERSION=8

# Adiciona o repositorio no ubuntu
RUN add-apt-repository -y ppa:webupd8team/java

# Atualizando o sistema e baixando o wget se nao existir
RUN apt-get update

# Baixando a versao do java
RUN apt-get install java-8-oracle mvn

EXPOSE 8888

#Instala o artefato Tercerizarrio 

CMD ["mkdir -p ~/projeto-tercerizzario/deploy"]

VOLUME ~/projeto-tercerizzario/deploy

ADD . ~/projeto-tercerizzario/deploy
