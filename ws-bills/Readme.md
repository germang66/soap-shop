Description
===============

* Soap WebService of bills

Stack
========

* Java EE7 
    * JAX-WS
* Oracle Database 12c EE 12.2.0.1.0 64bit
* WildFly9



Intellij-WildFly integration
==============================

# Settings

* In Run Debug Configuration choose JBoss Local
    * select WilFly 9.0.0.Final directory
* select operation mode: standalone
* jre: path to jdk-1.7

Setup Datasource in Wildfly
=============================

* download jdbc driver obdbc7.jar for oracle db 12.
* Create subfolders `[WILDFLY_HOME]/modules/system/layers/base/com/oracle/main/` 
* move jar here and create module.xml with the content

```
<module xmlns="urn:jboss:module:1.1" name="com.oracle">
  <resources>
    <resource-root path="ojdbc7.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

* in `[WILDFLY_HOME]/standalone/configuration/standalone.xml` add:
    * inside subsystem > datasources > drivers
    
    ```
   <driver name="oracle" module="com.oracle">
        <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    </driver> 
    ```
    
    * inside subsystem > datasources
    
    ```
   <datasource jndi-name="java:/oracleDS" pool-name="OracleDS" enabled="true">
     <connection-url>jdbc:oracle:thin:@[HOST_NAME]:1521/[ServiceName]</connection-url>
      <driver>oracle</driver>
      <pool>
       <min-pool-size>1</min-pool-size>
       <max-pool-size>5</max-pool-size>
       <prefill>true</prefill>
      </pool>
      <security>
       <user-name>[USER]</user-name>
       <password>[PWD]</password>
      </security>
    </datasource> 
    ```