package org.example.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entity.Person;
import org.example.entity.PersonEditBean;
import org.example.entity.PersonListBean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PersonUCCImpl implements PersonUCC{

    @Inject
    private PersonDAO personDAO;

    @Inject PersonTC personTC;



    @Override
    public void saveOrUpdatePerson(PersonEditBean personEditBean) {
        Person person = personEditBean.toEntity();
        personDAO.saveOrUpdate(person);

    }

    @Override
    public PersonEditBean findPersonById(Long id) {
        Person person = personDAO.findById(id);
        return new PersonEditBean(person);
    }



    @Override
    public List<PersonListBean> findAllPersons() {
        List<Person> personList = personDAO.findAll();
        return personList.stream().map(PersonListBean::new).collect(Collectors.toList());
    }

    public void createTestData() {
        if (personDAO.findAll().isEmpty()) {
            personDAO.saveOrUpdate(new Person("John", "Doe", LocalDate.of(1990, 1, 1), "1234567890", "john.doe@example.com", "Main Street", "10", "12345", "City", "Country"));
            personDAO.saveOrUpdate(new Person("Jane", "Doe", LocalDate.of(1992, 2, 2), "0987654321", "jane.doe@example.com", "Second Street", "20", "54321", "Town", "Country"));
        }
    }

}
