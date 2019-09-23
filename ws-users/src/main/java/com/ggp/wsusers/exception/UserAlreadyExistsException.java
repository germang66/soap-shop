package com.ggp.wsusers.exception;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + UserAlreadyExistsException.NAMESPACE_URI + "} USER_ALREADY_EXISTS")
public class UserAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;
    public static final String NAMESPACE_URI = "http://wsusers.ggp.com/exception";

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
