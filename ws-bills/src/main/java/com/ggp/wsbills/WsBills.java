package com.ggp.wsbills;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name="WsBills",
        targetNamespace = "http://www.wsbills.com")
public interface WsBills {

    @WebMethod
    String sayHello();

}
