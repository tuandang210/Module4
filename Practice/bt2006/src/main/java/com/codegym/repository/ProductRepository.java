package com.codegym.repository;

import com.codegym.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Product> findALl() {
        TypedQuery<Product> query = entityManager.createQuery("select c from Product c", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery("select c from Product c where c.id = ?1", Product.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
