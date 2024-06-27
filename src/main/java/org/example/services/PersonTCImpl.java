package org.example.services;

import org.example.entity.PersonListBean;

import java.time.LocalDate;

public class PersonTCImpl implements PersonTC{


    @Override
    public void setPersonBeanAttributes(PersonListBean personListBean, Long id, String firstname, String lastname, LocalDate birthdate, String telno, String email, String street, String streetNo, String postalCode, String city, String country) {
        personListBean.setId(id);
        personListBean.setFirstname(firstname);
        personListBean.setLastname(lastname);
        personListBean.setBirthdate(birthdate);
        personListBean.setTelNo(telno);
        personListBean.setEmail(email);
        personListBean.setStreet(street);
        personListBean.setStreetNo(streetNo);
        personListBean.setPostalCode(postalCode);
        personListBean.setCity(city);
        personListBean.setCountry(country);
    }
}
