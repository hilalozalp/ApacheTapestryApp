package org.example.entity;

import java.time.LocalDate;
import org.apache.tapestry5.beaneditor.Validate;

public class PersonEditBean {

    private Long id;

    @Validate("required")
    private String firstname;

    @Validate("required")
    private String lastname;

    @Validate("required")
    private LocalDate birthdate;

    private String telNo;

    @Validate("required, email")
    private String email;

    private String street;
    private String streetNo;
    private String postalCode;
    private String city;
    private String country;

    // Getter und Setter Methoden

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person toEntity() {
        Person person = new Person();
        person.setPersonId(this.id);
        person.setFirstname(this.firstname);
        person.setLastname(this.lastname);
        person.setBirthdate(this.birthdate);
        person.setTelNo(this.telNo);
        person.setEmail(this.email);
        person.setStreet(this.street);
        person.setStreetNo(this.streetNo);
        person.setPostalCode(this.postalCode);
        person.setCity(this.city);
        person.setCountry(this.country);
        return person;
    }

    public PersonEditBean() {
    }

    public PersonEditBean(Person person) {
        this.id = person.getPersonId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.birthdate = person.getBirthdate();
        this.telNo = person.getTelNo();
        this.email = person.getEmail();
        this.street = person.getStreet();
        this.streetNo = person.getStreetNo();
        this.postalCode = person.getPostalCode();
        this.city = person.getCity();
        this.country = person.getCountry();
    }
}
