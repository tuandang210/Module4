package com.codegym.repository;

import com.codegym.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
public class CustomerRepository implements ICustomerRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        Query query = entityManager.createNativeQuery("CALL Insert_Customer(?1, ?2)");
        query.setParameter(1,customer.getFirstName());
        query.setParameter(2,customer.getLastName());
        return query.executeUpdate() == 0;
    }
}