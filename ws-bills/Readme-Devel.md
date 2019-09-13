### Intellij-WildFly integration

##### Settings

* In Run Debug Configuration choose JBoss Local
    * select WilFly 9.0.0.Final directory
* select operation mode: standalone
* jre: path to jdk-1.7

### Setup Datasource in Wildfly

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
    
### WildFly9 Problem: java.lang.OutOfMemoryError: PermGen space 

* edit file `[WILDFLY_HOME]/bin/standalone.conf.bat` and below `:JAVA_OPTS_SET`

```

set "JAVA_OPTS=%JAVA_OPTS% -XX:+CMSPermGenSweepingEnabled -XX:+CMSClassUnloadingEnabled"
set "JAVA_OPTS=%JAVA_OPTS% -XX:PermSize=64M -XX:MaxPermSize=128M"
```

### SOAP Request (with Postman)

* POST  `http://localhost:8080/ws-bills/WsBillsService?wsdl`
    * raw -- Text (text/plain)
    * body
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://www.wsbills.com">
 <soapenv:Body>
     <hs:findAllBills>
     </hs:findAllBills>
 </soapenv:Body>
</soapenv:Envelope>
```





