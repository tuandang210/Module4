package com.codegym.repository;

import com.codegym.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CategoryRepository implements ICategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findALl() {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(Long id) {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c where c.id = ?1", Category.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public void save(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        } else {
            entityManager.merge(category);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
