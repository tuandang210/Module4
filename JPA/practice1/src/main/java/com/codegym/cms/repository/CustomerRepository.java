package com.codegym.cms.repository;

import com.codegym.cms.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CustomerRepository implements ICustomerRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.id = ?1", Customer.class);
        query.setParameter(1,id);
        try {
            return query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if(customer.getId() == null){
            entityManager.persist(customer);
        }else {
            entityManager.merge(customer);
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if(customer != null){
            entityManager.remove(customer);
        }
    }
}
