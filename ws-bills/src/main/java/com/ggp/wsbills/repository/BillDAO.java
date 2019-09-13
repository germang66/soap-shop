package com.ggp.wsbills.repository;

import com.ggp.wsbills.model.Bill;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BillDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Bill> findAllBills() {
        Query q = em.createQuery("select b from Bill b");
        List<Bill> bills = q.getResultList();
        return bills;
    }

}
