package com.ggp.wsbills.repository;

import com.ggp.wsbills.model.Bill;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
public class BillDAO {

    @PersistenceContext
    private EntityManager em;


    /**
     * return list of all the bills
     *
     * @return list of bills
     */
    public List<Bill> findAllBills() {
        Query q = em.createQuery("select b from Bill b");
        List<Bill> bills = q.getResultList();
        fetch(bills);
        return bills;
    }

    /**
     * return list of bills from an user
     *
     * @param userHash user hash
     * @return list of bills
     */
    public List<Bill> findBillsByUserHash(String userHash) {
        Query q = em.createQuery("select b from Bill b where b.userHash = :userHash")
                .setParameter("userHash", userHash);
        List<Bill> bills = q.getResultList();
        fetch(bills);
        return bills;
    }

    /**
     * fetch list of bill items
     *
     * @param bills list of bills
     */
    private void fetch(List<Bill> bills) {
        for (Bill bill : bills) {
            bill.getItems().size();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Bill newBill) {
        newBill.setCreationDate(new Date());
        em.persist(newBill);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Bill updatedBill) {
        Bill dbBill = em.find(Bill.class, updatedBill.getId());
        //just can update confirmation date
        dbBill.setConfirmationDate(updatedBill.getConfirmationDate());
        em.persist(dbBill);
    }

}
