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
# Create user
RUN /opt/jboss/wildfly/bin/add-user.sh ggp ggp1234 --silent

# Add custom configuration file (datasources)
ADD wildfly-conf/standalone.xml /opt/jboss/wildfly/standalone/configuration/
ADD wildfly-conf/oracle /opt/jboss/wildfly/modules/system/layers/base/com/oracle

# Move War
COPY --from=build /usr/src/app/target/ws-users.war /opt/jboss/wildfly/standalone/deployments/

# Run
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone.xml"]