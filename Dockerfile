# Comenzamos con la imagen oficial de alpine jdk 8
FROM openjdk:8-jdk-alpine

# Descargamos curl
RUN apk add --no-cache curl tar bash procps

# Downloading and installing Maven
# 1- Define a constant with the version of maven you want to install
ARG MAVEN_VERSION=3.8.6

# 2- Define a constant with the working directory
ARG USER_HOME_DIR="/root"

# 3- Define the URL where maven can be downloaded from
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

# 4- Create the directories, download maven, validate the download, install it, remove downloaded file and set links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downloading maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Unzipping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# 5- Define environmental variables required by Maven, like Maven_Home directory and where the maven repo is located
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Copiamos el pom.xml
COPY mvnw pom.xml ./
# Obtenemos las dependencias
RUN mvn dependency:go-offline
# Copiamos el resto del código
COPY src ./src
# Compilamos el código
CMD ["mvn", "spring-boot:run"]
# Creamos el jar
RUN mvn package

# Creamos la imagen final
FROM openjdk:8-jdk-alpine
# Copiamos el jar
COPY --from=0 target/*.jar app.jar
# Exponemos el puerto 8080
EXPOSE 8080
# Ejecutamos el jar
ENTRYPOINT ["java","-Dspring.profiles.active=test","-jar","/app.jar"]