#---------------
# Build stage
#--------------
FROM maven:3.5.3-jdk-7-alpine AS build
COPY src /usr/src/app/src 
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

#----------------
# Deploy stage
#----------------
FROM jboss/wildfly:9.0.0.Final

ARG ADMIN_USER
ARG ADMIN_PASS
ARG WAR_FILE

# Create user
RUN /opt/jboss/wildfly/bin/add-user.sh ${ADMIN_USER} ${ADMIN_PASS} --silent

# Add custom configuration file (datasources)
ADD wildfly-conf/standalone.xml /opt/jboss/wildfly/standalone/configuration/
ADD wildfly-conf/oracle /opt/jboss/wildfly/modules/system/layers/base/com/oracle

# Move War
COPY --from=build /usr/src/app/target/${WAR_FILE} /opt/jboss/wildfly/standalone/deployments/

# Run
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone.xml"]