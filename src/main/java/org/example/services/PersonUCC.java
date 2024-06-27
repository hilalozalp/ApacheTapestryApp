package org.example.services;

import org.example.entity.PersonEditBean;
import org.example.entity.PersonListBean;

import java.util.List;

public interface PersonUCC {
    void saveOrUpdatePerson(PersonEditBean personEditBean);
    PersonEditBean findPersonById(Long id);
    List<PersonListBean> findAllPersons();
    void createTestData();

}
