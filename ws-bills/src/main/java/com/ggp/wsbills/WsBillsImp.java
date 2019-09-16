package com.ggp.wsbills;

import com.ggp.wsbills.model.Bill;
import com.ggp.wsbills.repository.BillDAO;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.ArrayList;
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

    @Override
    public List<Bill> findBillsByUserHash(String userHash) {
        if (userHash == null || userHash.equals(""))
            return new ArrayList<>();
        return billDAO.findBillsByUserHash(userHash);
    }

    @Override
    public void saveBill(Bill newBill) {
        if (newBill == null) return;//TODO: throw exception
        if (newBill.getItems() == null || newBill.getItems().size() == 0) return; //TODO: throw exception
        billDAO.save(newBill);
    }

    @Override
    public void updateBill(Bill updatedBill) {
        if (updatedBill == null) return;
        billDAO.update(updatedBill);
    }

}
