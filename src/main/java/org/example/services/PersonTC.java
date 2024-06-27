package org.example.services;

import org.example.entity.PersonListBean;

import java.time.LocalDate;

public interface PersonTC {
    void  setPersonBeanAttributes(PersonListBean personListBean, Long id, String firstname, String lastname, LocalDate birthdate, String telno, String email, String street, String streetNo, String postalCode, String city, String country);
}
