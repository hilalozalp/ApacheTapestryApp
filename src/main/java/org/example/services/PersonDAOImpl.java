package org.example.services;

import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private static final Logger logger = LogManager.getLogger(PersonDAOImpl.class);

    @Override
    public List<Person> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.info("Fetching all persons from database");
        List<Person> personList = session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        session.close();
        if (personList == null) {
            logger.warn("No persons found in the database");
            return new ArrayList<>();
        }
        logger.info("Found {} persons in the database", personList.size());
        return personList;
    }

    @Override
    public Person findById(Long personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.info("Fetching person with ID {}", personId);
        Person person = session.get(Person.class, personId);
        session.close();
        return person;
    }

    @Override
    public void saveOrUpdate(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (person.getPersonId() == null) {
                logger.info("Saving new person: {}", person);
                session.save(person);
            } else {
                logger.info("Updating person: {}", person);
                session.merge(person);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Error saving or updating person", e);
            throw e;
        } finally {
            session.close();
        }
    }
}
