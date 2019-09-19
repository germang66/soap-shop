package com.ggp.wsusers.service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WsUsersEndpoint {

   private static final String NAMESPACE_URI = "http://www.wsusers.com" ;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SayHelloRequest")
	@ResponsePayload
    public String sayHello() {
        return "hello";
    }

}
