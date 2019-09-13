package com.ggp.wsbills;


import com.ggp.wsbills.model.Bill;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(name="WsBills",
        targetNamespace = "http://www.wsbills.com")
public interface WsBills {

    @WebMethod
    List<Bill> findAllBills();

}
