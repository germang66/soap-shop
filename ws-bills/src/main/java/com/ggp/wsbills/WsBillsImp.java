package com.ggp.wsbills;

import com.ggp.wsbills.model.Bill;
import com.ggp.wsbills.repository.BillDAO;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.ggp.wsbills.WsBills",
        portName = "WsBillsPort", serviceName = "WsBillsService")
public class WsBillsImp implements WsBills {

    @EJB(name = "billDAO")
    private BillDAO billDAO;

    @Override
    public List<Bill> findAllBills() {
        return billDAO.findAllBills();
    }
}
