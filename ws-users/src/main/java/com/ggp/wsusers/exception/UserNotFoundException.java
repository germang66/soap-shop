package com.ggp.wsusers.exception;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + UserNotFoundException.NAMESPACE_URI + "} USER_NOT_FOUND")
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    public static final String NAMESPACE_URI = "http://wsusers.ggp.com/exception";

    public UserNotFoundException(String message) {
        super(message);
    }


}
