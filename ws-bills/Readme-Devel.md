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

* parameters (all request)
    * POST  `http://localhost:8080/ws-bills/WsBillsService?wsdl`
    * raw -- Text (text/plain)
    * body

* list all the bills 
   
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://www.wsbills.com">
 <soapenv:Body>
     <hs:findAllBills>
     </hs:findAllBills>
 </soapenv:Body>
</soapenv:Envelope>
```


* save bill

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://www.wsbills.com">
 <soapenv:Body>
     <hs:saveBill>
        <arg0>
		    <items>
		        <amount>14</amount>
		        <price>5500.0</price>
		        <productHash>product123</productHash>
		    </items>
		    <items>
		        <amount>15</amount>
		        <price>10500.0</price>
		        <productHash>product456</productHash>
		    </items>
		    <items>
		        <amount>17</amount>
		        <price>2400.0</price>
		        <productHash>product789</productHash>
		    </items>
		    <paymentMethod>myMethod1234</paymentMethod>
		    <userHash>user333</userHash>
	    </arg0>
	</hs:saveBill>
 </soapenv:Body>
</soapenv:Envelope>
```

* list bills of user 
    * is used the userHash

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://www.wsbills.com">
 <soapenv:Body>
     <hs:findBillsByUserHash>
        <arg0>user333</arg0>
	 </hs:findBillsByUserHash>
 </soapenv:Body>
</soapenv:Envelope>
```

* update bill
    * require id for update bill
    * just can update confirmation date.

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://www.wsbills.com">
 <soapenv:Body>
     <hs:updateBill>
        <arg0>
		    <id>3</id>
		    <confirmationDate>2022-10-10T19:02:48-03:00</confirmationDate>
	    </arg0>
	</hs:updateBill>
 </soapenv:Body>
</soapenv:Envelope>
```


