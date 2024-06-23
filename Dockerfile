FROM selenium/standalone-chrome:latest

EXPOSE 5900-6000
EXPOSE 4444-4544

# Install dependencies
USER root

RUN apt-get -qq update
RUN apt-get -qq -y install ffmpeg
RUN apt-get -qq -y install openjdk-17-jdk
#RUN apt-get -qq -y install maven
#RUN apt-get -qq -y install gradle

# Install Maven
ARG MAVEN_VERSION="3.9.1"
ENV MAVEN_HOME /opt/apache-maven-${MAVEN_VERSION}
ENV	MAVEN_BIN ${MAVEN_HOME}/bin
ENV	MAVEN_DOWNLOAD_URL https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz

RUN apt-get update && \
  apt-get install -qq -y curl unzip && \
  mkdir -p /tmp/dependencies && \
  curl -L --silent ${MAVEN_DOWNLOAD_URL} > /tmp/dependencies/apache-maven-${MAVEN_VERSION}.tgz && \
  mkdir -p /opt && \
  tar -xzf /tmp/dependencies/apache-maven-${MAVEN_VERSION}.tgz -C /opt && \
  rm -rf /tmp/dependencies

# Set global PATH such that "maven" command is found
ENV PATH $PATH:$MAVEN_BIN


USER seluser
