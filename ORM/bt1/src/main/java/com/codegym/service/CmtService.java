package com.codegym.service;

import com.codegym.model.Cmt;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Service
public class CmtService implements ICmtService {
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cmt> findAll() {
        String queryStr = "SELECT c FROM Cmt AS c";
        TypedQuery<Cmt> query = entityManager.createQuery(queryStr, Cmt.class);
        return query.getResultList();
    }

    @Override
    public Cmt findOne(Long id) {
        if(id == null){
            return new Cmt();
        }
        String queryStr = "SELECT c FROM Cmt AS c WHERE c.id = :id";
        TypedQuery<Cmt> query = entityManager.createQuery(queryStr, Cmt.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Cmt save(Cmt cmt) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Cmt origin = findOne(cmt.getId());
            origin.setAuthor(cmt.getAuthor());
            origin.setRate(cmt.getRate());
            origin.setFeedback(cmt.getFeedback());
            origin.setDate(cmt.getDate());
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
