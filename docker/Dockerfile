#
# Copyright 2019, TOTVS S.A.
# All rights reserved.
#

# base image
FROM openjdk:11-jre-slim

# add our user and group
RUN groupadd -r fluig && useradd -r -g fluig fluig

# variables and arguments for fluig
ENV FLUIG_HOME "/opt/fluig/"

# artifacts repository
ARG ARTIFACTS_REPOSITORY="http://nexus.fluig.com/content/groups/public"

# define workdir
WORKDIR ${FLUIG_HOME}

# install necessary packages
RUN set -ex && \
   apt-get update && \
   apt-get install -y curl --no-install-recommends && \
   apt-get install -y ca-certificates

# create all folders and set the correct permissions
RUN mkdir -p "${FLUIG_HOME}" && \
    chown -R fluig:fluig "${FLUIG_HOME}"

# create docker entrypoint file with root
USER fluig

# copy artifacts to docker image
ADD --chown=fluig:fluig loquesmitche.jar "${FLUIG_HOME}"

# expose fluig ports
EXPOSE 8080

# create docker entrypoint file with root
USER root

# copy entrypoint
COPY docker-entrypoint.sh /usr/local/bin/

# remove any prior docker-entrypoint.sh if exist
# and add backwards compatibility
RUN set -ex && \
   rm -f /docker-entrypoint.sh && \
   # backwards compatibility
   ln -s /usr/local/bin/docker-entrypoint.sh /docker-entrypoint.sh

# don't let fluig run as root
USER fluig

#ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["java", "-Xms256m", "-Xmx512m", "-XX:MaxMetaspaceSize=128M", "-jar", "loquesmitche.jar", "--add-opens java.base/java.lang=ALL-UNNAMED", "--add-opens java.base/sun.nio.ch=ALL-UNNAMED"]