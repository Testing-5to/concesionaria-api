# Comenzamos con la imagen oficial de alpine jdk 8
FROM openjdk:8-jdk-alpine

# Descargamos curl
RUN apk add --no-cache curl tar bash procps

# Descargamos e instalamos Maven
# 1- Versión de Maven
ARG MAVEN_VERSION=3.8.6
# 2- Directorio de trabajo
ARG USER_HOME_DIR="/root"
# 3- URL de descarga de Maven
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
# 4- Creamos los directorios, descargamos maven, lo instalamos, removemos los archivos instalados y seteamos los links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
# 5- Definimos las variables de ambiente requeridas por Maven
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Copiamos el pom.xml
COPY mvnw pom.xml ./
# Obtenemos las dependencias
RUN mvn dependency:go-offline
# Copiamos el resto del código
COPY src ./src
# Creamos el jar
RUN mvn package

# Creamos la imagen final
FROM openjdk:8-jdk-alpine
# Copiamos el jar
COPY --from=0 target/*.jar app.jar
# Exponemos el puerto 8080
EXPOSE 8080
# Ejecutamos el jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]