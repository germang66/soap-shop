### Intellij-WildFly integration

* ... see Readme-Devel of ws-bills folder.

## About WSDL Generation

* Is define webservices schemas in the next files
    *  `webapp/schemas/UserDetails.xsd`
    *  `webapp/schemas/WsUserServiceOperations.xsd`
* Then is used the plugin `jaxb2-maven-plugin` to generate the classes with the annotations when the project in compiled. 
* When the application is deployed the wsdl is generated in base of the classes with the annotations.


### SOAP Request (with Postman)

* parameters (all request)
    * POST  `http://localhost:8080/ws-users/endpoints/WsUsersService.wsdl`
    * raw -- Text (text/xml)
    * body

* get user by email

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://webservices.wsusers.ggp.com">
 <soapenv:Header/>
 <soapenv:Body>
        <hs:GetUserByEmailRequest>
	    	<hs:email>myEmail@test.com</hs:email>
	    </hs:GetUserByEmailRequest>
 </soapenv:Body>
</soapenv:Envelope>
```

* save user

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hs="http://webservices.wsusers.ggp.com">
 <soapenv:Header/>
 <soapenv:Body>
        <hs:SaveUserRequest>
        	<hs:user>
        		<hs:email>myEmail@test.com</hs:email>	
        	</hs:user>
	    </hs:SaveUserRequest>
 </soapenv:Body>
</soapenv:Envelope>
```

* edit user

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:UpdateUserResponse xmlns:ns2="http://webservices.wsusers.ggp.com">
            <ns2:user>
                <ns2:email>myEmail@test.com</ns2:email>
            </ns2:user>
        </ns2:UpdateUserResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
