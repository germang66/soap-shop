package com.ggp.wsusers.exception;



import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + InvalidParameterException.NAMESPACE_URI + "} INVALID_PARAMETER")
public class InvalidParameterException extends Exception {

    private static final long serialVersionUID = 1L;
    public static final String NAMESPACE_URI = "http://wsusers.ggp.com/exception";

    public InvalidParameterException(String message) {
        super(message);
    }

}
