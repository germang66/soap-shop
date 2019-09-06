package com.ggp.wsbills;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.ggp.wsbills.WsBills",
        portName = "WsBillsPort", serviceName = "WsBillsService")
public class WsBillsImp implements WsBills {

    @Override
    public String sayHello() {
        return "Hello World";
    }
}
