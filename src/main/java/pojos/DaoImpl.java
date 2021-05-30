package pojos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl<T, B extends Serializable> implements DAO<T, B> {
    Session session = null;
    Transaction transaction = null;
    EntityManager entityManager = null;
    SessionFactoryUtil sessionFactoryUtil = null;

    @Override
    public void persist(T t) {
        session = sessionFactoryUtil.getSession();
        transaction = session.getTransaction();
        session.persist(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(T t) {
        session = sessionFactoryUtil.getSession();
        session.saveOrUpdate(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public T findById(B b) {
        session=SessionFactoryUtil.getSession();
        Query query=session.createQuery("FROM Person where id=:paramId");
        query.setParameter("paramId",b);
        List<T> list=query.getResultList();
        T t=null;
        try {
            t=list.get(0);
            return t;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("There aren't any entity's with this id");
        }
        return t;
    }

    @Override
    public void delete(B b) {
        session = sessionFactoryUtil.getSession();
        Person person = session.get(Person.class, b);
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T> findAll(List<T> list) {
        session = sessionFactoryUtil.getSession();
        list = (List<T>) session.createQuery("from Person ");
        session.close();
        return list;
    }

    @Override
    public void deleteAll() {
        session = sessionFactoryUtil.getSession();
        List<T> list = (List<T>) session.createQuery("from Person").list();
        for (T t : list) {
            session.delete(t);
        }
        session.getTransaction().commit();
        session.close();
    }
}
