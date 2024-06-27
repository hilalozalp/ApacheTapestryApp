package org.example.services;

import org.example.entity.Person;

import java.util.List;

public interface PersonDAO {
    void saveOrUpdate(Person person);
    Person findById(Long id);
    List<Person> findAll();

}
